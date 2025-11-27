<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="Entity.Sala" %>
<%@ page import="Entity.Usuario" %>

<%
    Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");

    List<Sala> salas = (List<Sala>) request.getAttribute("salas");
    Map<Sala, Boolean> disponibilidade = (Map<Sala, Boolean>) request.getAttribute("disponibilidade");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Salas Disponíveis</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/style.css">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="<%=request.getContextPath()%>/frontcontrollerServlet?action=consultarSalas">Sistema de Reservas</a>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link active" href="<%=request.getContextPath()%>/frontcontrollerServlet?action=consultarSalas">Salas</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/frontcontrollerServlet?action=consultarMinhasReservas">Minhas Reservas</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container my-5">
    <h1 class="my-4 text-center">Salas Disponíveis</h1>

    <div class="row g-4">

        <% for (Sala sala : salas) {  
               boolean disponivel = disponibilidade.get(sala);
        %>

        <div class="col-sm-6 col-md-4 col-lg-3">
            <div class="card h-100 shadow-sm <%= disponivel ? "" : "indisponivel" %>">
                <div class="card-body d-flex flex-column justify-content-between">
                    <h5 class="card-title text-center"><%= sala.getNome() %></h5>

                    <p class="card-text text-center mb-3">
                        Capacidade: <%= sala.getCapacidade() %><br>
                        Prédio: <%= sala.getPredio() %> - Andar: <%= sala.getAndar() %><br>
                        Número: <%= sala.getNumero() %><br>
                        Tipo: <%= sala.getTipo() %><br>
                        Horário: <%= sala.getHorarioDisp() %><br>
                        Status: <strong><%= disponivel ? "Disponível ✅" : "Indisponível ❌" %></strong>
                    </p>

                    <% if (disponivel && usuarioLogado != null) { %>

                    <form action="<%=request.getContextPath()%>/frontcontrollerServlet" method="post">
                        <input type="hidden" name="action" value="cadastrarReserva">
                        <input type="hidden" name="txtidSala" value="<%= sala.getId() %>">
                        <input type="hidden" name="txtidUsuario" value="<%= usuarioLogado.getIdUsuario() %>">
                        <input type="hidden" name="txtstatus" value="true">

                        <div class="mb-2">
                            <label>Data:</label>
                            <input type="date" name="txtdata" class="form-control mb-1" required>
                        </div>

                        <div class="mb-2">
                            <label>Hora Início:</label>
                            <input type="time" name="txthoraInicio" class="form-control mb-1" required>
                        </div>

                        <div class="mb-2">
                            <label>Hora Fim:</label>
                            <input type="time" name="txthoraFim" class="form-control mb-1" required>
                        </div>

                        <div class="mb-2">
                            <label>Observação:</label>
                            <input type="text" name="txtobservacao" class="form-control mb-2">
                        </div>

                        <button type="submit" class="btn btn-primary w-100">Reservar Sala</button>
                    </form>

                    <% } else { %>

                        <button class="btn btn-secondary w-100" disabled>Indisponível</button>

                    <% } %>
                </div>
            </div>
        </div>

        <% } %>

    </div>
</div>

</body>
</html>
