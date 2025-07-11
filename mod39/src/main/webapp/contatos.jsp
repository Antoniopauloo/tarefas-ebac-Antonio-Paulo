<%@ page import="br.com.Ant.ContatoServlet, br.com.Ant.Contato" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Contatos - Sistema de Contatos</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
        .table-card {
            background: white;
            padding: 2rem;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            margin: 2rem 0;
            overflow-x: auto;
        }

        .header-actions {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 2rem;
        }

        .btn-novo {
            background-color: var(--primary-color);
            color: white;
            padding: 0.75rem 1.5rem;
            border-radius: 4px;
            text-decoration: none;
            display: inline-flex;
            align-items: center;
            gap: 0.5rem;
            transition: background-color 0.2s;
        }

        .btn-novo:hover {
            background-color: var(--accent-color);
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 1rem;
        }

        th, td {
            padding: 1rem;
            text-align: left;
            border-bottom: 1px solid #e2e8f0;
        }

        th {
            font-weight: 600;
            color: #4b5563;
            background-color: #f8fafc;
        }

        tr:hover {
            background-color: #f8fafc;
        }

        .empty-state {
            text-align: center;
            padding: 3rem;
            color: #64748b;
        }

        .empty-state h3 {
            color: var(--primary-color);
            margin-bottom: 1rem;
        }

        .btn-excluir {
            color: #ef4444;
            text-decoration: none;
            padding: 0.5rem 1rem;
            border-radius: 4px;
            transition: all 0.2s;
        }

        .btn-excluir:hover {
            background-color: #fef2f2;
        }

        .footer {
            text-align: center;
            margin-top: 3rem;
            padding-top: 2rem;
            border-top: 1px solid #e2e8f0;
            color: #64748b;
        }
    </style>
    <link rel="stylesheet" href="css/style.css">
    <style>
        .table-card {
            background: white;
            padding: 2rem;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            margin: 2rem 0;
        }

        .header-actions {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 2rem;
        }

        .btn-novo {
            background-color: var(--primary-color);
            color: white;
            padding: 0.75rem 1.5rem;
            border-radius: 4px;
            display: inline-flex;
            align-items: center;
            gap: 0.5rem;
        }

        .btn-novo:hover {
            background-color: var(--accent-color);
        }

        .footer {
            text-align: center;
            margin-top: 3rem;
            padding-top: 2rem;
            border-top: 1px solid #e2e8f0;
            color: #64748b;
        }

        .empty-state {
            text-align: center;
            padding: 3rem;
            color: #64748b;
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
                <a href="formulario.jsp" class="nav-link">Novo Contato</a>
                <a href="contatos.jsp" class="nav-link active">Lista de Contatos</a>
            </div>
        </div>
    </nav>

    <div class="container">
        <div class="header-actions">
            <h2>Lista de Contatos</h2>
            <a href="formulario.jsp" class="btn-novo">
                <span>+</span> Novo contato
            </a>
        </div>

        <div class="table-card">
            <% if (ContatoServlet.getContatos().isEmpty()) { %>
                <div class="empty-state">
                    <h3>Nenhum contato cadastrado</h3>
                    <p>Clique em "Novo contato" para começar</p>
                </div>
            <% } else { %>
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nome</th>
                            <th>Email</th>
                            <th>Telefone</th>
                            <th>Ação</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (Contato c : ContatoServlet.getContatos()) {
                        %>
                            <tr>
                                <td><%= c.getId() %></td>
                                <td><%= c.getNome() %></td>
                                <td><%= c.getEmail() %></td>
                                <td><%= c.getTelefone() %></td>
                                <td><a href="excluir?id=<%= c.getId() %>" class="btn-excluir">Excluir</a></td>
                            </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            <% } %>
        </div>

        <footer class="footer">
            <p>Desenvolvido por AntCode</p>
            <p>&copy; 2025 Todos os direitos reservados</p>
        </footer>
    </div>
</body>
</html>
