package centromedico.appweb.controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import centromedico.accesoadatos.PacienteDAL;
import centromedico.entidadesdenegocios.Paciente;
import centromedico.appweb.utils.*;
import java.time.LocalDate;


@WebServlet(name = "PacienteServlet", urlPatterns = {"/Paciente"})
public class PacienteServlet extends HttpServlet {

    private Paciente obtenerPaciente(HttpServletRequest request) {
        String accion = Utilidad.getParameter(request, "accion", "index");
        Paciente paciente = new Paciente();
        if (accion.equals("create") == false) {
            paciente.setId(Integer.parseInt(Utilidad.getParameter(request, "id", "0")));
        }

        paciente.setNombre(Utilidad.getParameter(request, "nombre", ""));
        paciente.setApellido(Utilidad.getParameter(request, "apellido", ""));
        paciente.setFechaRegistro(LocalDate.parse(Utilidad.getParameter(request, "fechaRegistro", "")));

        
        if (accion.equals("index")) {
            paciente.setTop_aux(Integer.parseInt(Utilidad.getParameter(request, "top_aux", "10")));
            paciente.setTop_aux(paciente.getTop_aux() == 0 ? Integer.MAX_VALUE : paciente.getTop_aux());
        }
        
        return paciente;
    }
    
      private void doGetRequestIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Paciente paciente = new Paciente();
            paciente.setTop_aux(10);
            ArrayList<Paciente> pacientes = PacienteDAL.buscar(paciente);
            request.setAttribute("pacientes", pacientes);
            request.setAttribute("top_aux", paciente.getTop_aux());             
            request.getRequestDispatcher("Views/Paciente/index.jsp").forward(request, response);
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
    
      
       private void doPostRequestIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Paciente paciente = obtenerPaciente(request);
            ArrayList<Paciente> pacientes = PacienteDAL.buscar(paciente);
            request.setAttribute("pacientes", pacientes);
            request.setAttribute("top_aux", paciente.getTop_aux());
            request.getRequestDispatcher("Views/Paciente/index.jsp").forward(request, response);
        } catch (Exception ex) { 
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
       
       private void doGetRequestCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("Views/Paciente/create.jsp").forward(request, response);
    }
    
    private void doPostRequestCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Paciente paciente = obtenerPaciente(request);
            int result = PacienteDAL.crear(paciente);
            if (result != 0) {
                request.setAttribute("accion", "index");
                doGetRequestIndex(request, response);
            } else {
                Utilidad.enviarError("No se logro registrar un nuevo registro", request, response);
            }
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
    
     private void requestObtenerPorId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Paciente paciente = obtenerPaciente(request);
            Paciente paciente_result = PacienteDAL.obtenerPorId(paciente);
            if (paciente_result.getId() > 0) {
                request.setAttribute("paciente", paciente_result);
            } else {
                Utilidad.enviarError("El Id:" + paciente.getId() + " no existe en la tabla de Pacientes", request, response);
            }
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
     
      private void doGetRequestEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        requestObtenerPorId(request, response);
        request.getRequestDispatcher("Views/Paciente/edit.jsp").forward(request, response);
    }
      
      private void doPostRequestEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Paciente paciente = obtenerPaciente(request);
            int result = PacienteDAL.modificar(paciente);
            if (result != 0) {
                request.setAttribute("accion", "index");
                doGetRequestIndex(request, response);
            } else {
                Utilidad.enviarError("No se logro actualizar el registro", request, response);
            }
        } catch (Exception ex) {
            // Enviar al jsp de error si hay un Exception
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
    
    private void doGetRequestDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        requestObtenerPorId(request, response);
        request.getRequestDispatcher("Views/Paciente/details.jsp").forward(request, response);
    }
    
    private void doGetRequestDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        requestObtenerPorId(request, response);
        request.getRequestDispatcher("Views/Paciente/delete.jsp").forward(request, response);
    }
    
    private void doPostRequestDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Paciente paciente = obtenerPaciente(request);
            int result = PacienteDAL.eliminar(paciente);
            if (result != 0) {
                request.setAttribute("accion", "index");
                doGetRequestIndex(request, response);
            } else {
                Utilidad.enviarError("No se logró eliminar el registro", request, response);
            }
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SessionUser.authorize(request, response, () -> {
            String accion = Utilidad.getParameter(request, "accion", "index");
            switch (accion) {
                case "index":
                    request.setAttribute("accion", accion);
                    doGetRequestIndex(request, response);
                    break;
                case "create":
                    request.setAttribute("accion", accion);
                    doGetRequestCreate(request, response);
                    break;
                case "edit":
                    request.setAttribute("accion", accion);
                    doGetRequestEdit(request, response);
                    break;
                case "delete":
                    request.setAttribute("accion", accion);
                    doGetRequestDelete(request, response);
                    break;
                case "details":
                    request.setAttribute("accion", accion);
                    doGetRequestDetails(request, response);
                    break;
                default:
                    request.setAttribute("accion", accion);
                    doGetRequestIndex(request, response);
            }
        });
    }
    
     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SessionUser.authorize(request, response, () -> {
            String accion = Utilidad.getParameter(request, "accion", "index");
            switch (accion) {
                case "index":
                    request.setAttribute("accion", accion);
                    doPostRequestIndex(request, response);
                    break;
                case "create":
                    request.setAttribute("accion", accion);
                    doPostRequestCreate(request, response);
                    break;
                case "edit":
                    request.setAttribute("accion", accion);
                    doPostRequestEdit(request, response);
                    break;
                case "delete":
                    request.setAttribute("accion", accion);
                    doPostRequestDelete(request, response);
                    break;
                default:
                    request.setAttribute("accion", accion);
                    doGetRequestIndex(request, response);
            }
        });
    }
// </editor-fold>
}
