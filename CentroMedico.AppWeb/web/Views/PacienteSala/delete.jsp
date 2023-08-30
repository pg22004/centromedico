<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="centromedico.entidadesdenegocios.PacienteSala"%>
<% PacienteSala pacientesala = (PacienteSala) request.getAttribute("pacientesala");%>

<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Eliminar PacienteSala</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Eliminar Paciente Sala</h5>
            <form action="PacienteSala" method="post">  
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>"> 
                <input type="hidden" name="id" value="<%=pacientesala.getId()%>">  
                <div class="row">                                         
                    <div class="input-field col l4 s12">
                        <input id="txtPaciente" type="text" value="<%=pacientesala.getPaciente().getNombre()%>" disabled>
                        <label for="txtPaciente">Paciente</label>
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
                        <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">delete</i>Eliminar</button>
                        <a href="PacienteSala" class="waves-effect waves-light btn blue"><i class="material-icons right">cancel</i>Cancelar</a>                          
                    </div>
                </div>
            </form>          
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />
    </body>
</html>