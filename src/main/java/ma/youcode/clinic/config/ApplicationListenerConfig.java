package ma.youcode.clinic.config;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ApplicationListenerConfig implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Creating db");
        JPAConfig.init();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        JPAConfig.close();
    }
}
