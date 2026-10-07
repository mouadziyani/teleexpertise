package ma.youcode.clinic.filter;

import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import ma.youcode.clinic.config.CustomSecurityContext;
import ma.youcode.clinic.feature.auth.repository.UserRepository;
import ma.youcode.clinic.model.entity.User;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class BasicAuthFilter implements ContainerRequestFilter {
    @Inject
    private UserRepository userRepository;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader == null || !authorizationHeader.startsWith("Basic ")) {
            abortWith401(requestContext);
            return;
        }

        try {
            String base64Credentials =  authorizationHeader.substring("Basic ".length()).trim();

            String credentials = new String(Base64.getDecoder().decode(base64Credentials), StandardCharsets.UTF_8);

            String[] infos = credentials.split(":" , 2);

            String username = infos[0];
            String password = infos[1];

            User user = userRepository.findByUsername(username);

            if (user == null) {
                abortWith401(requestContext);
                return;
            }

            boolean passwordCorrect = BCrypt.checkpw(password, user.getPassword());

            if (!passwordCorrect) {
                abortWith401(requestContext);
                return;
            }

            CustomSecurityContext securityContext = new CustomSecurityContext(user , requestContext.getUriInfo().getRequestUri().getScheme());

            requestContext.setSecurityContext(securityContext);

        } catch (IllegalArgumentException e) {

            abortWith401(requestContext);
        }
    }

    private void abortWith401(ContainerRequestContext requestContext) {

        requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
                        .header(HttpHeaders.WWW_AUTHENTICATE, "Basic")
                        .build()
        );
    }
}
