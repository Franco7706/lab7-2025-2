package com.mycompany.servidorrestful;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import pe.pucp.transitsoft.bo.VehiculoBO;
import pe.pucp.transitsoft.model.VehiculoDTO;

@Path("Vehiculos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Vehiculo {

    private VehiculoBO vehiculoBO;

    public Vehiculo() {
        this.vehiculoBO = new VehiculoBO();
    }

    @POST
    @Path("crear")
    public Response insertarVehiculo(VehiculoDTO vehiculoDTO){        
        Integer respuesta = this.vehiculoBO.insertar(vehiculoDTO);        
        if (respuesta == 0)
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        return Response.status(Response.Status.CREATED).entity(vehiculoDTO).build();
    }
    
    @POST
    @Path("modificar")
    //@GET
    public Response modificarVehiculo(VehiculoDTO vehiculoDTO){  
        Integer respuesta = this.vehiculoBO.modificar(vehiculoDTO);
        if (respuesta==0)
            return Response.status(Response.Status.NOT_MODIFIED).build();
        return Response.ok(vehiculoDTO).build();
    }
    
    @DELETE
    @Path("{id}")
    public Response eliminarVehiculo(@PathParam("id") Integer id){
        Integer respuesta = this.vehiculoBO.eliminar(id);
        if (respuesta>0)
            return Response.noContent().build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    
    @GET
    public ArrayList<VehiculoDTO> listarTodosVehiculo() {
        return this.vehiculoBO.listarTodos();
    }

    @GET
    @Path("{id}")
    public Response obtenerPorIdVehiculo(@PathParam("id") Integer id) {
        VehiculoDTO vehiculo = this.vehiculoBO.obtenerPorId(id);
        if (vehiculo == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(vehiculo).build();
    }
}
