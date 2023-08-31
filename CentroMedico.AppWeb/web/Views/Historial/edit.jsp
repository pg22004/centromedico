<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="centromedico.entidadesdenegocios.Historial"%>
<% Historial historial = (Historial) request.getAttribute("historial");%>

<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Editar Historial</title>
    </head>
    <body class="bodys">
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="contenedor container">   
            <h3>Editar Historial</h3>
            <form action="Historial" method="post" onsubmit="return  validarFormulario()">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>"> 
                <input type="hidden" name="id" value="<%=historial.getId()%>">  
                <div class="row">                      
                    <div class="cajatexto input-field col l4 s12">
                        <input  class="inpu"  id="txtDetalleRegistro" type="text" name="detalleRegistro" value="<%=historial.getDetalleRegistro()%>" required class="validate" maxlength="100">
                        <label class="labe" for="txtDetalleRegistro">Detalle Registro</label>
                    </div> 
                    
                    <div class="cajatexto input-field col l4 s12">   
                        <jsp:include page="/Views/Paciente/select.jsp">                           
                            <jsp:param name="id" value="<%=historial.getIdPaciente() %>" />  
                        </jsp:include>  
                        <span id="slContacto_error" style="color:red" class="helper-text"></span>
                    </div>
                    <div class="cajatexto confirmhistorial col l4 s12">
                        <label class="labe labels-icons" for="txtFechaEntrada">Fecha Entrada</label>
                        <input  class="inpu"  id="txtFechaEntrada" type="date" name="fechaEntrada" value="<%=historial.getFechaEntrada()%>" required class="validate" maxlength="50">
                    </div> 
                </div>

                <div class="row">
                    <div class="col l12 s12">
                        <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">save</i>Guardar</button>
                        <a href="Historial" class="waves-effect waves-light btn blue"><i class="material-icons right">cancel</i>Cancelar</a>                          
                    </div>
                </div>
            </form>          
        </main>
                        
        <jsp:include page="/Views/Shared/footerBody.jsp" />   
        <script>
            function validarFormulario() {
                var result = true;                
                var slPaciente = document.getElementById("slPaciente");
                var slPaciente_error = document.getElementById("slPaciente_error");
                if (slPaciente.value == 0) {
                    slPaciente_error.innerHTML = "El Paciente es obligatorio";
                    result = false;
                } else {
                    slPaciente_error.innerHTML = "";
                }

                return result;
            }
        </script>
    </body>
</html>
