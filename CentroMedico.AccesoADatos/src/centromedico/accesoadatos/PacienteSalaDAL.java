package centromedico.accesoadatos;

import java.util.*;
import java.sql.*;
import centromedico.entidadesdenegocios.*;
import java.time.LocalDate;

public class PacienteSalaDAL {
    
     static String obtenerCampos() {
        return "ps.Id, ps.IdPaciente, ps.IdSala, ps.Fecha";
    }
     
     private static String obtenerSelect(PacienteSala pPacienteSala) {
        String sql;
        sql = "SELECT ";
        if (pPacienteSala.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER) {            
            sql += "TOP " + pPacienteSala.getTop_aux() + " ";
        }
        sql += (obtenerCampos() + " FROM PacienteSala ps");
        return sql;
    } 
     
    private static String agregarOrderBy(PacienteSala pPacienteSala) {
        String sql = " ORDER BY ps.Id DESC";
        if (pPacienteSala.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.MYSQL) {
            sql += " LIMIT " + pPacienteSala.getTop_aux() + " ";
        }
        return sql;
    } 
    
    public static int crear(PacienteSala pPacienteSala) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) { 
            sql = "INSERT INTO PacienteSala(IdPaciente, IdSala, Fecha) VALUES(?, ?, ?)";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, pPacienteSala.getIdPaciente());
                ps.setInt(2, pPacienteSala.getIdSala());
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
    
    public static int modificar(PacienteSala pPacienteSala) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) {
            sql = "UPDATE PacienteSala SET IdPaciente=?, IdSala=? WHERE Id=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, pPacienteSala.getIdPaciente());
                ps.setInt(2, pPacienteSala.getIdSala());
                ps.setInt(3, pPacienteSala.getId());
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
    
     public static int eliminar(PacienteSala pPacienteSala) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) {
            sql = "DELETE FROM PacienteSala WHERE Id=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, pPacienteSala.getId());
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
     
     static int asignarDatosResultSet(PacienteSala pPacienteSala, ResultSet pResultSet, int pIndex) throws Exception {
        pIndex++;
        pPacienteSala.setId(pResultSet.getInt(pIndex));
        pIndex++;
        pPacienteSala.setIdPaciente(pResultSet.getInt(pIndex));
        pIndex++;
        pPacienteSala.setIdSala(pResultSet.getInt(pIndex));
        pIndex++;
        pPacienteSala.setFecha(pResultSet.getDate(pIndex).toLocalDate()); 
        return pIndex;
    }
     
      private static void obtenerDatos(PreparedStatement pPS, ArrayList<PacienteSala> pPacienteSala) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS);) {
            while (resultSet.next()) {
                PacienteSala pacientesala = new PacienteSala(); 
                asignarDatosResultSet(pacientesala, resultSet, 0);
                pPacienteSala.add(pacientesala);
            }
            resultSet.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }
      
     private static void obtenerDatosIncluirRelaciones(PreparedStatement pPS, ArrayList<PacienteSala> pPacienteSalas) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS);) {
            HashMap<Integer, Paciente> pacienteMap = new HashMap(); 
            HashMap<Integer, Sala> salaMap = new HashMap(); 
            while (resultSet.next()) {
                PacienteSala pacientesala = new PacienteSala();
                int index = asignarDatosResultSet(pacientesala, resultSet, 0);
                if (pacienteMap.containsKey(pacientesala.getIdPaciente()) == false) {
                    Paciente paciente = new Paciente();
                    PacienteDAL.asignarDatosResultSet(paciente, resultSet, index);
                    pacienteMap.put(paciente.getId(), paciente); 
                    pacientesala.setPaciente(paciente); 
                } else {
                    pacientesala.setPaciente(pacienteMap.get(pacientesala.getIdPaciente())); 
                }
                if (salaMap.containsKey(pacientesala.getIdSala()) == false) {
                    Sala sala = new Sala();
                    SalaDAL.asignarDatosResultSet(sala, resultSet, index+4);
                    salaMap.put(sala.getId(), sala); 
                    pacientesala.setSala(sala); 
                } else {
                    pacientesala.setSala(salaMap.get(pacientesala.getIdSala())); 
                }
                pPacienteSalas.add(pacientesala); 
            }
            resultSet.close();
        } catch (SQLException ex) {
            throw ex; 
        }
    }
     
   
    
    public static PacienteSala obtenerPorId(PacienteSala pPacienteSala) throws Exception {
        PacienteSala pacientesala = new PacienteSala();
        ArrayList<PacienteSala> pacienteSalas = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) { 
            String sql = obtenerSelect(pPacienteSala);
            sql += " WHERE ps.Id=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, pPacienteSala.getId());
                obtenerDatos(ps, pacienteSalas);
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        }
        catch (SQLException ex) {
            throw ex;
        }
        if (pacienteSalas.size() > 0) {
            pacientesala = pacienteSalas.get(0);
        }
        return pacientesala;
    }
    
    public static ArrayList<PacienteSala> obtenerTodos() throws Exception {
        ArrayList<PacienteSala> pacienteSalas = new ArrayList<>();
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = obtenerSelect(new PacienteSala());
            sql += agregarOrderBy(new PacienteSala());
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                obtenerDatos(ps, pacienteSalas);
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        } 
        catch (SQLException ex) {
            throw ex;
        }
        return pacienteSalas;
    }
    
    static void querySelect(PacienteSala pPacienteSala, ComunDB.utilQuery pUtilQuery) throws SQLException {
        PreparedStatement statement = pUtilQuery.getStatement();
        if (pPacienteSala.getId() > 0) {
            pUtilQuery.AgregarNumWhere(" ps.Id=? ");
            if (statement != null) { 
                statement.setInt(pUtilQuery.getNumWhere(), pPacienteSala.getId()); 
            }
        }
        
         if (pPacienteSala.getIdPaciente() > 0) {
            pUtilQuery.AgregarNumWhere(" ps.IdPaciente=? ");
            if (statement != null) {
                statement.setInt(pUtilQuery.getNumWhere(), pPacienteSala.getIdPaciente());
            }
        }
 
        if (pPacienteSala.getIdSala()> 0) {
            pUtilQuery.AgregarNumWhere(" ps.IdSala=? ");
            if (statement != null) {
                statement.setInt(pUtilQuery.getNumWhere(), pPacienteSala.getIdSala());
            }
        }         
    }
    
    public static ArrayList<PacienteSala> buscar(PacienteSala pPacienteSala) throws Exception {
        ArrayList<PacienteSala> pacienteSalas = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = obtenerSelect(pPacienteSala);
            ComunDB comundb = new ComunDB();
            ComunDB.utilQuery utilQuery = comundb.new utilQuery(sql, null, 0); 
            querySelect(pPacienteSala, utilQuery);
            sql = utilQuery.getSQL(); 
            sql += agregarOrderBy(pPacienteSala);
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0); 
                querySelect(pPacienteSala, utilQuery);
                obtenerDatos(ps, pacienteSalas);
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        }
        catch (SQLException ex) {
            throw ex;
        }
        return pacienteSalas;
    }
    
    public static ArrayList<PacienteSala> buscarIncluirRelaciones(PacienteSala pPacienteSala) throws Exception {
        ArrayList<PacienteSala> pacienteSalas = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = "SELECT ";
            if (pPacienteSala.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER) {
                sql += "TOP " + pPacienteSala.getTop_aux() + " "; 
            }
            sql += obtenerCampos();
            sql += ",";
            sql += PacienteDAL.obtenerCampos();
            sql += ",";
            sql += SalaDAL.obtenerCampos();
            sql += " FROM PacienteSala ps";
            sql += " INNER JOIN Paciente pa on (ps.IdPaciente=pa.Id)";
            sql += " INNER JOIN Sala s on (ps.IdSala=s.Id)";
            ComunDB comundb = new ComunDB();
            ComunDB.utilQuery utilQuery = comundb.new utilQuery(sql, null, 0);
            querySelect(pPacienteSala, utilQuery);
            sql = utilQuery.getSQL();
            sql += agregarOrderBy(pPacienteSala);
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0);
                querySelect(pPacienteSala, utilQuery);
                obtenerDatosIncluirRelaciones(ps, pacienteSalas);
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        } catch (SQLException ex) {
            throw ex;
        }
        return pacienteSalas;
    }
    
     
}
