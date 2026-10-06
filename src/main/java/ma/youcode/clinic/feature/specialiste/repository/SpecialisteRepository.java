package ma.youcode.clinic.feature.specialiste.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ma.youcode.clinic.model.entity.Specialiste;

import java.util.Optional;

public class SpecialisteRepository {
    @PersistenceContext
    private EntityManager entityManager ;

    public Specialiste findById(Long id) {
        Specialiste specialiste = entityManager.find(Specialiste.class, id);
        return specialiste;
    }
}