<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="centromedico.entidadesdenegocios.Paciente"%>
<% Paciente paciente = (Paciente) request.getAttribute("paciente");%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Detalles del Paciente</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Detalles del Paciente</h5>
            <div class="row">
                <div class="input-field col l4 s12">
                    <input disabled  id="txtNombre" type="text" value="<%=paciente.getNombre()%>">
                    <label for="txtNombre">Nombre</label>
                </div>
                <div class="input-field col l4 s12">
                    <input disabled  id="txtApellido" type="text" value="<%=paciente.getApellido()%>">
                    <label for="txtApellido">Apellido</label>
                </div>
                <div class="input-field col l4 s12">
                    <input disabled  id="txtFechaRegistro" type="date" value="<%=paciente.getFechaRegistro()%>">
                    <label for="txtFechaRegistro">Fecha de registro</label>
                </div>
            </div>
            <div class="row">
                <div class="col l12 s12">
                    <a href="Paciente?accion=edit&id=<%=paciente.getId()%>" class="waves-effect waves-light btn blue"><i class="material-icons right">edit</i>Ir modificar</a>                        
                    <a href="Paciente" class="waves-effect waves-light btn blue"><i class="material-icons right">cancel</i>Cancelar</a>                          
                </div>
            </div>         
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />
    </body>
</html>