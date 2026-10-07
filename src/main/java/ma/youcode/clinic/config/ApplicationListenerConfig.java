package ma.youcode.clinic.config;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ma.youcode.clinic.feature.auth.seeder.UserSeeder;

@WebListener
public class ApplicationListenerConfig implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        JPAConfig.init();

        UserSeeder.seed();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        JPAConfig.close();
    }
}
