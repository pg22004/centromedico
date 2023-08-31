<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="centromedico.entidadesdenegocios.Historial"%>
<% Historial historial = (Historial) request.getAttribute("historial");%>

<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Eliminar Historial</title>
    </head>
    <body class="bodys">
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="contenedor container">   
            <h3>Eliminar Historial</h3>
            <form action="Historial" method="post">  
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>"> 
                <input type="hidden" name="id" value="<%=historial.getId()%>">  
                <div class="row">
                    <div class="cajatexto confirmhistorial col l4 s12">
                        <label class="labe labels-icons" for="txtFechaEntrada">Fecha Entrada</label>
                        <input  class="inpu"  id="txtFechaEntrada" type="date" value="<%=historial.getFechaEntrada()%>" disabled>
                    </div>                       
                    <div class="cajatexto input-field col l4 s12">
                        <input  class="inpu"  id="txtDetalleRegistro" type="text" value="<%=historial.getDetalleRegistro()%>" disabled>
                        <label class="labe" for="txtDetalleRegistro">Detalle Registro</label>
                    </div> 
                    <div class="cajatexto input-field col l4 s12">
                        <input  class="inpu" id="txtPaciente" type="text" value="<%=historial.getPaciente().getNombre()%>" disabled>
                        <label class="labe" for="txtPaciente">Paciente</label>
                    </div> 
                </div>
                <div class="row">
                    <div class="col l12 s12">
                        <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">delete</i>Eliminar</button>
                        <a href="Historial" class="waves-effect waves-light btn blue"><i class="material-icons right">cancel</i>Cancelar</a>                          
                    </div>
                </div>
            </form>          
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />
    </body>
</html>
