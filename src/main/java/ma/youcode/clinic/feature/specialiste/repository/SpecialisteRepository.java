package ma.youcode.clinic.feature.specialiste.repository;


import java.util.List;

import jakarta.persistence.EntityManager;
import ma.youcode.clinic.config.JPAConfig;
import ma.youcode.clinic.model.entity.Specialiste;
import ma.youcode.clinic.model.enums.Specialite;

public class SpecialisteRepository {

    private EntityManager entityManager ;

    public SpecialisteRepository() {
        this.entityManager = JPAConfig.getEntityManager();
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