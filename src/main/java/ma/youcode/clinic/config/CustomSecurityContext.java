package ma.youcode.clinic.config;

import jakarta.ws.rs.core.SecurityContext;
import ma.youcode.clinic.model.entity.User;

import java.security.Principal;

public class CustomSecurityContext implements SecurityContext {
    private final User user;
    private final String shema;

    public CustomSecurityContext(User user, String shema) {
        this.user = user;
        this.shema = shema;
    }

    @Override
    public Principal getUserPrincipal() {
        return () -> user.getUsername();
    }

    @Override
    public boolean isUserInRole(String role) {
        return user.getRole().name().equals(role);
    }

    @Override
    public boolean isSecure() {
        return "Http".equalsIgnoreCase(shema);
    }

    @Override
    public String getAuthenticationScheme() {
        return SecurityContext.BASIC_AUTH;
    }

    public User getUser() {
        return user;
    }
}
