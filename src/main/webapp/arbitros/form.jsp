<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    ArrayList<String> listaOpciones = (ArrayList <String>) request.getAttribute("listaPaises");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css'/>
        <title>LAB 9</title>
    </head>
    <body>


        <div class='container'>
            <div class="row mb-4">
                <div class="col"></div>
                <div class="col-md-6">
                    <jsp:include page="../includes/navbar.jsp"/>
                    <h1 class='mb-3'>Crear un Árbitro</h1>
                    <form method="POST" action="<%=request.getContextPath()%>/ArbitroServlet?action=guardar">
                        <div class="row g-2">
                            <% if(session.getAttribute("infotodo")!=null){
                                if(session.getAttribute("infotodo").equals("error al crear")) {%>
                            <div class="alert alert-danger" role="alert">
                                Hubo un error al crear el árbitro
                            </div>
                            <%session.removeAttribute("infotodo");%>
                            <%}}%>
                        </div>
                        <div class="form-group">
                            <label>Nombre</label>
                            <input type="text" class="form-control" name="nombre" required>
                        </div>
                        <div class="row g-2">
                            <% if(session.getAttribute("infotodo")!=null){
                               if(session.getAttribute("infotodo").equals("nombre vacio")) {%>
                                <div class="alert alert-danger" role="alert">
                                    Falta ingresar nombre
                                </div>
                              <%}else if(session.getAttribute("infotodo").equals("nombre repetido")){%>
                                    <div class="alert alert-danger" role="alert">
                                        El nombre ingresado ya se encuentra registrado
                                   </div>
                              <%}}%>
                            <%session.removeAttribute("infotodo");%>
                        </div>
                        <div class="form-group">
                            <label>País</label>
                            <select name="pais" class="form-control" required>
                                <% for(String opcion : listaOpciones){%>
                                <option value="<%=opcion%>"><%=opcion%></option>
                                <% } %>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-primary">Guardar</button>
                        <a href="<%= request.getContextPath()%>/ArbitroServlet" class="btn btn-danger">Cancelar</a>
                    </form>
                </div>
                <div class="col"></div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
                integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
                crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
                integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
                crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
                integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
                crossorigin="anonymous"></script>

    </body>
</html>
