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
import pe.pucp.transitsoft.bo.CapturaBO;
import pe.pucp.transitsoft.model.CapturaDTO;

@Path("Capturas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Captura {

    private CapturaBO capturaBO;

    public Captura() {
        this.capturaBO = new CapturaBO();
    }

    @POST
    public Response insertarCaptura(CapturaDTO capturaDTO) {        
        Integer respuesta = this.capturaBO.insertar(capturaDTO);        
        if (respuesta == 0)
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        return Response.status(Response.Status.CREATED).entity(capturaDTO).build();
    }
    
    @PUT
    public Response modificarCaptura(CapturaDTO capturaDTO) {  
        Integer respuesta = this.capturaBO.modificar(capturaDTO);
        if (respuesta == 0)
            return Response.status(Response.Status.NOT_MODIFIED).build();
        return Response.ok(capturaDTO).build();
    }
    
    @DELETE
    @Path("{id}")
    public Response eliminarCaptura(@PathParam("id") Integer id) {
        Integer respuesta = this.capturaBO.eliminar(id);
        if (respuesta > 0)
            return Response.noContent().build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    
    @GET
    public ArrayList<CapturaDTO> listarTodosCaptura() {
        return this.capturaBO.listarTodos();
    }

    @GET
    @Path("{id}")
    public Response obtenerPorIdCaptura(@PathParam("id") Integer id) {
        CapturaDTO captura = this.capturaBO.obtenerPorId(id);
        if (captura == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(captura).build();
    }
}