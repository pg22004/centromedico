package centromedico.appweb.controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import centromedico.accesoadatos.PacienteSalaDAL;
import centromedico.accesoadatos.PacienteDAL;
import centromedico.accesoadatos.SalaDAL;

import centromedico.appweb.utils.*;
import centromedico.entidadesdenegocios.PacienteSala;
import centromedico.entidadesdenegocios.Paciente;
import centromedico.entidadesdenegocios.Sala;

@WebServlet(name = "PacienteSalaServlet", urlPatterns = {"/PacienteSala"})
public class PacienteSalaServlet extends HttpServlet {
 
    private PacienteSala obtenerPacienteSala(HttpServletRequest request) {
        String accion = Utilidad.getParameter(request, "accion", "index");
        PacienteSala pacientesala = new PacienteSala();
        if (accion.equals("create") == false) {
            pacientesala.setId(Integer.parseInt(Utilidad.getParameter(request, "id", "0")));
        }

        pacientesala.setIdPaciente(Integer.parseInt(Utilidad.getParameter(request, "idPaciente", "0")));
        pacientesala.setIdSala(Integer.parseInt(Utilidad.getParameter(request, "idSala", "0")));
        
        if (accion.equals("index")) {
            pacientesala.setTop_aux(Integer.parseInt(Utilidad.getParameter(request, "top_aux", "10")));
            pacientesala.setTop_aux(pacientesala.getTop_aux() == 0 ? Integer.MAX_VALUE : pacientesala.getTop_aux());
        }
        
        return pacientesala;
    }
    
     private void doGetRequestIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PacienteSala pacientesala = new PacienteSala();
            pacientesala.setTop_aux(10);
            ArrayList<PacienteSala> pacienteSalas = PacienteSalaDAL.buscarIncluirRelaciones(pacientesala);
            request.setAttribute("pacienteSalas", pacienteSalas);
            request.setAttribute("top_aux", pacientesala.getTop_aux());
            request.getRequestDispatcher("Views/PacienteSala/index.jsp").forward(request, response);
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    private void doPostRequestIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PacienteSala pacientesala = obtenerPacienteSala(request);
            ArrayList<PacienteSala> pacienteSalas = PacienteSalaDAL.buscarIncluirRelaciones(pacientesala);
            request.setAttribute("pacienteSalas", pacienteSalas);
            request.setAttribute("top_aux", pacientesala.getTop_aux());
            request.getRequestDispatcher("Views/PacienteSala/index.jsp").forward(request, response);
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
    
     private void doGetRequestCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("Views/PacienteSala/create.jsp").forward(request, response);
    }

    private void doPostRequestCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PacienteSala pacientesala = obtenerPacienteSala(request);
            int result = PacienteSalaDAL.crear(pacientesala);
            if (result != 0) {
                request.setAttribute("accion", "index");
                doGetRequestIndex(request, response);
            } else {
                Utilidad.enviarError("No se logro guardar el nuevo registro", request, response);
            }
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }

    }
    
     private void requestObtenerPorId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PacienteSala pacientesala = obtenerPacienteSala(request);
            PacienteSala pacientesala_result = PacienteSalaDAL.obtenerPorId(pacientesala);
            if (pacientesala_result.getId() > 0) {
                Paciente paciente = new Paciente();
                paciente.setId(pacientesala_result.getIdPaciente());
                pacientesala_result.setPaciente(PacienteDAL.obtenerPorId(paciente));
                Sala sala = new Sala();
                sala.setId(pacientesala_result.getIdSala());
                pacientesala_result.setSala(SalaDAL.obtenerPorId(sala));
                request.setAttribute("pacientesala", pacientesala_result);
            } else {
                Utilidad.enviarError("El Id:" + pacientesala_result.getId() + " no existe en la tabla de Paciente Sala", request, response);
            }
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
     
    private void doGetRequestEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        requestObtenerPorId(request, response);
        request.getRequestDispatcher("Views/PacienteSala/edit.jsp").forward(request, response);
    }

    private void doPostRequestEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PacienteSala pacientesala = obtenerPacienteSala(request);
            int result = PacienteSalaDAL.modificar(pacientesala);
            if (result != 0) {
                request.setAttribute("accion", "index");
                doGetRequestIndex(request, response);
            } else {
                Utilidad.enviarError("No se logro actualizar el registro", request, response);
            }
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    private void doGetRequestDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        requestObtenerPorId(request, response);
        request.getRequestDispatcher("Views/PacienteSala/details.jsp").forward(request, response);
    }

    private void doGetRequestDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        requestObtenerPorId(request, response);
        request.getRequestDispatcher("Views/PacienteSala/delete.jsp").forward(request, response);
    }

    private void doPostRequestDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PacienteSala pacientesala = obtenerPacienteSala(request);
            int result = PacienteSalaDAL.eliminar(pacientesala);
            if (result != 0) {
                request.setAttribute("accion", "index");
                doGetRequestIndex(request, response);
            } else {
                Utilidad.enviarError("No se logro eliminar el registro", request, response);
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