package com.mycompany.servidorrestful;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import pe.pucp.transitsoft.bo.InfraccionBO;
import pe.pucp.transitsoft.model.InfraccionDTO;

@Path("Infracciones")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Infraccion {

    private InfraccionBO infraccionBO;

    public Infraccion() {
        this.infraccionBO = new InfraccionBO();
    }

    @POST
    public Response insertarInfraccion(InfraccionDTO infraccionDTO) {        
        Integer respuesta = this.infraccionBO.insertar(infraccionDTO);        
        if (respuesta == 0)
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        return Response.status(Response.Status.CREATED).entity(infraccionDTO).build();
    }
    
    @GET
    public ArrayList<InfraccionDTO> listarTodosInfraccion() {
        return this.infraccionBO.listarTodos();
    }
}