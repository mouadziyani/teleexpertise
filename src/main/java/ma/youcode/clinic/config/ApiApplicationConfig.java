package ma.youcode.clinic.config;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

@ApplicationPath("/api")
public class ApiApplicationConfig extends ResourceConfig {

    public ApiApplicationConfig() {
        packages("ma.youcode.clinic");

        register(new ApplicationBinder());
        register(JacksonJsonProvider.class);
        register(RolesAllowedDynamicFeature.class);
    }
}
