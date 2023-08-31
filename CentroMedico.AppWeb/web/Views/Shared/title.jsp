<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="centromedico.appweb.utils.Utilidad"%>
<%@page import="jakarta.servlet.http.HttpServletRequest"%>

<!--Import de css local-->
<link rel="stylesheet" href="<%=Utilidad.obtenerRuta(request, "/wwwroot/css/estilos.css") %>">

<!--Import de JavaScript local-->
<script src="<%=Utilidad.obtenerRuta(request, "/wwwroot/js/.js") %>"></script>


<!--Import de Boostrap-->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

<!--Import Google Icon Font-->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<!--Import de jquery -->
<script src="https://code.jquery.com/jquery-3.3.1.js" integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60=" crossorigin="anonymous"></script>

<!--Let browser know website is optimized for mobile-->
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>



<!-- Compilado y minimizado CSS de Materialize-->
<!--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">-->
<link rel="stylesheet" href="<%=Utilidad.obtenerRuta(request, "/wwwroot/lib/materialize/css/materialize.min.css") %>">


<!-- Compilado y minimizado JavaScript de Materialize-->
<!--<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>-->
<script src="<%=Utilidad.obtenerRuta(request, "/wwwroot/lib/materialize/js/materialize.min.js") %>"></script>
