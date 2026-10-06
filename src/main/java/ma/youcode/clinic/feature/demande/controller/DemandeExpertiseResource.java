package ma.youcode.clinic.feature.demande.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ma.youcode.clinic.feature.demande.dto.CreateDemandeExpertiseRequestDTO;
import ma.youcode.clinic.feature.demande.service.DemandeExpertiseService;

import java.util.Map;

@Path("/demande")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DemandeExpertiseResource {
    @Inject
    private DemandeExpertiseService demandeExpertiseService;

    @POST
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
}
