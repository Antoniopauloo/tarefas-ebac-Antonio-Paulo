package br.com.jeb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/contato")
public class ContatoServlet extends HttpServlet {

    private static List<Contato> contatos = new ArrayList<>();
    private static int idAtual = 1;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nome = req.getParameter("nome");
        String email = req.getParameter("email");
        String telefone = req.getParameter("telefone");

        Contato novo = new Contato(idAtual++, nome, email, telefone);
        contatos.add(novo);

        resp.sendRedirect("contatos.jsp");
    }

    public static List<Contato> getContatos() {
        return contatos;
    }

    public static void excluirContato(int id) {
        contatos.removeIf(c -> c.getId() == id);
    }
}
