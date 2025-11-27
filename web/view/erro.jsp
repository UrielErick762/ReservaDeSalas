<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Erro no Sistema</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f8f9fa; display: flex; align-items: center; justify-content: center; height: 100vh; font-family: 'Poppins', sans-serif; }
        .error-card { background: white; padding: 2rem; border-radius: 1rem; box-shadow: 0 0 20px rgba(0,0,0,0.1); text-align: center; }
        h1 { color: #dc3545; font-size: 2rem; margin-bottom: 1rem; }
        p { color: #6c757d; }
        .btn-voltar { margin-top: 1rem; }
    </style>
</head>
<body>
    <div class="error-card">
        <h1>Ops... algo deu errado!</h1>
        <p>${msg != null ? msg : "Ocorreu um erro inesperado no sistema."}</p>
        <!-- Botão aponta para o index na raiz -->
        <a href="<%=request.getContextPath()%>/index.html" class="btn btn-primary btn-voltar">Voltar ao início</a>
    </div>
</body>
</html>
