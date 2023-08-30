<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="centromedico.entidadesdenegocios.PacienteSala"%>
<%@page import="centromedico.entidadesdenegocios.Paciente"%>
<%@page import="centromedico.entidadesdenegocios.Sala"%>
<%@page import="java.util.ArrayList"%>

<% ArrayList<PacienteSala> pacienteSalas = (ArrayList<PacienteSala>) request.getAttribute("pacienteSalas");
    int numPage = 1;
    int numReg = 10;
    int countReg = 0;
    if (pacienteSalas == null) {
        pacienteSalas = new ArrayList();
    } else if (pacienteSalas.size() > numReg) {
        double divNumPage = (double) pacienteSalas.size() / (double) numReg;
        numPage = (int) Math.ceil(divNumPage);
    }
    String strTop_aux = request.getParameter("top_aux");
    int top_aux = 10;
    if (strTop_aux != null && strTop_aux.trim().length() > 0) {
        top_aux = Integer.parseInt(strTop_aux);
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Lista de PacienteSala</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Buscar Paciente Sala</h5>
            <form action="PacienteSala" method="post">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>"> 
                <div class="row">
                    
                    
                    <div class="input-field col l4 s12">   
                        <jsp:include page="/Views/Paciente/select.jsp">                           
                            <jsp:param name="id" value="0" />  
                        </jsp:include>                        
                    </div>
                    
                    <div class="input-field col l4 s12">   
                        <jsp:include page="/Views/Sala/select.jsp">                           
                            <jsp:param name="id" value="0" />  
                        </jsp:include>                        
                    </div>
                    <div class="input-field col l4 s12">   
                        <jsp:include page="/Views/Shared/selectTop.jsp">
                            <jsp:param name="top_aux" value="<%=top_aux%>" />                        
                        </jsp:include>                        
                    </div> 
                </div>
                <div class="row">
                    <div class="col l12 s12">
                         <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">search</i>Buscar</button>
                        <a href="PacienteSala?accion=create" class="waves-effect waves-light btn blue"><i class="material-icons right">add</i>Crear</a>                          
                    </div>
                </div>
            </form>

            <div class="row">
                <div class="col l12 s12">
                    <div style="overflow: auto">
                        <table class="paginationjs">
                            <thead>
                                <tr>                                     

                                    <th>Paciente</th>
                                    <th>Apellido</th> 
                                   <th>Sala</th>  
                                    <th>Fecha</th>  
                                    <th>Acciones</th>
                                </tr>
                            </thead>                       
                            <tbody>                           
                                <% for (PacienteSala pacientesala : pacienteSalas) {
                                        int tempNumPage = numPage;
                                        if (numPage > 1) {
                                            countReg++;
                                            double divTempNumPage = (double) countReg / (double) numReg;
                                            tempNumPage = (int) Math.ceil(divTempNumPage);
                                        }
                                %>
                                <tr data-page="<%= tempNumPage%>">                                    
                                    <td><%=pacientesala.getPaciente().getNombre()%></td>
                                    <td><%=pacientesala.getPaciente().getApellido()%></td>
                                    <td><%=pacientesala.getSala().getNombre()%></td>
                                    <td><%=pacientesala.getFecha()%></td>  
                                    <td>
                                        <div style="display:flex">
                                             <a href="PacienteSala?accion=edit&id=<%=pacientesala.getId()%>" title="Modificar" class="waves-effect waves-light btn green">
                                            <i class="material-icons">edit</i>
                                        </a>
                                        <a href="PacienteSala?accion=details&id=<%=pacientesala.getId()%>" title="Ver" class="waves-effect waves-light btn blue">
                                            <i class="material-icons">description</i>
                                        </a>
                                        <a href="PacienteSala?accion=delete&id=<%=pacientesala.getId()%>" title="Eliminar" class="waves-effect waves-light btn red">
                                            <i class="material-icons">delete</i>
                                        </a>    
                                        </div>                                                                    
                                    </td>                                   
                                </tr>
                                <%}%>                                                       
                            </tbody>
                        </table>
                    </div>                  
                </div>
            </div>             
            <div class="row">
                <div class="col l12 s12">
                    <jsp:include page="/Views/Shared/paginacion.jsp">
                        <jsp:param name="numPage" value="<%= numPage%>" />                        
                    </jsp:include>
                </div>
            </div>
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />
    </body>
</html>