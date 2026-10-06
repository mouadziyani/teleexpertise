package ma.youcode.clinic.feature.specialiste.resource;

import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ma.youcode.clinic.feature.specialiste.service.SpecialisteService;
import ma.youcode.clinic.model.entity.Specialiste;
import ma.youcode.clinic.model.enums.Specialite;

@Path("/specialistes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SpecialisteResource {
    private SpecialisteService service;

    public SpecialisteResource(SpecialisteService service){
        this.service=service;
    }

    @GET
    public Response listSpecialiste(@QueryParam("specialite") Specialite specialite,@QueryParam("tarif") String tarif){
        List<Specialiste> specialistes = service.listSpesialiste(specialite, tarif);
        return Response.ok(specialistes).build();
    }
}
