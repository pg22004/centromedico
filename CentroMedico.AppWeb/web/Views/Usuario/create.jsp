<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="centromedico.entidadesdenegocios.Usuario"%>

<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Crear Usuario</title>
    </head>
    <body class="bodys">
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="contenedor container">   
            <h3>Crear Usuario</h3>
            <form action="Usuario" method="post" onsubmit="return  validarFormulario()">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">                
                <div class="row">
                    <div class="cajatexto input-field col l4 s12">
                        <input class="inpu" id="txtNombre" type="text" name="nombre" required class="validate" maxlength="30">
                        <label class="labe"  for="txtNombre">Nombre</label>
                    </div>                       
                    <div class="cajatexto input-field col l4 s12">
                        <input class="inpu" id="txtApellido" type="text" name="apellido" required class="validate" maxlength="30">
                        <label class="labe" for="txtApellido">Apellido</label>
                    </div> 
                    <div class="cajatexto input-field col l4 s12">
                        <input class="inpu" id="txtLogin" type="text" name="login" required class="validate" maxlength="25">
                        <label class="labe" for="txtLogin">Login</label>
                    </div> 
                    <div class="cajatexto input-field col l4 s12">
                        <input class="inpu" id="txtPassword" type="password" name="password" required class="validate" minlength="5" maxlength="32">
                        <label class="labe" for="txtPassword">Password</label>
                    </div> 
                    <div class="confirmusuario cajatexto col l4 s12">
                        <label class="labe labels-icons" for="txtConfirmPassword_aux">Confirmar password</label>
                        <input class="inpu " id="txtConfirmPassword_aux" type="password" name="confirmPassword_aux" required class="validate" minlength="5" maxlength="32">
                        <span id="txtConfirmPassword_aux_error" style="color:red" class="helper-text"></span>
                    </div> 
                    <div class="cajatexto input-field col l4 s12">   
                        <select id="slEstatus" name="estatus" class="validate">
                            <option value="0">SELECCIONAR</option>
                            <option value="<%=Usuario.EstatusUsuario.ACTIVO%>">ACTIVO</option>
                            <option value="<%=Usuario.EstatusUsuario.INACTIVO%>">INACTIVO</option>
                        </select>       
                        <label for="slEstatus">Estatus</label>
                        <span id="slEstatus_error" style="color:red" class="helper-text"></span>
                    </div>
                    <div class="cajatexto input-field col l4 s12">   
                        <jsp:include page="/Views/Rol/select.jsp">                           
                            <jsp:param name="id" value="0" />  
                        </jsp:include>  
                        <span id="slRol_error" style="color:red" class="helper-text"></span>
                    </div>
                </div>

                <div class="row">
                    <div class="col l12 s12">
                        <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">save</i>Guardar</button>
                        <a href="Usuario" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
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
                var slEstatus = document.getElementById("slEstatus");
                var slEstatus_error = document.getElementById("slEstatus_error");
                var slRol = document.getElementById("slRol");
                var slRol_error = document.getElementById("slRol_error");
                if (txtPassword.value != txtConfirm_password.value) {
                    txtConfirm_password_error.innerHTML = "El password y confirmar password debe ser iguales";
                    result = false;
                } else {
                    txtConfirm_password_error.innerHTML = "";
                }
                if (slEstatus.value == 0) {
                    slEstatus_error.innerHTML = "El estatus es obligatorio";
                    result = false;
                } else {
                    slEstatus_error.innerHTML = "";
                }
                if (slRol.value == 0) {
                    slRol_error.innerHTML = "El Rol es obligatorio";
                    result = false;
                } else {
                    slRol_error.innerHTML = "";
                }

                return result;
            }
        </script>
    </body>
</html>
