package ma.youcode.clinic.feature.auth.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import ma.youcode.clinic.config.JPAConfig;
import ma.youcode.clinic.model.entity.User;

public class UserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public UserRepository() {
        this.entityManager = JPAConfig.getEntityManager();
    }

    public User findByUsername(String username) {
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.username = :username",
                User.class
        );

        query.setParameter("username", username);

        return query.getSingleResult();
    }
}
