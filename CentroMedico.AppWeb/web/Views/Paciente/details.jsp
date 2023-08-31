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
    <body class="bodys">
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="contenedor container">   
            <h3>Detalles del Paciente</h3>
            <div class="row">
                <div class="cajatexto input-field col l4 s12">
                    <input  class="inpu" disabled  id="txtNombre" type="text" value="<%=paciente.getNombre()%>">
                    <label class="labe"  for="txtNombre">Nombre</label>
                </div>
                <div class="cajatexto input-field col l4 s12">
                    <input  class="inpu" disabled  id="txtApellido" type="text" value="<%=paciente.getApellido()%>">
                    <label  class="labe" for="txtApellido">Apellido</label>
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