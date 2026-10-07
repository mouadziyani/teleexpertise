package ma.youcode.clinic.feature.auth.seeder;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import ma.youcode.clinic.config.JPAConfig;
import ma.youcode.clinic.model.entity.User;
import ma.youcode.clinic.model.enums.UserRole;
import org.mindrot.jbcrypt.BCrypt;

public class UserSeeder {

    public static void seed() {

        EntityManager entityManager = JPAConfig.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            // NURSE
            if (findByUsername(entityManager, "nurse1") == null) {

                User nurse1 = new User(
                        "nurse1",
                        BCrypt.hashpw("nurse123", BCrypt.gensalt()),
                        UserRole.NURSE
                );

                entityManager.persist(nurse1);
            }

            // GENERALIST
            if (findByUsername(entityManager, "generaliste1") == null) {

                User generaliste = new User(
                        "generaliste1",
                        BCrypt.hashpw("generaliste123", BCrypt.gensalt()),
                        UserRole.GENERALIST
                );

                entityManager.persist(generaliste);
            }

            // SPECIALISTE
            if (findByUsername(entityManager, "specialiste1") == null) {

                User specialiste = new User(
                        "specialiste1",
                        BCrypt.hashpw("specialiste123", BCrypt.gensalt()),
                        UserRole.SPECIALISTE
                );

                entityManager.persist(specialiste);
            }

            transaction.commit();

        } catch (Exception e) {

            if (transaction.isActive()) {
                transaction.rollback();
            }

            throw e;

        } finally {
            entityManager.close();
        }
    }

    private static User findByUsername(EntityManager entityManager, String username) {
        try {
            return entityManager
                    .createQuery(
                            "SELECT u FROM User u WHERE u.username = :username",
                            User.class
                    )
                    .setParameter("username", username)
                    .getSingleResult();

        } catch (Exception e) {
            return null;
        }
    }
}