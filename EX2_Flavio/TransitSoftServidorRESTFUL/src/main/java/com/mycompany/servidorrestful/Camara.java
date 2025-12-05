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
import pe.pucp.transitsoft.bo.CamaraBO;
import pe.pucp.transitsoft.model.CamaraDTO;

@Path("Camaras")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Camara {

    private CamaraBO camaraBO;

    public Camara() {
        this.camaraBO = new CamaraBO();
    }

    @POST
    public Response insertarCamara(CamaraDTO camaraDTO) {        
        Integer respuesta = this.camaraBO.insertar(camaraDTO);        
        if (respuesta == 0)
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        return Response.status(Response.Status.CREATED).entity(camaraDTO).build();
    }
    
    @PUT
    public Response modificarCamara(CamaraDTO camaraDTO) {  
        Integer respuesta = this.camaraBO.modificar(camaraDTO);
        if (respuesta == 0)
            return Response.status(Response.Status.NOT_MODIFIED).build();
        return Response.ok(camaraDTO).build();
    }
    
    @DELETE
    @Path("{id}")
    public Response eliminarCamara(@PathParam("id") Integer id) {
        Integer respuesta = this.camaraBO.eliminar(id);
        if (respuesta > 0)
            return Response.noContent().build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    
    @GET
    public ArrayList<CamaraDTO> listarTodosCamara() {
        return this.camaraBO.listarTodos();
    }

    @GET
    @Path("{id}")
    public Response obtenerPorIdCamara(@PathParam("id") Integer id) {
        CamaraDTO camara = this.camaraBO.obtenerPorId(id);
        if (camara == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(camara).build();
    }
}