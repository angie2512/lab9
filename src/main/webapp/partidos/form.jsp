<%@ page import="com.example.lab9_base.Bean.Seleccion" %>
<%@ page import="com.example.lab9_base.Bean.Arbitro" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="listaSelecciones" scope="request" type="java.util.ArrayList<com.example.lab9_base.Bean.Seleccion>"/>
<jsp:useBean id="listaSelecciones2" scope="request" type="java.util.ArrayList<com.example.lab9_base.Bean.Seleccion>"/>
<jsp:useBean id="listaArbitros" scope="request" type="java.util.ArrayList<com.example.lab9_base.Bean.Arbitro>"/>
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
            <h1 class='mb-3'>Crear un Partido de Clasificatorias</h1>
            <form method="POST" action="<%=request.getContextPath()%>/PartidoServlet?action=guardar">
                <div class="form-group">
                    <label>Jornada</label>
                    <input type="number" class="form-control" id="jornada" name="jornada">
                </div>
                <div class="form-group">
                    <label>Fecha</label>
                    <input class="form-control datetimepicker" id="fecha" name="fecha"
                           type="date"/>
                </div>
                <div class="form-group">
                    <label>Selección local</label>
                    <select name="local" id="local" class="form-control">
                        <% for(Seleccion seleccion_local: listaSelecciones){%>
                        <option value="<%=seleccion_local.getIdSeleccion()%>"><%=seleccion_local.getNombre()%>
                        </option>
                        <% } %>
                    </select>
                </div>
                <div class="form-group">
                    <label>Selección Visitante</label>
                    <select name="visitante" id="visitante" class="form-control">
                        <% for(Seleccion seleccion_visita: listaSelecciones2){%>
                        <option value="<%=seleccion_visita.getIdSeleccion()%>"><%=seleccion_visita.getNombre()%>
                        </option>
                        <% } %>
                    </select>
                </div>
                <div class="form-group">
                    <label>Árbitro</label>
                    <select name="arbitro" id= "arbitro" class="form-control">
                        <% for(Arbitro arbitro: listaArbitros){%>
                        <option value="<%=arbitro.getIdArbitro()%>"><%=arbitro.getNombre()%>
                        </option>
                        <% } %>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Guardar</button>
                <a href="<%=request.getContextPath()%>/PartidoServlet" class="btn btn-danger">Cancelar</a>
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