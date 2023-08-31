<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="centromedico.entidadesdenegocios.Usuario"%>
<% Usuario usuario = (Usuario) request.getAttribute("usuario");%>

<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Cambiar password</title>
    </head>
    <body class="bodys">
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="contenedor container">   
            <h3>Cambiar Contraseña</h3>
            <form action="Usuario" method="post" onsubmit="return  validarFormulario()">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>"> 
                <input type="hidden" name="id" value="<%=usuario.getId()%>">  
                <div class="row">                   
                    <div class="cajatexto input-field col l4 s12">
                        <input  class="inpu" id="txtLogin" type="text" name="login" value="<%=usuario.getLogin()%>" readonly>
                        <label class="labe" for="txtLogin">Login</label>
                    </div>  
                    <div class="cajatexto input-field col l4 s12">
                        <input  class="inpu" id="txtPasswordActual" type="password" name="passwordActual" required class="validate" minlength="5" maxlength="32">
                        <label class="labe" for="txtPasswordActual">Password actual</label>
                    </div> 
                </div>
                <div class="row">
                    <div class="cajatexto input-field col l4 s12">
                        <input  class="inpu" id="txtPassword" type="password" name="password" required class="validate" minlength="5" maxlength="32">
                        <label class="labe" for="txtPassword">Password</label>
                    </div> 
                    <div class="cajatexto input-field col l4 s12">
                        <input  class="inpu" id="txtConfirmPassword_aux" type="password" name="confirmPassword_aux" required class="validate" minlength="5" maxlength="32">
                        <label for="txtConfirmPassword_aux">Confirmar password</label>
                        <span class="labe" id="txtConfirmPassword_aux_error" style="color:red" class="helper-text"></span>
                    </div> 
                </div>

                <div class="row">
                    <div class="col l12 s12">
                        <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">swap_horiz</i>Cambiar password</button>                                                
                    </div>
                </div>
            </form>          
        </main>
                        
        <jsp:include page="/Views/Shared/footerBody.jsp" />   
        <script>
            function validarFormulario() {
                var result = true;
                var txtPassword = document.getElementById("txtPassword");
                var txtConfirm_password = document.getElementById("txtConfirmPassword_aux");
                var txtConfirm_password_error = document.getElementById("txtConfirmPassword_aux_error");
                if (txtPassword.value != txtConfirm_password.value) {
                    txtConfirm_password_error.innerHTML = "El password y confirmar password debe ser iguales";
                    result = false;
                } else {
                    txtConfirm_password_error.innerHTML = "";
                }
                return result;
            }
        </script>
    </body>
</html>
