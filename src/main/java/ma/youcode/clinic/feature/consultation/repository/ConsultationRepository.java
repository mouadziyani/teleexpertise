package ma.youcode.clinic.feature.consultation.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ma.youcode.clinic.config.JPAConfig;
import ma.youcode.clinic.model.entity.Consultation;

public class ConsultationRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public ConsultationRepository() {
        this.entityManager = JPAConfig.getEntityManager();
    }

    public Consultation findById(Long id) {
        Consultation consultation = entityManager.find(Consultation.class, id);
        return consultation;
    }
}
