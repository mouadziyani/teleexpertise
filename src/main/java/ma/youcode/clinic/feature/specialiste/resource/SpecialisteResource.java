package ma.youcode.clinic.feature.specialiste.resource;

import java.util.List;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import ma.youcode.clinic.feature.auth.repository.UserRepository;
import ma.youcode.clinic.feature.specialiste.service.SpecialisteService;
import ma.youcode.clinic.model.entity.Specialiste;
import ma.youcode.clinic.model.entity.User;
import ma.youcode.clinic.model.enums.Specialite;

@Path("/specialistes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SpecialisteResource {
    @Inject 
    private SpecialisteService service;
    @Context
    private SecurityContext securityContext;
    @Inject
    private UserRepository userRepository;

    @GET
    @RolesAllowed("GENERALIST")
    public Response listSpecialiste(@QueryParam("specialite") Specialite specialite){
        String username = securityContext.getUserPrincipal().getName();

        User user = userRepository.findByUsername(username);

        if (user == null) {
            return Response.status(Response.Status.FORBIDDEN)
                    .entity("User doesn't exist.")
                    .build();
        }
        List<Specialiste> specialistes = service.listSpesialiste(specialite);
        return Response.ok(specialistes).build();
    }
}
