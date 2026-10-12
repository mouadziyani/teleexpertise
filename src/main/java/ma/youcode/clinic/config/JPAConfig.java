package ma.youcode.clinic.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAConfig {
    private static EntityManagerFactory emf;

    public static void init() {
        try {
            emf = Persistence.createEntityManagerFactory("teleexpertise");


        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
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
