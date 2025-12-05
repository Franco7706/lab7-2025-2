package com.mycompany.transitsoftclientews;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import pe.pucp.transitsoft.model.CamaraDTO;
import pe.pucp.transitsoft.model.CapturaDTO;
import pe.pucp.transitsoft.model.EstadoCapturaDTO;
import pe.pucp.transitsoft.model.VehiculoDTO;

public class CapturaBO extends BOImplBase<CapturaDTO> {

    public CapturaBO() {
        super("http://localhost:8080/TransitSoftServidorRESTFUL/resources/Capturas");
    }

    public Integer insertar(Integer idCamara, String placa, Double velocidad, Date fechaCaptura, String estado) throws JsonProcessingException, IOException, InterruptedException {
        this.crearHttpClient();
        
        CapturaDTO capturaDTO = this.crearDTO(null, idCamara, placa, velocidad, fechaCaptura, estado);
        String jsonRequest = this.serializarDTO(capturaDTO);
        
        this.crearHttpRequestPOST(jsonRequest);
        this.enviarRequest();
        
        CapturaDTO capturaRespuesta = this.deserializar(CapturaDTO.class);
        this.cerrarHttpClient();

        if (this.response.statusCode() == Response.Status.CREATED.getStatusCode()) {
            return capturaRespuesta.getId();
        }
        return 0;
    }

    public Integer modificar(Integer id, Integer idCamara, String placa, Double velocidad, Date fechaCaptura, String estado) throws JsonProcessingException, IOException, InterruptedException {
        this.crearHttpClient();
        
        CapturaDTO capturaDTO = this.crearDTO(id, idCamara, placa, velocidad, fechaCaptura, estado);
        String jsonRequest = this.serializarDTO(capturaDTO);
        
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

    public CapturaDTO obtenerPorId(Integer id) throws IOException, InterruptedException {
        this.crearHttpClient();
        this.crearHttpRequestGET(id);
        this.enviarRequest();
        CapturaDTO captura = this.deserializar(CapturaDTO.class);
        this.cerrarHttpClient();
        return captura;
    }

    public ArrayList<CapturaDTO> listarTodos() throws IOException, InterruptedException {
        this.crearHttpClient();
        this.crearHttpRequestGET();
        this.enviarRequest();
        ArrayList<CapturaDTO> lista = this.deserializarListaDTO(new TypeReference<ArrayList<CapturaDTO>>() {});
        this.cerrarHttpClient();
        return lista;
    }

    // Helper para armar la estructura compleja
    private CapturaDTO crearDTO(Integer id, Integer idCamara, String placa, Double velocidad, Date fechaCaptura, String estado) {
        CapturaDTO dto = new CapturaDTO();
        dto.setId(id);
        dto.setPlaca(placa);
        dto.setVelocidad(velocidad);
        dto.setFechaCaptura(fechaCaptura);
        
        // Convertir String a Enum
        if (estado != null) {
            dto.setEstado(EstadoCapturaDTO.valueOf(estado));
        }
        
        // Crear objeto Camara solo con ID (referencia)
        if (idCamara != null) {
            CamaraDTO camara = new CamaraDTO();
            camara.setId(idCamara);
            dto.setCamara(camara);
        }
        
        // El vehículo en CapturaDTO a veces es solo referencial por la placa
        // Depende de tu lógica, aquí inicializamos uno básico con la placa
        if (placa != null) {
            VehiculoDTO vehiculo = new VehiculoDTO();
            vehiculo.setPlaca(placa);
            dto.setVehiculo(vehiculo);
        }
        
        return dto;
    }
}