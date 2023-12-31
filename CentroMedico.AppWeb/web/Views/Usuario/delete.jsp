<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="centromedico.entidadesdenegocios.Usuario"%>
<% Usuario usuario = (Usuario) request.getAttribute("usuario");%>

<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Eliminar Usuario</title>
    </head>
    <body class="bodys">
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="contenedor container">   
            <h3>Eliminar Usuario</h3>
            <form action="Usuario" method="post">  
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>"> 
                <input type="hidden" name="id" value="<%=usuario.getId()%>">  
                <div class="row">
                    <div class="cajatexto input-field col l4 s12">
                        <input  class="inpu" id="txtNombre" type="text" value="<%=usuario.getNombre()%>" disabled>
                        <label class="labe" for="txtNombre">Nombre</label>
                    </div>                       
                    <div class="cajatexto input-field col l4 s12">
                        <input  class="inpu" id="txtApellido" type="text" value="<%=usuario.getApellido()%>" disabled>
                        <label class="labe" for="txtApellido">Apellido</label>
                    </div> 
                    <div class="cajatexto input-field col l4 s12">
                        <input  class="inpu" id="txtLogin" type="text" value="<%=usuario.getLogin()%>" disabled>
                        <label class="labe" for="txtLogin">Login</label>
                    </div>                     
                    <div class="cajatexto input-field col l4 s12">   
                        <select id="slEstatus" name="estatus" disabled>
                            <option value="0" <%=(usuario.getEstatus() == 10) ? "selected" : ""%>>SELECCIONAR</option>
                            <option value="<%=Usuario.EstatusUsuario.ACTIVO%>"  <%=(usuario.getEstatus() == Usuario.EstatusUsuario.ACTIVO) ? "selected" : ""%>>ACTIVO</option>
                            <option value="<%=Usuario.EstatusUsuario.INACTIVO%>"  <%=(usuario.getEstatus() == Usuario.EstatusUsuario.INACTIVO) ? "selected" : ""%>>INACTIVO</option>
                        </select>       
                        <label for="slEstatus">Estatus</label>                       
                    </div>
                    <div class="cajatexto input-field col l4 s12">
                        <input id="txtRol" type="text" value="<%=usuario.getRol().getNombre()%>" disabled>
                        <label for="txtRol">Rol</label>
                    </div> 
                </div>
                <div class="row">
                    <div class="col l12 s12">
                        <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">delete</i>Eliminar</button>
                        <a href="Usuario" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
                    </div>
                </div>
            </form>          
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />         
    </body>
</html>
