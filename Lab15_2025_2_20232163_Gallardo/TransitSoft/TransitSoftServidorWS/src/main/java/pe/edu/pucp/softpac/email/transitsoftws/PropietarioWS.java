package pe.edu.pucp.softpac.email.transitsoftws;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import pe.edu.pucp.transitsoft.bo.PropietarioBO;
import pe.edu.pucp.transitsoft.model.PropietarioDTO;
import pe.edu.pucp.transitsoft.model.VehiculoDTO;


@Path("Propietarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PropietarioWS {
    
    private PropietarioBO propietarioBO;
    
    public PropietarioWS(){
        propietarioBO = new PropietarioBO();
    }
    
    @GET
    @Path("{id}")
    public Response obtenerPropietarioVehiculo(@PathParam("id") Integer vehiculoId, @QueryParam("motor") String motor){
        PropietarioDTO propietario = this.propietarioBO.obtenerPropietarioVehiculo(vehiculoId, motor);
        if (propietario==null){
           return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(propietario).build();
    }
}