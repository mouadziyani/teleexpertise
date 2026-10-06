package ma.youcode.clinic.feature.demande.repository;

import jakarta.persistence.EntityManager;
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
        entityManager.persist(demande);

        return demande;
    }
}
