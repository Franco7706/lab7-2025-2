package com.mycompany.transitsoftclientews;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import pe.pucp.transitsoft.model.PropietarioDTO;
import pe.pucp.transitsoft.model.VehiculoDTO;

public class VehiculoBO extends BOImplBase<VehiculoDTO> {

    public VehiculoBO() {
        super("http://localhost:8080/TransitSoftServidorRESTFUL/resources/Vehiculos");
    }

    public Integer insertar(String placa, String marca, String modelo, Integer anho, Integer idPropietario) throws JsonProcessingException, IOException, InterruptedException {
        this.crearHttpClient();
        
        VehiculoDTO vehiculoDTO = this.crearDTO(null, placa, marca, modelo, anho, idPropietario);
        String jsonRequest = this.serializarDTO(vehiculoDTO);
        
        this.crearHttpRequestPOST(jsonRequest);
        this.enviarRequest();
        
        VehiculoDTO vehiculoRespuesta = this.deserializar(VehiculoDTO.class);
        this.cerrarHttpClient();

        if (this.response.statusCode() == Response.Status.CREATED.getStatusCode()) {
            return vehiculoRespuesta.getId();
        }
        return 0;
    }

    public Integer modificar(Integer id, String placa, String marca, String modelo, Integer anho, Integer idPropietario) throws JsonProcessingException, IOException, InterruptedException {
        this.crearHttpClient();
        
        VehiculoDTO vehiculoDTO = this.crearDTO(id, placa, marca, modelo, anho, idPropietario);
        String jsonRequest = this.serializarDTO(vehiculoDTO);
        
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

    public VehiculoDTO obtenerPorId(Integer id) throws IOException, InterruptedException {
        this.crearHttpClient();
        this.crearHttpRequestGET(id);
        this.enviarRequest();
        VehiculoDTO vehiculo = this.deserializar(VehiculoDTO.class);
        this.cerrarHttpClient();
        return vehiculo;
    }

    public ArrayList<VehiculoDTO> listarTodos() throws IOException, InterruptedException {
        this.crearHttpClient();
        this.crearHttpRequestGET();
        this.enviarRequest();
        ArrayList<VehiculoDTO> lista = this.deserializarListaDTO(new TypeReference<ArrayList<VehiculoDTO>>() {});
        this.cerrarHttpClient();
        return lista;
    }

    // Helper privado
    private VehiculoDTO crearDTO(Integer id, String placa, String marca, String modelo, Integer anho, Integer idPropietario) {
        VehiculoDTO dto = new VehiculoDTO();
        dto.setId(id);
        dto.setPlaca(placa);
        dto.setMarca(marca);
        dto.setModelo(modelo);
        dto.setAnho(anho);
        
        if (idPropietario != null) {
            PropietarioDTO prop = new PropietarioDTO();
            prop.setId(idPropietario);
            dto.setPropietario(prop);
        }
        return dto;
    }
}