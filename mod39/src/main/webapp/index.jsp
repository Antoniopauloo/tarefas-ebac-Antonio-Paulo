<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sistema de Contatos - AntCode</title>
    <link rel="stylesheet" href="css/style.css">    <style>
        .card-container {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
            gap: 2rem;
            margin: 2rem 0;
        }

        .card {
            background: white;
            padding: 2rem;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            text-align: center;
            transition: transform 0.2s;
        }

        .card:hover {
            transform: translateY(-5px);
        }

        .card h3 {
            color: var(--primary-color);
            margin-bottom: 1rem;
        }

        .card-icon {
            font-size: 2.5rem;
            margin-bottom: 1rem;
        }

        .footer {
            text-align: center;
            margin-top: 3rem;
            padding-top: 2rem;
            border-top: 1px solid #e2e8f0;
            color: #64748b;
        }

        .header {
            text-align: center;
            margin-bottom: 3rem;
        }

        .header h1 {
            color: var(--primary-color);
            font-size: 2.5rem;
            margin-bottom: 1rem;
        }

        .header p {
            color: #64748b;
            font-size: 1.1rem;
        }
    </style>
</head>
<body>
    <nav class="nav">
        <div class="nav-container">
            <a href="index.jsp" class="nav-logo">
                <span>Ant</span>Code
            </a>
            <div class="nav-links">
                <a href="index.jsp" class="nav-link active">Início</a>
                <a href="formulario.jsp" class="nav-link">Novo Contato</a>
                <a href="contatos.jsp" class="nav-link">Lista de Contatos</a>
            </div>
        </div>
    </nav>

    <div class="container">
        <header class="header">
            <h1>Sistema de Gerenciamento de Contatos</h1>
            <p>Organize seus contatos de forma simples e eficiente</p>
        </header>

        <div class="card-container">
            <a href="formulario.jsp" class="card">
                <div class="card-icon">📝</div>
                <h3>Novo Contato</h3>
                <p>Adicione um novo contato ao sistema</p>
            </a>

            <a href="contatos.jsp" class="card">
                <div class="card-icon">📋</div>
                <h3>Lista de Contatos</h3>
                <p>Visualize e gerencie seus contatos</p>
            </a>
        </div>

        <footer class="footer">
            <p>Desenvolvido por AntCode</p>
            <p>&copy; 2025 Todos os direitos reservados</p>
        </footer>
    </div>
</body>
</html>
