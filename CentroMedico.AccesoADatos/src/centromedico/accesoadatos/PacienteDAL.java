package centromedico.accesoadatos;

import java.util.*;
import java.sql.*;
import centromedico.entidadesdenegocios.*;
import java.time.LocalDate;

public class PacienteDAL {
    static String obtenerCampos() {
        return "p.Id, p.Nombre, p.Apellido, p.FechaRegistro";
    }
    
    private static String obtenerSelect(Paciente pPaciente) {
        String sql;
        sql = "SELECT ";
        if (pPaciente.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER) {            
            sql += "TOP " + pPaciente.getTop_aux() + " ";
        }
        sql += (obtenerCampos() + " FROM Paciente p");
        return sql;
    }
    
     private static String agregarOrderBy(Paciente pPaciente) {
        String sql = " ORDER BY c.Id DESC";
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
            sql = "UPDATE Paciente SET Nombre=?, Apellido = ?, FechaRegistro = ? WHERE Id=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setString(1, pPaciente.getNombre());
                ps.setString(2, pPaciente.getApellido());
                ps.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
                ps.setInt(4, pPaciente.getId());
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
}
