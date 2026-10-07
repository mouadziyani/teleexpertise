package ma.youcode.clinic.feature.demande.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import ma.youcode.clinic.config.JPAConfig;
import ma.youcode.clinic.model.entity.DemandeExpertise;

public class DemandeExpertiseRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public DemandeExpertiseRepository() {
        this.entityManager = JPAConfig.getEntityManager();
    }

    public DemandeExpertise save(DemandeExpertise demande) {

        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            entityManager.persist(demande);

            transaction.commit();

            return demande;

        } catch (Exception e) {

            if (transaction.isActive()) {
                transaction.rollback();
            }

            System.err.println("Error : " + e.getMessage());
        }

        return null;
    }
}
