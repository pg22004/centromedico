package centromedico.accesoadatos;

import java.util.*;
import java.sql.*;
import centromedico.entidadesdenegocios.*;
import java.time.LocalDate;

public class PacienteDAL {
  
    static String obtenerCampos() {
        return "pa.Id, pa.Nombre, pa.Apellido, pa.FechaRegistro";
    }
    
    private static String obtenerSelect(Paciente pPaciente) {
        String sql;
        sql = "SELECT ";
        if (pPaciente.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER) {            
            sql += "TOP " + pPaciente.getTop_aux() + " ";
        }
        sql += (obtenerCampos() + " FROM Paciente pa");
        return sql;
    }
    
    private static String agregarOrderBy(Paciente pPaciente) {
        String sql = " ORDER BY pa.Id DESC";
        if (pPaciente.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.MYSQL) {
            sql += " LIMIT " + pPaciente.getTop_aux() + " ";
        }
        return sql;
    }
    
    public static int crear(Paciente pPaciente) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) { 
            sql = "INSERT INTO Paciente(Nombre, Apellido, FechaRegistro) VALUES(?, ?, ?)";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setString(1, pPaciente.getNombre());
                ps.setString(2, pPaciente.getApellido());
                ps.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
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
    
    public static int modificar(Paciente pPaciente) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) {
            sql = "UPDATE Paciente SET Nombre=?, Apellido = ? WHERE Id=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setString(1, pPaciente.getNombre());
                ps.setString(2, pPaciente.getApellido());
                
                ps.setInt(3, pPaciente.getId());
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
    
    public static int eliminar(Paciente pPaciente) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) {
            sql = "DELETE FROM Paciente WHERE Id=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, pPaciente.getId());
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
    
    static int asignarDatosResultSet(Paciente pPaciente, ResultSet pResultSet, int pIndex) throws Exception {
        pIndex++;
        pPaciente.setId(pResultSet.getInt(pIndex));
        pIndex++;
        pPaciente.setNombre(pResultSet.getString(pIndex));
        pIndex++;
        pPaciente.setApellido(pResultSet.getString(pIndex));
        pIndex++;
        pPaciente.setFechaRegistro(pResultSet.getDate(pIndex).toLocalDate()); 
        return pIndex;
    }
    
    private static void obtenerDatos(PreparedStatement pPS, ArrayList<Paciente> pPacientes) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS);) {
            while (resultSet.next()) {
                Paciente paciente = new Paciente(); 
                asignarDatosResultSet(paciente, resultSet, 0);
                pPacientes.add(paciente);
            }
            resultSet.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }
    
    public static Paciente obtenerPorId(Paciente pPaciente) throws Exception {
        Paciente paciente = new Paciente();
        ArrayList<Paciente> pacientes = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) { 
            String sql = obtenerSelect(pPaciente);
            sql += " WHERE pa.Id=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, pPaciente.getId());
                obtenerDatos(ps, pacientes);
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        }
        catch (SQLException ex) {
            throw ex;
        }
        if (pacientes.size() > 0) {
            paciente = pacientes.get(0);
        }
        return paciente;
    }
    
    public static ArrayList<Paciente> obtenerTodos() throws Exception {
        ArrayList<Paciente> pacientes = new ArrayList<>();
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = obtenerSelect(new Paciente());
            sql += agregarOrderBy(new Paciente());
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                obtenerDatos(ps, pacientes);
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        } 
        catch (SQLException ex) {
            throw ex;
        }
        return pacientes;
    }
    
    static void querySelect(Paciente pPaciente, ComunDB.utilQuery pUtilQuery) throws SQLException {
        PreparedStatement statement = pUtilQuery.getStatement();
        if (pPaciente.getId() > 0) {
            pUtilQuery.AgregarNumWhere(" pa.Id=? ");
            if (statement != null) { 
                statement.setInt(pUtilQuery.getNumWhere(), pPaciente.getId()); 
            }
        }

        if (pPaciente.getNombre() != null && pPaciente.getNombre().trim().isEmpty() == false) {
            pUtilQuery.AgregarNumWhere(" pa.Nombre LIKE ? "); 
            if (statement != null) {
                statement.setString(pUtilQuery.getNumWhere(), "%" + pPaciente.getNombre() + "%"); 
            }
        }
        
        if (pPaciente.getApellido()!= null && pPaciente.getApellido().trim().isEmpty() == false) {
            pUtilQuery.AgregarNumWhere(" pa.Apellido LIKE ? "); 
            if (statement != null) {
                statement.setString(pUtilQuery.getNumWhere(), "%" + pPaciente.getApellido()+ "%"); 
            }
        }
        
//       if (pPaciente.getFechaRegistro()!= null && pPaciente.getFechaRegistro().trim().isEmpty() == false) {
//            pUtilQuery.AgregarNumWhere(" p.FechaRegistro LIKE ? "); 
//            if (statement != null) {
//                statement.setString(pUtilQuery.getNumWhere(), "%" + pPaciente.getFechaRegistro()+ "%"); 
//            }
//        }
        
    }
    
    public static ArrayList<Paciente> buscar(Paciente pPaciente) throws Exception {
        ArrayList<Paciente> pacientes = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = obtenerSelect(pPaciente);
            ComunDB comundb = new ComunDB();
            ComunDB.utilQuery utilQuery = comundb.new utilQuery(sql, null, 0); 
            querySelect(pPaciente, utilQuery);
            sql = utilQuery.getSQL(); 
            sql += agregarOrderBy(pPaciente);
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0); 
                querySelect(pPaciente, utilQuery);
                obtenerDatos(ps, pacientes);
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        }
        catch (SQLException ex) {
            throw ex;
        }
        return pacientes;
    }
    
}
