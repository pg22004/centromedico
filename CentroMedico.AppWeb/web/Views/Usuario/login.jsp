<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title> Centro Médico | Login  </title>

    </head>
    <body  class="bodylogin">
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
       <main>
            <div class="login-box">
                <form class="formlogin" action="Usuario?accion=login" method="post">
                    <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">                

                    <h2 class="h2s">Iniciar sesión</h2>
                    
                    <div class="user-box">
                        <input class="inputs" autocomplete="off"id="txtLogin" type="text" name="login" required class="validate" maxlength="25" />
                        <label class="labels" for="txtLogin">Nombre de Usuario</label>
                        <span class="text-danger"></span>
                    </div>
              

                    <div class="user-box">
                        <input class="inputs" autocomplete="off" id="txtPassword" type="password" name="password" required class="validate" minlength="5" maxlength="32"/>
                        <label class="labels" for="txtPassword">Contraseña</label>
                        <span  class="text-danger"></span>
                    </div>      

                    <button class="buttons" type="submit">Entrar</button>            

                <% if (request.getAttribute("error") != null) { %>
                <div class="row">
                    <div class="col l12 s12">
                        <span style="color:red"><%= request.getAttribute("error") %></span>                                              
                    </div>
                </div>
                <%}%>
                </form>
            </div>
        </main>
                                
        <jsp:include page="/Views/Shared/footerBody.jsp" />      
    </body>
</html>