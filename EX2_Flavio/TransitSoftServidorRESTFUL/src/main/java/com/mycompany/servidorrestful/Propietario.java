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
import pe.pucp.transitsoft.bo.PropietarioBO;
import pe.pucp.transitsoft.model.PropietarioDTO;

@Path("Propietarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Propietario {

    private PropietarioBO propietarioBO;

    public Propietario() {
        this.propietarioBO = new PropietarioBO();
    }
    @POST
    public Response insertarPropietario(PropietarioDTO propietarioDTO){        
        Integer respuesta = this.propietarioBO.insertar(propietarioDTO);        
        if (respuesta == 0)
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        return Response.status(Response.Status.CREATED).entity(propietarioDTO).build();
    }
    
    @PUT
    public Response modificarPropietario(PropietarioDTO propietarioDTO){  
        Integer respuesta = this.propietarioBO.modificar(propietarioDTO);
        if (respuesta==0)
            return Response.status(Response.Status.NOT_MODIFIED).build();
        return Response.ok(propietarioDTO).build();
    }

    @DELETE
    @Path("{id}")
    public Response eliminarPropietario(@PathParam("id") Integer id){
        Integer respuesta = this.propietarioBO.eliminar(id);
        if (respuesta>0)
            return Response.noContent().build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    public ArrayList<PropietarioDTO> listarTodosPropietario() {
        return this.propietarioBO.listarTodos();
    }

    @GET
    @Path("{id}")
    public Response obtenerPorIdPropietario(@PathParam("id") Integer id) {
        PropietarioDTO propietario = this.propietarioBO.obtenerPorId(id);
        if (propietario == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(propietario).build();
    }
}
