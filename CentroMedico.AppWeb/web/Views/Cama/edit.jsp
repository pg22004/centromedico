<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="centromedico.entidadesdenegocios.Cama"%>
<% Cama cama = (Cama) request.getAttribute("cama");%>

<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Editar Cama</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Editar Cama</h5>
            <form action="Cama" method="post" onsubmit="return  validarFormulario()">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>"> 
                <input type="hidden" name="id" value="<%=cama.getId()%>">  
                <div class="row">
                    <div class="input-field col l4 s12">   
                        <jsp:include page="/Views/Sala/select.jsp">                           
                            <jsp:param name="id" value="<%=cama.getIdSala() %>" />  
                        </jsp:include>  
                        <span id="slSala_error" style="color:red" class="helper-text"></span>
                    </div>
                </div>

                <div class="row">
                    <div class="col l12 s12">
                        <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">save</i>Guardar</button>
                        <a href="Cama" class="waves-effect waves-light btn blue"><i class="material-icons right">cancel</i>Cancelar</a>                          
                    </div>
                </div>
            </form>          
        </main>
                        
        <jsp:include page="/Views/Shared/footerBody.jsp" />   
        <script>
            function validarFormulario() {
                var result = true;                
                var slSala= document.getElementById("slSala");
                var slSala_error = document.getElementById("slSala_error");
                if (slSala.value == 0) {
                    slSala_error.innerHTML = "La Sala es obligatoria";
                    result = false;
                } else {
                    slSala_error.innerHTML = "";
                }

                return result;
            }
        </script>
    </body>
</html>
