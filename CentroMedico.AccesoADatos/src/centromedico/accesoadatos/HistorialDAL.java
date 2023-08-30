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
        sql += (obtenerCampos() + " FROM Historial h");
        return sql;
    }
    
    private static String agregarOrderBy(Historial pHistorial) {
        String sql = " ORDER BY h.Id DESC";
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
            sql = "UPDATE Historial SET IdPaciente=?, DetalleRegistro=? WHERE Id=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, pHistorial.getIdPaciente());
                ps.setString(2, pHistorial.getDetalleRegistro());
                ps.setInt(3, pHistorial.getId());
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
    
    private static void obtenerDatos(PreparedStatement pPS, ArrayList<Historial> pHistorial) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS);) {
            while (resultSet.next()) {
                Historial historial = new Historial(); 
                asignarDatosResultSet(historial, resultSet, 0);
                pHistorial.add(historial);
            }
            resultSet.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }
    
   private static void obtenerDatosIncluirPaciente(PreparedStatement pPS, ArrayList<Historial> pHistoriales) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS);) {
            HashMap<Integer, Paciente> pacienteMap = new HashMap(); 
            while (resultSet.next()) {
                Historial historial = new Historial();
                int index = asignarDatosResultSet(historial, resultSet, 0);
                if (pacienteMap.containsKey(historial.getIdPaciente()) == false) {
                    Paciente paciente = new Paciente();
                    PacienteDAL.asignarDatosResultSet(paciente, resultSet, index);
                    pacienteMap.put(paciente.getId(), paciente); 
                    historial.setPaciente(paciente); 
                } else {
                    historial.setPaciente(pacienteMap.get(historial.getIdPaciente())); 
                }
                pHistoriales.add(historial); 
            }
            resultSet.close();
        } catch (SQLException ex) {
            throw ex; 
        }
    }
    
    public static Historial obtenerPorId(Historial pHistorial) throws Exception {
        Historial historial = new Historial();
        ArrayList<Historial> historiales = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) { 
            String sql = obtenerSelect(pHistorial);
            sql += " WHERE h.Id=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, pHistorial.getId());
                obtenerDatos(ps, historiales);
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        }
        catch (SQLException ex) {
            throw ex;
        }
        if (historiales.size() > 0) {
            historial = historiales.get(0);
        }
        return historial;
    }
    
    public static ArrayList<Historial> obtenerTodos() throws Exception {
        ArrayList<Historial> historiales = new ArrayList<>();
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = obtenerSelect(new Historial());
            sql += agregarOrderBy(new Historial());
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                obtenerDatos(ps, historiales);
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        } 
        catch (SQLException ex) {
            throw ex;
        }
        return historiales;
    }
    
    static void querySelect(Historial pHistorial, ComunDB.utilQuery pUtilQuery) throws SQLException {
        PreparedStatement statement = pUtilQuery.getStatement();
        if (pHistorial.getId() > 0) {
            pUtilQuery.AgregarNumWhere(" h.Id=? ");
            if (statement != null) { 
                statement.setInt(pUtilQuery.getNumWhere(), pHistorial.getId()); 
            }
        }
        
         if (pHistorial.getIdPaciente() > 0) {
            pUtilQuery.AgregarNumWhere(" h.IdPaciente=? ");
            if (statement != null) {
                statement.setInt(pUtilQuery.getNumWhere(), pHistorial.getIdPaciente());
            }
        }

////        if (pHistorial.getFechaEntrada() != null && pHistorial.getFechaEntrada().trim().isEmpty() == false) {
////            pUtilQuery.AgregarNumWhere(" h.FechaEntrada LIKE ? "); 
////            if (statement != null) {
////                statement.setString(pUtilQuery.getNumWhere(), "%" + pHistorial.getFechaEntrada() + "%"); 
////            }
////        }
        
        if (pHistorial.getDetalleRegistro()!= null && pHistorial.getDetalleRegistro().trim().isEmpty() == false) {
            pUtilQuery.AgregarNumWhere(" h.DetalleRegistro LIKE ? "); 
            if (statement != null) {
                statement.setString(pUtilQuery.getNumWhere(), "%" + pHistorial.getDetalleRegistro()+ "%"); 
            }
        }
        
    }
    
    public static ArrayList<Historial> buscar(Historial pHistorial) throws Exception {
        ArrayList<Historial> historiales = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = obtenerSelect(pHistorial);
            ComunDB comundb = new ComunDB();
            ComunDB.utilQuery utilQuery = comundb.new utilQuery(sql, null, 0); 
            querySelect(pHistorial, utilQuery);
            sql = utilQuery.getSQL(); 
            sql += agregarOrderBy(pHistorial);
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0); 
                querySelect(pHistorial, utilQuery);
                obtenerDatos(ps, historiales);
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        }
        catch (SQLException ex) {
            throw ex;
        }
        return historiales;
    }
    
     public static ArrayList<Historial> buscarIncluirPaciente(Historial pHistorial) throws Exception {
        ArrayList<Historial> historiales = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = "SELECT ";
            if (pHistorial.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER) {
                sql += "TOP " + pHistorial.getTop_aux() + " "; 
            }
            sql += obtenerCampos();
            sql += ",";
            sql += PacienteDAL.obtenerCampos();
            sql += " FROM Historial h";
            sql += " JOIN Paciente pa on (h.IdPaciente=pa.Id)";
            ComunDB comundb = new ComunDB();
            ComunDB.utilQuery utilQuery = comundb.new utilQuery(sql, null, 0);
            querySelect(pHistorial, utilQuery);
            sql = utilQuery.getSQL();
            sql += agregarOrderBy(pHistorial);
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0);
                querySelect(pHistorial, utilQuery);
                obtenerDatosIncluirPaciente(ps, historiales);
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        } catch (SQLException ex) {
            throw ex;
        }
        return historiales;
    }
}
