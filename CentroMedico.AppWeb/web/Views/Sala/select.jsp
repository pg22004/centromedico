<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="centromedico.entidadesdenegocios.Sala"%>
<%@page import="centromedico.accesoadatos.SalaDAL"%>
<%@page import="java.util.ArrayList"%>

<% ArrayList<Sala> salas = SalaDAL.obtenerTodos();
    int id = Integer.parseInt(request.getParameter("id"));
%>
<select id="slSala" name="idSala">
    <option <%=(id == 0) ? "selected" : ""%>  value="0">SELECCIONAR</option>
    <% for (Sala sala : salas) {%>
     <option <%=(id == sala.getId()) ? "selected" : "" %>  value="<%=sala.getId()%>"><%= sala.getNombre()%></option>
    <%}%>
</select>
<label for="idSala">Sala</label>
