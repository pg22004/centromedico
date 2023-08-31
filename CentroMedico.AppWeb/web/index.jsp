<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="centromedico.appweb.utils.*"%>
<%@page import="jakarta.servlet.http.HttpServletRequest"%>

<% if (SessionUser.isAuth(request) == false) {
         response.sendRedirect("Usuario?accion=login");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Principal</title>
    </head>
    <body class="bodys">
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="contenedor container">  
            <div class="row">
                <div class="col l12 s12">
                    <h1>Bienvenidos</h1> 
                    <h5>A la ultima innovación de sistemas de base de datos para el Centro Médico IMECS </h5> 
                </div>
            </div>            
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />
    </body>
</html>