package pe.edu.pucp.transitsoft.services;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import pe.edu.pucp.transitsoft.model.ConductoresDTO;
import pe.edu.pucp.transitsoftbusiness.ConductorBO;

@Path("conductores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ConductorWR {
    private ConductorBO conductorBO;

    public ConductorWR(){
        conductorBO = new ConductorBO();
    }
    
    @GET
    public Response listarConductores(@QueryParam("tipolicencia")int tipoLicencia){
        return Response.ok(conductorBO.listarConductores(tipoLicencia)).build();
    }
    
    @GET
    @Path("{num_licencia}/vehiculos")
    public Response listarVehiculosConductor(@PathParam("num_licencia") String numeroLicencia){
        return Response.ok(conductorBO.listarVehiculosConductor(numeroLicencia)).build();
    }
    
    @POST
    public Response insertarConductor(ConductoresDTO conductor){
        int id = conductorBO.insertarConductor(conductor);
        if(id==0) return Response.status(Response.Status.BAD_REQUEST).build();
        return Response.ok(id).status(Response.Status.CREATED).build();
    }
    
}
