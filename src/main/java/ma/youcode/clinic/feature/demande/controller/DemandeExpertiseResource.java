package ma.youcode.clinic.feature.demande.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import ma.youcode.clinic.feature.auth.repository.UserRepository;
import ma.youcode.clinic.feature.demande.dto.CreateDemandeExpertiseRequestDTO;
import ma.youcode.clinic.feature.demande.service.DemandeExpertiseService;
import ma.youcode.clinic.model.entity.DemandeExpertise;
import ma.youcode.clinic.model.entity.User;

import java.util.List;
import java.util.Map;

@Path("/demande")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DemandeExpertiseResource {
    @Inject
    private DemandeExpertiseService demandeExpertiseService;

    @Inject
    private SecurityContext securityContext;

    @Inject
    private UserRepository userRepository;

    @POST
    @RolesAllowed("GENERALIST")
    public Response createDemande(CreateDemandeExpertiseRequestDTO requestDTO) {
        Map<String , String> errors = demandeExpertiseService.creatDemande(requestDTO);

        if (!errors.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(errors)
                    .build();
        }

        return Response.status(Response.Status.CREATED)
                .entity("Demande created with success.")
                .build();
    }

    @GET
    public Response getDemandes(@QueryParam("statut") String statut, @QueryParam("consultationId") Long consultationId) {
        String username = securityContext.getUserPrincipal().getName();

        User user = userRepository.findByUsername(username);

        if (user == null) {
            return Response.status(Response.Status.FORBIDDEN)
                    .entity("User doesn't exist.")
                    .build();
        }

        // GENERALIST
        if (consultationId != null) {

            if (!securityContext.isUserInRole("GENERALIST")) {
                return Response.status(Response.Status.FORBIDDEN)
                        .entity("Access denied.")
                        .build();
            }

            List<DemandeExpertise> demandes =
                    demandeExpertiseService.getConsultationDemande(consultationId);

            return Response.ok(demandes).build();
        }

        // SPECIALISTE
        if (statut != null) {

            if (!securityContext.isUserInRole("SPECIALISTE")) {
                return Response.status(Response.Status.FORBIDDEN)
                        .entity("Access denied.")
                        .build();
            }

            List<DemandeExpertise> demandes =
                    demandeExpertiseService.getSpecialisteDemande(
                            user.getId(),
                            statut
                    );

            return Response.ok(demandes).build();
        }

        return Response.status(Response.Status.BAD_REQUEST)
                .entity("statut or consultationId is required.")
                .build();
    }
}
