<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="centromedico.entidadesdenegocios.Cama"%>
<% Cama cama = (Cama) request.getAttribute("cama");%>

<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Detalle de la Cama</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Detalle de la Cama</h5>
             <div class="row">
                <div class="row">
                    <div class="input-field col l4 s12">
                        <input  id="txtIdSala" type="text" value="<%=cama.getIdSala()%>" disabled>
                        <label for="txtIdSala">IdSala</label>
                    </div>                       
                    
                    <div class="input-field col l4 s12">
                        <input id="txtSala" type="text" value="<%=cama.getSala().getIdSala()%>" disabled>
                        <label for="txtSala"></label>
                    </div> 
                </div>

                <div class="row">
                    <div class="col l12 s12">
                         <a href="Cama?accion=edit&id=<%=cama.getId()%>" class="waves-effect waves-light btn blue"><i class="material-icons right">edit</i>Ir modificar</a>            
                        <a href="Cama" class="waves-effect waves-light btn blue"><i class="material-icons right">cancel</i>Cancelar</a>                          
                    </div>
                </div>          
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />
    </body>
</html>
