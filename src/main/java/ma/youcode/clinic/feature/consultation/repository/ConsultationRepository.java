package ma.youcode.clinic.feature.consultation.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ma.youcode.clinic.model.entity.Consultation;

public class ConsultationRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Consultation findById(Long id) {
        Consultation consultation = entityManager.find(Consultation.class, id);
        return consultation;
    }
}
