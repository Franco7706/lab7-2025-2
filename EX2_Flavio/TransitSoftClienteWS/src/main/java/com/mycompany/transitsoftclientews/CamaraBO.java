package com.mycompany.transitsoftclientews;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import pe.pucp.transitsoft.model.CamaraDTO;

public class CamaraBO extends BOImplBase<CamaraDTO> {

    public CamaraBO() {
        super("http://localhost:8080/TransitSoftServidorRESTFUL/resources/Camaras");
    }

    public Integer insertar(String modelo, String codigoSerie, Long latitud, Long longitud) throws JsonProcessingException, IOException, InterruptedException {
        this.crearHttpClient();
        
        CamaraDTO camaraDTO = this.crearDTO(null, modelo, codigoSerie, latitud, longitud);
        String jsonRequest = this.serializarDTO(camaraDTO);
        
        this.crearHttpRequestPOST(jsonRequest);
        this.enviarRequest();
        
        CamaraDTO camaraRespuesta = this.deserializar(CamaraDTO.class);
        this.cerrarHttpClient();

        if (this.response.statusCode() == Response.Status.CREATED.getStatusCode()) {
            return camaraRespuesta.getId();
        }
        return 0;
    }

    public Integer modificar(Integer id, String modelo, String codigoSerie, Long latitud, Long longitud) throws JsonProcessingException, IOException, InterruptedException {
        this.crearHttpClient();
        
        CamaraDTO camaraDTO = this.crearDTO(id, modelo, codigoSerie, latitud, longitud);
        String jsonRequest = this.serializarDTO(camaraDTO);
        
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

    public CamaraDTO obtenerPorId(Integer id) throws IOException, InterruptedException {
        this.crearHttpClient();
        this.crearHttpRequestGET(id);
        this.enviarRequest();
        CamaraDTO camara = this.deserializar(CamaraDTO.class);
        this.cerrarHttpClient();
        return camara;
    }

    public ArrayList<CamaraDTO> listarTodos() throws IOException, InterruptedException {
        this.crearHttpClient();
        this.crearHttpRequestGET();
        this.enviarRequest();
        ArrayList<CamaraDTO> lista = this.deserializarListaDTO(new TypeReference<ArrayList<CamaraDTO>>() {});
        this.cerrarHttpClient();
        return lista;
    }

    // Helper privado
    private CamaraDTO crearDTO(Integer id, String modelo, String codigoSerie, Long latitud, Long longitud) {
        CamaraDTO dto = new CamaraDTO();
        dto.setId(id);
        dto.setModelo(modelo);
        dto.setCodigoSerie(codigoSerie);
        dto.setLatitud(latitud);
        dto.setLongitud(longitud);
        return dto;
    }
}