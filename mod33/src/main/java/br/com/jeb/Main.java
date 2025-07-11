package br.com.jeb;

import br.com.jeb.domain.Brand;
import br.com.jeb.domain.Car;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager em = emf.createEntityManager();

        try {
            // Create a new brand
            Brand brand = new Brand();
            brand.setName("Toyota");

            // Create a new car
            Car car = new Car();
            car.setModel("Corolla");
            car.setYear(2023);
            car.setBrand(brand);

            // Start transaction
            em.getTransaction().begin();

            // Persist objects
            em.persist(brand);
            em.persist(car);

            // Commit transaction
            em.getTransaction().commit();

            System.out.println("Brand and Car saved successfully!");
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
