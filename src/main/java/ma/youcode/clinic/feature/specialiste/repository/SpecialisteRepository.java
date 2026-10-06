package ma.youcode.clinic.feature.specialiste.repository;


import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ma.youcode.clinic.model.entity.Specialiste;
import ma.youcode.clinic.model.enums.Specialite;

public class SpecialisteRepository {
        @PersistenceContext
    private EntityManager entityManager ;

    public SpecialisteRepository(EntityManager entityManager){
        this.entityManager=entityManager;
    }

    public List<Specialiste> findAll(){
        return entityManager.createQuery("SELECT s FROM Specialiste s",Specialiste.class).getResultList();
    }

    public List<Specialiste> findBySpecialite(Specialite specialite) {
            return entityManager.createQuery("SELECT s FROM Specialiste s WHERE s.specialite = :spec", Specialiste.class)
                    .setParameter("spec", specialite)
                    .getResultList();
    }

    public Specialiste findById(Long id) {
        Specialiste specialiste = entityManager.find(Specialiste.class, id);
        return specialiste;
    }
}