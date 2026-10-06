package ma.youcode.clinic.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAConfig {
    private static EntityManagerFactory emf;

    public static void init() {
        System.out.println("1 - Starting JPA initialization");

        try {
            emf = Persistence.createEntityManagerFactory("teleexpertise");

            System.out.println("2 - JPA initialized successfully");

        } catch (Exception e) {
            System.out.println("3 - ERROR initializing JPA");
            e.printStackTrace();
        }
    }

    public static EntityManager getEntityManager() {
        if (emf == null || !emf.isOpen()) {
            throw new IllegalStateException("EntityManagerFactory is not initialized");
        }

        return emf.createEntityManager();
    }

    public static void close() {
        if (emf.isOpen()) {
            emf.close();
            System.out.println("JPA closed.");
        }
    }
}
