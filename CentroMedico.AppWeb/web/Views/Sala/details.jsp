<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="centromedico.entidadesdenegocios.Sala"%>
<% Sala sala = (Sala) request.getAttribute("sala");%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Detalles de Sala</title>
    </head>
    <body class="bodys">
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="contenedor container">   
            <h3>Detalle de Sala</h3>
            <div class="row">
                <div class="cajatexto input-field col l4 s12">
                    <input  class="inpu" disabled  id="txtNombre" type="text" value="<%=sala.getNombre()%>">
                    <label class="labe"  for="txtNombre">Nombre</label>
                </div>  
                     <div class="cajatexto input-field col l4 s12">
                    <input  class="inpu" disabled  id="txtNumeroCamas" type="text" value="<%=sala.getNumeroCamas()%>">
                    <label class="labe"  for="txtNumeroCamas">NumeroCamas</label>
                </div>  
            </div>
            <div class="row">
                <div class="col l12 s12">
                    <a href="Sala?accion=edit&id=<%=sala.getId()%>" class="waves-effect waves-light btn blue"><i class="material-icons right">edit</i>Ir modificar</a>                        
                    <a href="Sala" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
                </div>
            </div>         
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />
    </body>
</html>
