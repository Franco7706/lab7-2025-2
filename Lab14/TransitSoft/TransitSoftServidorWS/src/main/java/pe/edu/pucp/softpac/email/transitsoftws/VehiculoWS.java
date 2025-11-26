package pe.edu.pucp.softpac.email.transitsoftws;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import pe.pucp.transitsoft.bo.VehiculoBO;
import pe.pucp.transitsoft.model.VehiculoDTO;


@Path("Vehiculos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VehiculoWS {
    
    private VehiculoBO vehiculoBO;
    
    public VehiculoWS(){
        vehiculoBO = new VehiculoBO();
    }
    
    @POST
    @Path("Propietario")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response listarPorPropietario(String descripcion_propietario){
        System.out.println("====== ENTRÓ AL MÉTODO ======");
        System.out.println("Parámetro recibido: [" + descripcion_propietario + "]");
        if(descripcion_propietario==null || descripcion_propietario.isEmpty()){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        ArrayList<VehiculoDTO> vehiculos = vehiculoBO.listarPorPropietario(descripcion_propietario);
        if(vehiculos==null || vehiculos.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(vehiculos).build();
    }
}