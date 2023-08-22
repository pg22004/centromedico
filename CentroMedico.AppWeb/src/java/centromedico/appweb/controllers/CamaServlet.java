package centromedico.appweb.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import centromedico.accesoadatos.CamaDAL;
import centromedico.accesoadatos.SalaDAL;
import centromedico.appweb.utils.*;
import centromedico.entidadesdenegocios.Sala;
import centromedico.entidadesdenegocios.Cama;


@WebServlet(name = "CamaServlet", urlPatterns = {"/Cama"})
public class CamaServlet extends HttpServlet {
private Cama obtenerCama(HttpServletRequest request) {
        String accion = Utilidad.getParameter(request, "accion", "index");
        Cama cama = new Cama();
        if (accion.equals("create") == false) {
            cama.setId(Integer.parseInt(Utilidad.getParameter(request, "id", "0")));
        }
       
        
        cama.setIdSala(Integer.parseInt(Utilidad.getParameter(request, "idSala", "0")));
        
        if (accion.equals("index")) {
            cama.setTop_aux(Integer.parseInt(Utilidad.getParameter(request, "top_aux", "10")));
            cama.setTop_aux(cama.getTop_aux() == 0 ? Integer.MAX_VALUE : cama.getTop_aux());
        }
        
        return cama;
    }

    private void doGetRequestIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Cama cama = new Cama();
            cama.setTop_aux(10);
            ArrayList<Cama> camas = CamaDAL.buscarIncluirSala(cama);
            request.setAttribute("Cama", camas);
            request.setAttribute("top_aux", cama.getTop_aux());
            request.getRequestDispatcher("Views/Cama/index.jsp").forward(request, response);
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    private void doPostRequestIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Cama cama = obtenerCama(request);
            ArrayList<Cama>camas = CamaDAL.buscarIncluirSala(cama);
            request.setAttribute("camas", camas);
            request.setAttribute("top_aux", cama.getTop_aux());
            request.getRequestDispatcher("Views/Cama/index.jsp").forward(request, response);
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    private void doGetRequestCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("Views/Cama/create.jsp").forward(request, response);
    }

    private void doPostRequestCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Cama cama = obtenerCama(request);
            int result = CamaDAL.crear(cama);
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
            Cama cama= obtenerCama(request);
            Cama cama_result = CamaDAL.obtenerPorId(cama);
            if (cama_result.getId() > 0) {
               Sala sala = new Sala();
                sala.setId(cama_result.getIdSala());
                cama_result.setSala(SalaDAL.obtenerPorId(sala));
                request.setAttribute("cama", cama_result);
            } else {
                Utilidad.enviarError("El Id:" + cama_result.getId() + " no existe en la tabla de Camas", request, response);
            }
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    private void doGetRequestEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        requestObtenerPorId(request, response);
        request.getRequestDispatcher("Views/Cama/edit.jsp").forward(request, response);
    }

    private void doPostRequestEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Cama cama = obtenerCama(request);
            int result = CamaDAL.modificar(cama);
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
        request.getRequestDispatcher("Views/Cama/details.jsp").forward(request, response);
    }

    private void doGetRequestDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        requestObtenerPorId(request, response);
        request.getRequestDispatcher("Views/Cama/delete.jsp").forward(request, response);
    }

    private void doPostRequestDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Cama cama = obtenerCama(request);
            int result = CamaDAL.eliminar(cama);
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
