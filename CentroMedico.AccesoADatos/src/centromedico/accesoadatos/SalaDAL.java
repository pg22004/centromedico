package centromedico.accesoadatos;

import java.util.*;
import java.sql.*;
import centromedico.entidadesdenegocios.*;

public class SalaDAL{
static String obtenerCampos() {
        return "s.Id, s.nombre, s.numeroCamas,";
    }
    private static String obtenerSelect(Sala pSala) {
        String sql;
        sql = "SELECT ";
        if (pSala.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER) {
             sql += "TOP " + pSala.getTop_aux() + " ";
        }
        sql += (obtenerCampos() + " FROM Sala s");
        return sql;
    }
    private static String agregarOrderBy(Sala pSala) {
        String sql = " ORDER BY u.Id DESC";
        if (pSala.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.MYSQL) {
            sql += " LIMIT " + pSala.getTop_aux() + " ";
        }
        return sql;
    }
    public static int crear(Sala pSala) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) { 
            sql = "INSERT INTO Sala(Nombre, NumeroCamas) VALUES(?, ?)";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setString(1, pSala.getNombre());
                ps.setInt(2, pSala.getNumeroCamas());
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
    public static int modificar(Sala pSala) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) {
            sql = "UPDATE Sala SET Nombre=?, NumeroCamas = ? WHERE Id=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setString(1, pSala.getNombre());
                ps.setInt(2, pSala.getNumeroCamas());
                ps.setInt(3, pSala.getId());
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
    public static int eliminar(Sala pSala) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) {
            sql = "DELETE FROM Sala WHERE Id=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, pSala.getId());
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
    static int asignarDatosResultSet(Sala pSala, ResultSet pResultSet, int pIndex) throws Exception {
        pIndex++;
        pSala.setId(pResultSet.getInt(pIndex));
        pIndex++;
        pSala.setNombre(pResultSet.getString(pIndex));
        pIndex++;
        pSala.setNumeroCamas(pResultSet.getInt(pIndex));
        pIndex++;
        return pIndex;
    }
    private static void obtenerDatos(PreparedStatement pPS, ArrayList<Sala> pSala) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS);) {
            while (resultSet.next()) {
                Sala sala = new Sala(); 
                asignarDatosResultSet(sala, resultSet, 0);
                pSala.add(sala);
            }
            resultSet.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }
        public static Sala obtenerPorId(Sala pSala) throws Exception {
        Sala sala = new Sala();
        ArrayList<Sala> salas = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) { 
            String sql = obtenerSelect(pSala);
            sql += " WHERE c.Id=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, pSala.getId());
                obtenerDatos(ps,salas );
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        }
        catch (SQLException ex) {
            throw ex;
        }
        if (salas.size() > 0) {
            sala = salas.get(0);
        }
        return sala;
    }
    }

