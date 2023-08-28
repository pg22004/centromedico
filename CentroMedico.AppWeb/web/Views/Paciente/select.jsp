<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="centromedico.entidadesdenegocios.Paciente"%>
<%@page import="centromedico.entidadesdenegocios.PacienteDAL"%>
<%@page import="java.util.ArrayList"%>

<% ArrayList<Paciente> pacientes = PacienteDAL.obtenerTodos();
    int id = Integer.parseInt(request.getParameter("id"));
%>
<select id="slPaciente" name="idPaciente">
    <option <%=(id == 0) ? "selected" : ""%>  value="0">SELECCIONAR</option>
    <% for (Paciente paciente : pacientes) {%>
        <option <%=(id == paciente.getId()) ? "selected" : "" %>  value="<%=paciente.getId()%>"><%= paciente.getNombre()%>
            <%= paciente.getApellido()%><%= paciente.getFechaRegistro()%></option>
    <%}%>
</select>
<label for="idPaciente">Paciente</label>
