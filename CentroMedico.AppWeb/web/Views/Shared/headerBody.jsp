<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="centromedico.appweb.utils.*"%>
<%@page import="jakarta.servlet.http.HttpServletRequest"%>

<nav class="BarraPrincipal navbar navbar-expand-md navbar-dark fixed-top">
    <div class="container-fluid">

        <a class="navbar-brand" href="Home">Centro Médico
            <img class="logotipo" src="https://firebasestorage.googleapis.com/v0/b/bruflix-imecs.appspot.com/o/Imagenes_Pelicula%2Flogo.jpg?alt=media&token=c1dd86fe-bd84-4b78-8885-c8a913fdf7d4" />
        </a>

        <button id="menu" class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
            <span id="menu" class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse " id="navbarCollapse">
            <ul class="navbar-nav me-auto mb-2 mb-md-0">
                <% if (SessionUser.isAuth(request)) {  %>
                    <li class="nav-item ItembarraInicio"><a class="nav-link text-white" href="Home">Inicio</a></li>
                    <li class="nav-item ItembarraInicio"><a class="nav-link text-white" href="Paciente">Paciente</a></li>
                    <li class="nav-item ItembarraInicio"><a class="nav-link text-white" href="Historial">Historial</a></li>
                    <li class="nav-item ItembarraInicio"><a class="nav-link text-white" href="PacienteSala">Sala del Paciente</a></li>
                    <li class="nav-item ItembarraInicio"><a class="nav-link text-white" href="Sala">Sala</a></li>
                    <li class="nav-item Itembarra dropdown">
                        <a class="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                          Menú
                        </a>
                        <ul id="Fonfo" class="dropdown-menu">
                            <li class="ItembarraInicio"><a id="FonfoItem" class="dropdown-item" href="Usuario">Usuarios</a></li>
                            <li class="ItembarraInicio"><a id="FonfoItem"  class="dropdown-item" href="Rol">Roles</a></li>
                            <li><hr class="dropdown-divider"></li>
                            <li class="ItembarraInicio"><a id="FonfoItem" class="dropdown-item" href="Usuario?accion=cambiarpass">Cambiar password</a></li>
                            <li class="ItembarraInicio"><a id="FonfoItem" class="dropdown-item" href="Usuario?accion=login">Cerrar sesión</a></li>
                        </ul>
                    </li>
                <%}%>
            </ul>
        </div>
    </div>
</nav>    
