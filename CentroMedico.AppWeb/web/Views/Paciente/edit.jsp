<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="centromedico.entidadesdenegocios.Paciente"%>
<% Paciente paciente = (Paciente) request.getAttribute("paciente");%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Editar Paciente</title>
    </head>
    <body class="bodys">
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="contenedor container">   
            <h3>Editar Paciente</h3>
            <form action="Paciente" method="post">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">   
                <input type="hidden" name="id" value="<%=paciente.getId()%>">   
                <div class="row">
                    <div class="cajatexto input-field col l4 s12">
                        <input  class="inpu"  id="txtNombre" type="text" name="nombre" value="<%=paciente.getNombre()%>" required class="validate" maxlength="50">
                        <label class="labe"  for="txtNombre">Nombre</label>
                    </div>
                    <div class="cajatexto input-field col l4 s12">
                        <input  class="inpu"  id="txtApellido" type="text" name="apellido" value="<%=paciente.getApellido()%>" required class="validate" maxlength="100">
                        <label  class="labe" for="txtApellido">Apellido</label>
                    </div>
                     
                    
                </div>
                <div class="row">
                    <div class="col l12 s12">
                        <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">save</i>Guardar</button>
                        <a href="Paciente" class="waves-effect waves-light btn blue"><i class="material-icons right">cancel</i>Cancelar</a>                          
                    </div>
                </div>
            </form>          
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />
    </body>
</html>