<%--
  Created by IntelliJ IDEA.
  User: Angie
  Date: 9/11/2022
  Time: 22:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.lab9_base.Bean.Partido" %>
<jsp:useBean id="lista" scope="request" type="java.util.ArrayList<com.example.lab9_base.Bean.Partido>"/>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css' />
    <title>LAB 9</title>
</head>
<body>
<div class='container'>
    <div class="row mb-5 mt-4">
        <div class="col-lg-6">
            <h1 class=''>Lista de Partidos</h1>
        </div>
        <div class="col-lg-6 my-auto text-lg-right">
            <a href="<%= request.getContextPath()%>/PartidoServlet?action=crear" class="btn btn-primary">Crear Partido</a>
        </div>
    </div>
    <table class="table">
        <tr>
            <th>#</th>
            <th>Jornada</th>
            <th>Fecha</th>
            <th>Selección Local</th>
            <th>Selección Visitante</th>
            <th>Estadio a jugar</th>
            <th>Árbitro</th>
        </tr>
        <% int i = 1;
            for (Partido partido : lista) { %>
        <tr>
            <td><%=i%>
            </td>
            <td><%=partido.getNumeroJornada()%>
            </td>
            <td><%=partido.getFecha()%>
            </td>
            <td><%=partido.getSeleccionLocal().getNombre()%>
            </td>
            <td><%=partido.getSeleccionVisitante().getNombre()%>
            </td>
            <td><%=partido.getSeleccionLocal().getEstadio().getNombre()%>
            </td>
            <td><%=partido.getArbitro().getNombre()%>
            </td>
            <td>
                <a type="button" class="btn btn-primary"
                <%--                           href="<%=request.getContextPath()%>/JobServlet?action=editar&id=<%=job.getJobId()%>">--%>
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                     class="bi bi-pencil" viewBox="0 0 16 16">
                    <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"></path>
                </svg>
                </a>
            </td>
            <td>
                <a type="button" class="btn btn-primary"
                <%--                           href="<%=request.getContextPath()%>/JobServlet?action=editarParcial&id=<%=job.getJobId()%>">--%>
                <i class="bi bi-pencil-square"></i>
                </a>
            </td>
            <td>
                <a type="button" class="btn btn-danger"
                   onclick="return confirm('¿Estas seguro(a) que deseas borrar?')"
                <%--                           href="<%=request.getContextPath()%>/JobServlet?action=borrar&id=<%=job.getJobId()%>">--%>
                <i class="bi bi-trash"></i>
                </a>
            </td>
        </tr>
        <% i++;
        }

        %>

    </table>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

</body>
</html>
