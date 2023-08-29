package centromedico.accesoadatos;

import java.util.*;
import java.sql.*;
import centromedico.entidadesdenegocios.*;

public class CamaDAL {
    
    static String obtenerCampos() {
        return "ca.Id, ca.IdSala";
    }
    
    private static String obtenerSelect(Cama pCama) {
        String sql;
        sql = "SELECT ";
        if (pCama.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER) {            
            sql += "TOP " + pCama.getTop_aux() + " ";
        }
        sql += (obtenerCampos() + " FROM Cama ca");
        return sql;
    }
    
    private static String agregarOrderBy(Cama pCama) {
        String sql = " ORDER BY ca.Id DESC";
        if (pCama.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.MYSQL) {
            sql += " LIMIT " + pCama.getTop_aux() + " ";
        }
        return sql;
    }
    
    public static int crear(Cama pCama) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) { 
            sql = "INSERT INTO Cama(IdSala) VALUES(?)";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, pCama.getIdSala());
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
    
    public static int modificar(Cama pCama) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) {
            sql = "UPDATE Cama SET IdSala=? WHERE Id=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, pCama.getIdSala());
                ps.setInt(2, pCama.getId());
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
    
    public static int eliminar(Cama pCama) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) {
            sql = "DELETE FROM Cama WHERE Id=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, pCama.getId());
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
    
    static int asignarDatosResultSet(Cama pCama, ResultSet pResultSet, int pIndex) throws Exception {
        pIndex++;
        pCama.setId(pResultSet.getInt(pIndex));
        pIndex++;
        pCama.setIdSala(pResultSet.getInt(pIndex));
        return pIndex;
    }
    
    private static void obtenerDatos(PreparedStatement pPS, ArrayList<Cama> pCamas) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS);) {
            while (resultSet.next()) {
                Cama cama = new Cama(); 
                asignarDatosResultSet(cama, resultSet, 0);
                pCamas.add(cama);
            }
            resultSet.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }
    
    private static void obtenerDatosIncluirSala(PreparedStatement pPS, ArrayList<Cama> pCamas) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS);) {
            HashMap<Integer, Sala> salaMap = new HashMap(); 
            while (resultSet.next()) {
                Cama cama = new Cama();
                int index = asignarDatosResultSet(cama, resultSet, 0);
                if (salaMap.containsKey(cama.getIdSala()) == false) {
                    Sala sala = new Sala();
                    SalaDAL.asignarDatosResultSet(sala, resultSet, index);
                    salaMap.put(sala.getId(), sala); 
                    cama.setSala(sala); 
                } else {
                    cama.setSala(salaMap.get(cama.getIdSala())); 
                }
                pCamas.add(cama); 
            }
            resultSet.close();
        } catch (SQLException ex) {
            throw ex; 
        }
    }
    
    public static Cama obtenerPorId(Cama pCama) throws Exception {
        Cama cama = new Cama();
        ArrayList<Cama> camas = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = obtenerSelect(pCama);
            sql += " WHERE ca.Id=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, pCama.getId());
                obtenerDatos(ps, camas);
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        }
        catch (SQLException ex) {
            throw ex;
        }
        if (camas.size() > 0) {
            cama = camas.get(0);
        }
        return cama;
    }
    
    public static ArrayList<Cama> obtenerTodos() throws Exception {
        ArrayList<Cama> camas = new ArrayList<>();
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = obtenerSelect(new Cama()); 
            sql += agregarOrderBy(new Cama());
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                obtenerDatos(ps, camas);
                ps.close();
            } catch (SQLException ex) {
                throw ex; 
            }
            conn.close();
        }
        catch (SQLException ex) {
            throw ex;
        }
        return camas;
    }
    
    static void querySelect(Cama pCama, ComunDB.utilQuery pUtilQuery) throws SQLException {
        PreparedStatement statement = pUtilQuery.getStatement();
        if (pCama.getId() > 0) {
            pUtilQuery.AgregarNumWhere(" ca.Id=? ");
            if (statement != null) {
                statement.setInt(pUtilQuery.getNumWhere(), pCama.getId());
            }
        }

        if (pCama.getIdSala()> 0) {
            pUtilQuery.AgregarNumWhere(" ca.IdSala=? ");
            if (statement != null) {
                statement.setInt(pUtilQuery.getNumWhere(), pCama.getIdSala());
            }
        } 
    }
    
    public static ArrayList<Cama> buscar(Cama pCama) throws Exception {
        ArrayList<Cama> camas = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = obtenerSelect(pCama);
            ComunDB comundb = new ComunDB();
            ComunDB.utilQuery utilQuery = comundb.new utilQuery(sql, null, 0);
            querySelect(pCama, utilQuery);
            sql = utilQuery.getSQL();
            sql += agregarOrderBy(pCama);
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0);
                querySelect(pCama, utilQuery);
                obtenerDatos(ps, camas);
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        } 
        catch (SQLException ex) {
            throw ex;
        }
        return camas;
    }
    
    public static ArrayList<Cama> buscarIncluirSala(Cama pCama) throws Exception {
        ArrayList<Cama> camas = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = "SELECT ";
            if (pCama.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER) {
                sql += "TOP " + pCama.getTop_aux() + " "; 
            }
            sql += obtenerCampos();
            sql += ",";
            sql += SalaDAL.obtenerCampos();
            sql += " FROM Cama ca";
            sql += " JOIN Sala s on (ca.IdSala=s.Id)";
            ComunDB comundb = new ComunDB();
            ComunDB.utilQuery utilQuery = comundb.new utilQuery(sql, null, 0);
            querySelect(pCama, utilQuery);
            sql = utilQuery.getSQL();
            sql += agregarOrderBy(pCama);
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0);
                querySelect(pCama, utilQuery);
                obtenerDatosIncluirSala(ps, camas);
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        } catch (SQLException ex) {
            throw ex;
        }
        return camas;
    }
}
