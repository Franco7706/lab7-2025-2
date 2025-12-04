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
import pe.edu.pucp.transitsoft.bo.VehiculoBO;
import pe.edu.pucp.transitsoft.model.VehiculoDTO;


@Path("Vehiculos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VehiculoWS {
    
    private VehiculoBO vehiculoBO;
    
    public VehiculoWS(){
        vehiculoBO = new VehiculoBO();
    }
    
    @POST
    public Response insertarOModificar(VehiculoDTO vehiculoDTO, @QueryParam("motor") String motor){  
        Integer respuesta = this.vehiculoBO.insertarOModificar(vehiculoDTO, motor);
        if (respuesta==-1) // Modificado
             return Response.ok(vehiculoDTO).build();
        if (respuesta!=0) // Creado
            return Response.status(Response.Status.CREATED).entity(vehiculoDTO).build();
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}