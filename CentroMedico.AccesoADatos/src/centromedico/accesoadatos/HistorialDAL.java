package centromedico.accesoadatos;

import java.util.*;
import java.sql.*;
import centromedico.entidadesdenegocios.*;
import java.time.LocalDate;

public class HistorialDAL {
    
   static String obtenerCampos() {
        return "h.Id, h.idPaciente, h.FechaEntrada, h.DetalleRegistro";
    }
    
   private static String obtenerSelect(Historial pHistorial) {
        String sql;
        sql = "SELECT ";
        if (pHistorial.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER) {            
            sql += "TOP " + pHistorial.getTop_aux() + " ";
        }
        sql += (obtenerCampos() + " FROM Contacto c");
        return sql;
    }
    
    private static String agregarOrderBy(Historial pHistorial) {
        String sql = " ORDER BY c.Id DESC";
        if (pHistorial.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.MYSQL) {
            sql += " LIMIT " + pHistorial.getTop_aux() + " ";
        }
        return sql;
    }
    
    public static int crear(Historial pHistorial) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) { 
            sql = "INSERT INTO Historial(IdPaciente, FechaEntrada, DetalleRegistro) VALUES(?, ?, ?)";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, pHistorial.getIdPaciente());
                ps.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
                ps.setString(3, pHistorial.getDetalleRegistro());
                result = ps.executeUpdate();
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        } catch (SQLException ex) {
            throw ex;
        }
        return result;
    }
    
    public static int modificar(Historial pHistorial) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) {
            sql = "UPDATE Historial SET IdPaciente=?, FechaEntrada = ?, DetalleRegistro = ?, WHERE Id=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, pHistorial.getIdPaciente());
                ps.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
                ps.setString(3, pHistorial.getDetalleRegistro());
                ps.setInt(4, pHistorial.getId());
                result = ps.executeUpdate();
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        } catch (SQLException ex) {
            throw ex;
        }
        return result;
    }
    
    public static int eliminar(Historial pHistorial) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) {
            sql = "DELETE FROM Historial WHERE Id=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, pHistorial.getId());
                result = ps.executeUpdate();
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        } catch (SQLException ex) {
            throw ex;
        }
        return result;
    }
    
    static int asignarDatosResultSet(Historial pHistorial, ResultSet pResultSet, int pIndex) throws Exception {
        pIndex++;
        pHistorial.setId(pResultSet.getInt(pIndex));
        pIndex++;
        pHistorial.setIdPaciente(pResultSet.getInt(pIndex));
        pIndex++;
        pHistorial.setFechaEntrada(pResultSet.getDate(pIndex).toLocalDate()); 
        pIndex++;
        pHistorial.setDetalleRegistro(pResultSet.getString(pIndex));
        return pIndex;
    }
    
    private static void obtenerDatos(PreparedStatement pPS, ArrayList<Historial> pContactos) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS);) {
            while (resultSet.next()) {
                Historial contacto = new Historial(); 
                asignarDatosResultSet(contacto, resultSet, 0);
                pContactos.add(contacto);
            }
            resultSet.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }
    
    public static Historial obtenerPorId(Historial pHistorial) throws Exception {
        Historial contacto = new Historial();
        ArrayList<Historial> historial = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) { 
            String sql = obtenerSelect(pHistorial);
            sql += " WHERE c.Id=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, pHistorial.getId());
                obtenerDatos(ps, historial);
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        }
        catch (SQLException ex) {
            throw ex;
        }
        if (historial.size() > 0) {
            contacto = historial.get(0);
        }
        return contacto;
    }
    
    public static ArrayList<Historial> obtenerTodos() throws Exception {
        ArrayList<Historial> contactos = new ArrayList<>();
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = obtenerSelect(new Historial());
            sql += agregarOrderBy(new Historial());
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                obtenerDatos(ps, contactos);
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        } 
        catch (SQLException ex) {
            throw ex;
        }
        return contactos;
    }
    
    static void querySelect(Contacto pContacto, ComunDB.utilQuery pUtilQuery) throws SQLException {
        PreparedStatement statement = pUtilQuery.getStatement();
        if (pContacto.getId() > 0) {
            pUtilQuery.AgregarNumWhere(" c.Id=? ");
            if (statement != null) { 
                statement.setInt(pUtilQuery.getNumWhere(), pContacto.getId()); 
            }
        }

        if (pContacto.getNombre() != null && pContacto.getNombre().trim().isEmpty() == false) {
            pUtilQuery.AgregarNumWhere(" c.Nombre LIKE ? "); 
            if (statement != null) {
                statement.setString(pUtilQuery.getNumWhere(), "%" + pContacto.getNombre() + "%"); 
            }
        }
        
        if (pContacto.getEmail()!= null && pContacto.getEmail().trim().isEmpty() == false) {
            pUtilQuery.AgregarNumWhere(" c.Email LIKE ? "); 
            if (statement != null) {
                statement.setString(pUtilQuery.getNumWhere(), "%" + pContacto.getEmail()+ "%"); 
            }
        }
        
        if (pContacto.getTelefono()!= null && pContacto.getTelefono().trim().isEmpty() == false) {
            pUtilQuery.AgregarNumWhere(" c.Telefono LIKE ? "); 
            if (statement != null) {
                statement.setString(pUtilQuery.getNumWhere(), "%" + pContacto.getTelefono()+ "%"); 
            }
        }
        
        if (pContacto.getCelular()!= null && pContacto.getCelular().trim().isEmpty() == false) {
            pUtilQuery.AgregarNumWhere(" c.Celular LIKE ? "); 
            if (statement != null) {
                statement.setString(pUtilQuery.getNumWhere(), "%" + pContacto.getCelular()+ "%"); 
            }
        }
    }
    
    public static ArrayList<Contacto> buscar(Contacto pContacto) throws Exception {
        ArrayList<Contacto> contactos = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = obtenerSelect(pContacto);
            ComunDB comundb = new ComunDB();
            ComunDB.utilQuery utilQuery = comundb.new utilQuery(sql, null, 0); 
            querySelect(pContacto, utilQuery);
            sql = utilQuery.getSQL(); 
            sql += agregarOrderBy(pContacto);
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0); 
                querySelect(pContacto, utilQuery);
                obtenerDatos(ps, contactos);
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        }
        catch (SQLException ex) {
            throw ex;
        }
        return contactos;
    }

}
