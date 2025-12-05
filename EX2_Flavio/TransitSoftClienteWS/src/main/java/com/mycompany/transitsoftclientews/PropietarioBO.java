package com.mycompany.transitsoftclientews;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import pe.pucp.transitsoft.model.PropietarioDTO;

public class PropietarioBO extends BOImplBase<PropietarioDTO> {

    public PropietarioBO() {
        super("http://localhost:8080/TransitSoftServidorRESTFUL/resources/Propietarios");
    }

    public Integer insertar(String dni, String nombres, String apellidos, String direccion) throws JsonProcessingException, IOException, InterruptedException {
        this.crearHttpClient();
        
        PropietarioDTO propietarioDTO = this.crearDTO(null, dni, nombres, apellidos, direccion);
        String jsonRequest = this.serializarDTO(propietarioDTO);
        
        this.crearHttpRequestPOST(jsonRequest);
        this.enviarRequest();
        
        PropietarioDTO propietarioRespuesta = this.deserializar(PropietarioDTO.class);
        this.cerrarHttpClient();

        if (this.response.statusCode() == Response.Status.CREATED.getStatusCode()) {
            return propietarioRespuesta.getId();
        }
        return 0;
    }

    public Integer modificar(Integer id, String dni, String nombres, String apellidos, String direccion) throws JsonProcessingException, IOException, InterruptedException {
        this.crearHttpClient();
        
        PropietarioDTO propietarioDTO = this.crearDTO(id, dni, nombres, apellidos, direccion);
        String jsonRequest = this.serializarDTO(propietarioDTO);
        
        this.crearHttpRequestPUT(jsonRequest);
        this.enviarRequest();
        this.cerrarHttpClient();

        if (this.response.statusCode() == Response.Status.OK.getStatusCode()) {
            return id;
        }
        return 0;
    }

    public Integer eliminar(Integer id) throws IOException, InterruptedException {
        this.crearHttpClient();
        this.crearHttpRequestDELETE(id);
        this.enviarRequest();
        this.cerrarHttpClient();
        
        if (this.response.statusCode() == Response.Status.NO_CONTENT.getStatusCode()) {
            return 1;
        }
        return 0;
    }

    public PropietarioDTO obtenerPorId(Integer id) throws IOException, InterruptedException {
        this.crearHttpClient();
        this.crearHttpRequestGET(id);
        this.enviarRequest();
        PropietarioDTO propietario = this.deserializar(PropietarioDTO.class);
        this.cerrarHttpClient();
        return propietario;
    }

    public ArrayList<PropietarioDTO> listarTodos() throws IOException, InterruptedException {
        this.crearHttpClient();
        this.crearHttpRequestGET(); // Versión sin ID
        this.enviarRequest();
        ArrayList<PropietarioDTO> lista = this.deserializarListaDTO(new TypeReference<ArrayList<PropietarioDTO>>() {});
        this.cerrarHttpClient();
        return lista;
    }

    // Helper privado para armar el DTO
    private PropietarioDTO crearDTO(Integer id, String dni, String nombres, String apellidos, String direccion) {
        PropietarioDTO dto = new PropietarioDTO();
        dto.setId(id);
        dto.setDni(dni);
        dto.setNombres(nombres);
        dto.setApellidos(apellidos);
        dto.setDireccion(direccion);
        return dto;
    }
}