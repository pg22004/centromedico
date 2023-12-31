<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="centromedico.entidadesdenegocios.Sala"%>
<% Sala sala = (Sala) request.getAttribute("sala");%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Editar Sala</title>
    </head>
    <body class="bodys">
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="contenedor container">   
            <h3>Editar Sala</h3>
            <form action="Sala" method="post">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">   
                <input type="hidden" name="id" value="<%=sala.getId()%>">   
                <div class="row">
                    <div class="cajatexto input-field col l4 s12">
                        <input  class="inpu"  id="txtNombre" type="text" name="nombre" value="<%=sala.getNombre()%>" required class="validate" maxlength="30">
                        <label  class="labe" for="txtNombre">Nombre</label>
                    </div> 
                        <div class="cajatexto input-field col l4 s12">
                        <input  class="inpu"  id="txtNumeroCamas" type="number" name="numeroCamas" value="<%=sala.getNumeroCamas()%>" required class="validate" maxlength="30">
                        <label  class="labe" for="txtNumeroCamas">NumeroCamas</label>
                    </div>   
                </div>
                <div class="row">
                    <div class="col l12 s12">
                        <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">save</i>Guardar</button>
                        <a href="Sala" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
                    </div>
                </div>
            </form>          
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />
    </body>
</html>
