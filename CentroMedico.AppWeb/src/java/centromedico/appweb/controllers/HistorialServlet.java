package centromedico.appweb.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import centromedico.accesoadatos.PacienteDAL;
import centromedico.accesoadatos.HistorialDAL;
import java.time.LocalDate;
import centromedico.appweb.utils.*;
import centromedico.entidadesdenegocios.Paciente;
import centromedico.entidadesdenegocios.Historial;

@WebServlet(name = "HistorialServlet", urlPatterns = {"/Historial"})
public class HistorialServlet extends HttpServlet {

    private Historial obtenerHistorial(HttpServletRequest request) {
        String accion = Utilidad.getParameter(request, "accion", "index");
        Historial historial = new Historial();
        if (accion.equals("create") == false) {
            historial.setId(Integer.parseInt(Utilidad.getParameter(request, "id", "0")));
        }
        historial.setFechaEntrada(LocalDate.parse(Utilidad.getParameter(request, "fechaEntrada", "")));
        historial.setDetalleRegistro(Utilidad.getParameter(request, "detalleRegistro", ""));
        historial.setIdPaciente(Integer.parseInt(Utilidad.getParameter(request, "idPaciente", "0")));
        
        if (accion.equals("index")) {
            historial.setTop_aux(Integer.parseInt(Utilidad.getParameter(request, "top_aux", "10")));
            historial.setTop_aux(historial.getTop_aux() == 0 ? Integer.MAX_VALUE : historial.getTop_aux());
        }
        
        return historial;
    }

    private void doGetRequestIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Historial historial = new Historial();
            historial.setTop_aux(10);
            ArrayList<Historial> historiales = HistorialDAL.buscarIncluirPaciente(historial);
            request.setAttribute("historiales", historiales);
            request.setAttribute("top_aux", historial.getTop_aux());
            request.getRequestDispatcher("Views/Historial/index.jsp").forward(request, response);
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    private void doPostRequestIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Historial historial = obtenerHistorial(request);
            ArrayList<Historial> historiales = HistorialDAL.buscarIncluirPaciente(historial);
            request.setAttribute("historiales", historiales);
            request.setAttribute("top_aux", historial.getTop_aux());
            request.getRequestDispatcher("Views/Historial/index.jsp").forward(request, response);
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    private void doGetRequestCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("Views/Historial/create.jsp").forward(request, response);
    }

    private void doPostRequestCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Historial historial = obtenerHistorial(request);
            int result = HistorialDAL.crear(historial);
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
            Historial historial = obtenerHistorial(request);
            Historial historial_result = HistorialDAL.obtenerPorId(historial);
            if (historial_result.getId() > 0) {
                Paciente paciente = new Paciente();
                paciente.setId(historial_result.getIdPaciente());
                historial_result.setPaciente(PacienteDAL.obtenerPorId(paciente));
                request.setAttribute("historial", historial_result);
            } else {
                Utilidad.enviarError("El Id:" + historial_result.getId() + " no existe en la tabla de Historial", request, response);
            }
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    private void doGetRequestEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        requestObtenerPorId(request, response);
        request.getRequestDispatcher("Views/Historial/edit.jsp").forward(request, response);
    }

    private void doPostRequestEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Historial historial = obtenerHistorial(request);
            int result = HistorialDAL.modificar(historial);
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
        request.getRequestDispatcher("Views/Historial/details.jsp").forward(request, response);
    }

    private void doGetRequestDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        requestObtenerPorId(request, response);
        request.getRequestDispatcher("Views/Historial/delete.jsp").forward(request, response);
    }

    private void doPostRequestDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Historial historial = obtenerHistorial(request);
            int result = HistorialDAL.eliminar(historial);
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
