package ma.youcode.clinic.feature.demande.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import ma.youcode.clinic.config.JPAConfig;
import ma.youcode.clinic.model.entity.DemandeExpertise;
import ma.youcode.clinic.model.enums.StatutDemande;

import java.util.List;

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

    public List<DemandeExpertise> findBySpecialiste(Long specialisteId) {
        return entityManager.createQuery(
                        "SELECT d FROM DemandeExpertise d WHERE d.specialiste.userId = :specialisteId",
                        DemandeExpertise.class
                )
                .setParameter("specialisteId", specialisteId)
                .getResultList();
    }
    public DemandeExpertise findById(long id){
        return entityManager.find(DemandeExpertise.class , id);
    }
    public void update(DemandeExpertise demande) {
        entityManager.merge(demande);
    }

    public List<DemandeExpertise> findBySpecialisteAndStatut(Long specialisteId , StatutDemande statut) {
        return entityManager.createQuery(
                        "SELECT d FROM DemandeExpertise d WHERE d.specialiste.userId = :specialisteId AND d.statut = :statut",
                        DemandeExpertise.class
                )
                .setParameter("specialisteId", specialisteId)
                .setParameter("statut", statut)
                .getResultList();
    }
}
