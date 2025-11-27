<%-- 
    Document   : MinhasReservas
    Created on : 24/11/2025, 22:38:40
    Author     : igor
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="Entity.Reserva, Entity.Sala, Entity.Usuario" %>

<%
    // Recupera dados enviados pelo Controller
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
    List<Reserva> reservas = (List<Reserva>) request.getAttribute("reservas");
    List<Sala> salas = (List<Sala>) request.getAttribute("salas");

    if (usuario == null) {
        response.sendRedirect("index.html");
        return;
    }
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Minhas Reservas</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/style.css">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="<%=request.getContextPath()%>/frontcontrollerServlet?action=consultarSalas">Sistema de Reservas</a>

    <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
            data-bs-target="#navbarNav">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav ms-auto">
        <li class="nav-item">
          <a class="nav-link" href="<%=request.getContextPath()%>/frontcontrollerServlet?action=consultarSalas">Salas</a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" aria-current="page"
             href="<%=request.getContextPath()%>/frontcontrollerServlet?action=consultarMinhasReservas">Minhas Reservas</a>
        </li>
      </ul>
    </div>
  </div>
</nav>

<div class="container my-5">
    <h1 class="mb-4 text-center">Minhas Reservas</h1>

    <div class="row g-4">
        <%
            if (reservas == null || reservas.isEmpty()) {
        %>
            <h4 class="text-center text-muted">Você não possui reservas.</h4>
        <%
            } else {

                for (Reserva res : reservas) {

                    // Busca sala correspondente (o Controller já enviou todas as salas)
                    Sala sala = null;
                    for (Sala s : salas) {
                        if (s.getId() == res.getIdSala().getId()) {
                            sala = s;
                            break;
                        }
                    }

                    boolean status = res.isStatus();
                    // Texto já calculado no Controller
        %>

        <div class="col-md-6">
            <div class="card shadow-sm p-3">

                <h5><%= (sala != null ? sala.getNome() : "Sala Desconhecida") %> - <%= (sala != null ? sala.getPredio() : "") %></h5>

                <p>Data: <%= res.getData() %></p>
                <p>Horário: <%= res.getHoraInicio() %> - <%= res.getHoraFim() %></p>

                <p>Status: <strong><%= status %></strong></p>

                <p>Observação:
                    <%= res.getObservacao() != null ? res.getObservacao() : "-" %>
                </p>

                <form action="<%=request.getContextPath()%>/frontcontrollerServlet" method="post">
                    <input type="hidden" name="action" value="deletarReserva">
                    <input type="hidden" name="txtid" value="<%= res.getIdReserva() %>">
                    <button class="btn btn-danger btn-sm">Deletar</button>
                </form>

                <form action="<%=request.getContextPath()%>/frontcontrollerServlet" method="post" class="mt-2">
                    <input type="hidden" name="action" value="atualizarReserva">
                    <input type="hidden" name="txtid" value="<%= res.getIdReserva() %>">
                    <input type="hidden" name="txtidSala" value="<%= (sala != null ? sala.getId() : 0) %>">
                    <input type="hidden" name="txtidUsuario" value="<%= usuario.getIdUsuario() %>">

                    <label>Data:</label>
                    <input type="date" name="txtdata" value="<%= res.getData() %>"
                           class="form-control mb-1" required>

                    <label>Hora Início:</label>
                    <input type="time" name="txthoraInicio" value="<%= res.getHoraInicio() %>"
                           class="form-control mb-1" required>

                    <label>Hora Fim:</label>
                    <input type="time" name="txthoraFim" value="<%= res.getHoraFim() %>"
                           class="form-control mb-1" required>

                    <label>Observação:</label>
                    <input type="text" name="txtobservacao" value="<%= res.getObservacao() %>"
                           class="form-control mb-2">

                    <button class="btn btn-primary btn-sm">Atualizar</button>
                </form>

            </div>
        </div>

        <%
                }
            }
        %>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>