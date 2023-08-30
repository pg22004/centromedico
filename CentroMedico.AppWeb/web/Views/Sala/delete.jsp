<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="centromedico.entidadesdenegocios.Sala"%>
<% Sala sala = (Sala) request.getAttribute("sala");%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Eliminar Sala</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Eliminar Sala</h5>          
            <form action="Sala" method="post">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">   
                <input type="hidden" name="id" value="<%=sala.getId()%>">   
                <div class="row">
                    <div class="input-field col l4 s12">
                    <input disabled  id="txtNombre" type="text" value="<%=sala.getNombre()%>">
                    <label for="txtNombre">Nombre</label>
                </div>  
                    <div class="input-field col l4 s12">
                    <input disabled  id="txtNumeroCamas" type="number" value="<%=sala.getNumeroCamas()%>">
                    <label for="txtNumeroCamas">NumeroCamas</label>
                </div>        
                </div>
                <div class="row">
                    <div class="col l12 s12">
                        <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">delete</i>Eliminar</button>
                        <a href="Sala" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
                    </div>
                </div>
            </form>          
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />
    </body>
</html>
