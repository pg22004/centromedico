<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="centromedico.entidadesdenegocios.Historial"%>
<% Historial historial = (Historial) request.getAttribute("historial");%>

<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Detalle del Historial</title>
    </head>
    <body class="bodys">
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="contenedor container">   
            <h3>Detalle del Historial</h3>
             <div class="row">
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
                        <label class="labe" for="txtPaciente">Nombre</label>
                    </div> 
                        <div class="cajatexto input-field col l4 s12">
                        <input class="inpu"  id="txtPaciente" type="text" value="<%=historial.getPaciente().getApellido()%>" disabled>
                        <label class="labe" for="txtPaciente">Apellido</label>
                    </div> 
                </div>

                <div class="row">
                    <div class="col l12 s12">
                         <a href="Historial?accion=edit&id=<%=historial.getId()%>" class="waves-effect waves-light btn blue"><i class="material-icons right">edit</i>Ir modificar</a>            
                        <a href="Historial" class="waves-effect waves-light btn blue"><i class="material-icons right">cancel</i>Cancelar</a>                          
                    </div>
                </div>          
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />
    </body>
</html>