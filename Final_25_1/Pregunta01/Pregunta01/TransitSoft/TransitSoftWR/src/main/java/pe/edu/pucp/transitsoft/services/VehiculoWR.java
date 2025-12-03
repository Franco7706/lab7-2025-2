package pe.edu.pucp.transitsoft.services;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import pe.edu.pucp.transitsoftbusiness.VehiculoBO;

@Path("vehiculos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VehiculoWR {
    
    private VehiculoBO vehiculoBO;

    public VehiculoWR(){
        vehiculoBO = new VehiculoBO();
    }
    
    @GET
    @Path("{placa}/conductor")
    public Response listarConductoresVehiculo(@PathParam("placa") String placa){
        return Response.ok(vehiculoBO.listarConductoresVehiculo(placa)).build();
    }
}
