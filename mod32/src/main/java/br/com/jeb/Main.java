package br.com.jeb;

import br.com.jeb.model.Produto;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager em = emf.createEntityManager();

        try {
            // Criar um produto de teste
            Produto produto = new Produto();
            produto.setNome("Produto Teste");
            produto.setPreco(new BigDecimal("99.99"));
            produto.setQuantidade(10);

            // Iniciar transação
            em.getTransaction().begin();

            // Persistir o produto
            em.persist(produto);

            // Commit da transação
            em.getTransaction().commit();

            System.out.println("Produto salvo com sucesso! ID: " + produto.getId());
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
            emf.close();
        }
    }
}
