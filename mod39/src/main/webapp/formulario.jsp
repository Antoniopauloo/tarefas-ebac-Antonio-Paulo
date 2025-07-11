<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Novo Contato - Sistema de Contatos</title>    <link rel="stylesheet" href="css/style.css">
    <style>
        .form-card {
            background: white;
            padding: 2rem;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            margin-bottom: 2rem;
        }

        .actions {
            display: flex;
            gap: 1rem;
            margin-top: 2rem;
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

        input[type="text"],
        input[type="email"],
        input[type="tel"] {
            width: 100%;
            padding: 0.75rem;
            border: 1px solid #e2e8f0;
            border-radius: 4px;
            font-size: 1rem;
            margin-top: 0.5rem;
        }

        label {
            color: #4b5563;
            font-weight: 500;
        }

        .form-group {
            margin-bottom: 1.5rem;
        }

        .btn-salvar {
            background-color: var(--primary-color);
            color: white;
            padding: 0.75rem 1.5rem;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 1rem;
            transition: background-color 0.2s;
        }

        .btn-salvar:hover {
            background-color: var(--accent-color);
        }

        .btn-voltar {
            color: #4b5563;
            text-decoration: none;
            padding: 0.75rem 1.5rem;
            border-radius: 4px;
            transition: background-color 0.2s;
        }

        .btn-voltar:hover {
            background-color: #f3f4f6;
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
                <a href="index.jsp" class="nav-link">Início</a>
                <a href="formulario.jsp" class="nav-link active">Novo Contato</a>
                <a href="contatos.jsp" class="nav-link">Lista de Contatos</a>
            </div>
        </div>
    </nav>    <div class="container">
        <header class="header">
            <h1>Novo Contato</h1>
            <p>Adicione um novo contato ao sistema</p>
        </header>

        <div class="form-card">
            <form action="contato" method="post">
                <div class="form-group">
                    <label for="nome">Nome</label>
                    <input type="text" id="nome" name="nome" required placeholder="Digite o nome completo">
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" id="email" name="email" required placeholder="Digite o email">
                </div>
                <div class="form-group">
                    <label for="telefone">Telefone</label>
                    <input type="tel" id="telefone" name="telefone" required placeholder="Digite o telefone">
                </div>
                <div class="actions">
                    <button type="submit" class="btn-salvar">Salvar Contato</button>
                    <a href="contatos.jsp" class="btn-voltar">Voltar para Lista</a>
                </div>
            </form>
        </div>

        <footer class="footer">
            <p>Desenvolvido por AntCode</p>
            <p>&copy; 2025 Todos os direitos reservados</p>
        </footer>
    </div>
</body>
</html>
