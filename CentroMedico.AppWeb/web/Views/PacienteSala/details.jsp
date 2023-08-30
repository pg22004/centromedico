<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="centromedico.entidadesdenegocios.PacienteSala"%>
<% PacienteSala pacientesala = (PacienteSala) request.getAttribute("pacientesala");%>

<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Detalle de PacienteSala</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Detalle de Paciente Sala</h5>
             <div class="row">
                <div class="row">
                     
                    <div class="input-field col l4 s12">
                        <input id="txtPaciente" type="text" value="<%=pacientesala.getPaciente().getNombre()%>" disabled>
                        <label for="txtPaciente">Nombre</label>
                    </div>  
                    <div class="input-field col l4 s12">
                        <input id="txtPaciente" type="text" value="<%=pacientesala.getPaciente().getApellido()%>" disabled>
                        <label for="txtPaciente">Apellido</label>
                    </div>     
                    <div class="input-field col l4 s12">
                        <input id="txtIdSala" type="text" value="<%=pacientesala.getSala().getNombre()%>" disabled>
                        <label for="txtIdSala">Sala</label>
                    </div>  
                        
                     <div class="input-field col l4 s12">
                        <input  id="txtFechaEntrada" type="date" value="<%=pacientesala.getFecha()%>" disabled>
                        <label for="txtFechaEntrada">Fecha Entrada</label>
                    </div>  
                </div>

                <div class="row">
                    <div class="col l12 s12">
                         <a href="PacienteSala?accion=edit&id=<%=pacientesala.getId()%>" class="waves-effect waves-light btn blue"><i class="material-icons right">edit</i>Ir modificar</a>            
                        <a href="PacienteSala" class="waves-effect waves-light btn blue"><i class="material-icons right">cancel</i>Cancelar</a>                          
                    </div>
                </div>          
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />
    </body>
</html>
