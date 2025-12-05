package com.mycompany.transitsoftclientews;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import pe.pucp.transitsoft.model.InfraccionDTO;

public class InfraccionBO extends BOImplBase<InfraccionDTO> {

    public InfraccionBO() {
        super("http://localhost:8080/TransitSoftServidorRESTFUL/resources/Infracciones");
    }

    // Este método recibe MUCHOS parámetros porque la tabla es denormalizada (historial)
    public Integer insertar(String placa, Double velocidad, Double limite, Double exceso, 
                            String marcaVehiculo, String modeloVehiculo, Integer anhoVehiculo,
                            String dniPropietario, String nombresPropietario, String apellidosPropietario, String direccionPropietario,
                            String modeloCamara, String codigoSerieCamara, Long latitud, Long longitud,
                            Double monto, Date fechaCaptura, Date fechaRegistro) throws JsonProcessingException, IOException, InterruptedException {
        
        this.crearHttpClient();
        
        InfraccionDTO dto = this.crearDTO(placa, velocidad, limite, exceso, marcaVehiculo, modeloVehiculo, anhoVehiculo, 
                                          dniPropietario, nombresPropietario, apellidosPropietario, direccionPropietario, 
                                          modeloCamara, codigoSerieCamara, latitud, longitud, monto, fechaCaptura, fechaRegistro);
        
        String jsonRequest = this.serializarDTO(dto);
        
        this.crearHttpRequestPOST(jsonRequest);
        this.enviarRequest();
        this.cerrarHttpClient();

        // Como el DTO no tiene ID, solo verificamos el status HTTP
        if (this.response.statusCode() == Response.Status.CREATED.getStatusCode()) {
            return 1; // Éxito
        }
        return 0;
    }

    public ArrayList<InfraccionDTO> listarTodos() throws IOException, InterruptedException {
        this.crearHttpClient();
        this.crearHttpRequestGET();
        this.enviarRequest();
        ArrayList<InfraccionDTO> lista = this.deserializarListaDTO(new TypeReference<ArrayList<InfraccionDTO>>() {});
        this.cerrarHttpClient();
        return lista;
    }
    
    // Helper gigante para la tabla de hechos
    private InfraccionDTO crearDTO(String placa, Double velocidad, Double limite, Double exceso, 
                                   String marcaVehiculo, String modeloVehiculo, Integer anhoVehiculo,
                                   String dniPropietario, String nombresPropietario, String apellidosPropietario, String direccionPropietario,
                                   String modeloCamara, String codigoSerieCamara, Long latitud, Long longitud,
                                   Double monto, Date fechaCaptura, Date fechaRegistro) {
        
        InfraccionDTO dto = new InfraccionDTO();
        dto.setPlaca(placa);
        dto.setVelocidad(velocidad);
        dto.setLimite(limite);
        dto.setExceso(exceso);
        dto.setMarcaVehiculo(marcaVehiculo);
        dto.setModeloVehiculo(modeloVehiculo);
        dto.setAnhoVehiculo(anhoVehiculo);
        dto.setDniPropietario(dniPropietario);
        dto.setNombresPropietario(nombresPropietario);
        dto.setApellidosPropietario(apellidosPropietario);
        dto.setDireccionPropietario(direccionPropietario);
        dto.setModeloCamara(modeloCamara);
        dto.setCodigoSerieCamara(codigoSerieCamara);
        dto.setLatitud(latitud);
        dto.setLongitud(longitud);
        dto.setMonto(monto);
        dto.setFechaCaptura(fechaCaptura);
        dto.setFechaRegistro(fechaRegistro);
        
        return dto;
    }
}