package pe.edu.pucp.transitsoft.bo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.core.Response;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Properties;
import pe.pucp.transitsoft.model.PropietarioDTO;
import pe.pucp.transitsoft.model.VehiculoDTO;

public class VehiculoBO {
    private final String ARCHIVO_CONFIGURACION="service.properties";
    private HttpClient cliente;
    private HttpRequest request;
    private HttpResponse<String> response;
    private String url;
    private ObjectMapper mapper;
    
    public VehiculoBO(){
        this.url = obtenerUrl();
        this.mapper = new ObjectMapper();
    }
    
    private String obtenerUrl() {
        Properties properties = new Properties();
        String url;
        try {
            String nmArchivoConf = "/" + ARCHIVO_CONFIGURACION;
            properties.load(this.getClass().getResourceAsStream(nmArchivoConf));
            url = properties.getProperty("url");
            return url;
        } catch (FileNotFoundException ex) {
            System.err.println("Error al leer el archivo de propiedades - " + ex);
        } catch (IOException ex) {
            System.err.println("Error al leer el archivo de propiedades - " + ex);
        }
        return "";
    }
    
     public ArrayList<VehiculoDTO> listarPorPropietario(String descripcion_propietario) throws JsonProcessingException, IOException, InterruptedException{        
        this.crearHttpClient();
        this.crearHttpRequestPOST(descripcion_propietario,"text/plain",this.url + "/Propietario");
        this.enviarRequest();
        ArrayList<VehiculoDTO> vehiculos = this.deserializarListaDTO(new TypeReference<ArrayList<VehiculoDTO>>(){});                
        this.cerrarHttpClient();
        if (response.statusCode() == Response.Status.OK.getStatusCode())
            return vehiculos;
        return new ArrayList<>();                
    }
     
         
    private void crearHttpClient() {
        this.cliente = HttpClient.newHttpClient();
    }

    private void cerrarHttpClient() {
        this.cliente.close();
    }

    private VehiculoDTO crearDTO(Integer id, String placa, String marca, String modelo, Integer anho, PropietarioDTO propietario) {
        VehiculoDTO vehiculoDTO = new VehiculoDTO();
        vehiculoDTO.setId(id);
        vehiculoDTO.setPlaca(placa);
        vehiculoDTO.setMarca(marca);
        vehiculoDTO.setModelo(modelo);
        vehiculoDTO.setAnho(anho);
        vehiculoDTO.setPropietario(propietario);
        return vehiculoDTO;
    }

    private String serializarDTO(VehiculoDTO vehiculoDTO) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonRequest = mapper.writeValueAsString(vehiculoDTO);
        return jsonRequest;
    }

    private void crearHttpRequestPUT(String jsonRequest) {
        this.request = HttpRequest.newBuilder()
                .uri(URI.create(this.url))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(jsonRequest))
                .build();
    }

    private void crearHttpRequestPOST(String jsonRequest) {
        crearHttpRequestPOST(jsonRequest, "application/json",this.url);
    }
    
    private void crearHttpRequestPOST(String jsonRequest,String contentType,String url) {
        this.request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", contentType)
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                .build();
    }


    private void enviarRequest() throws IOException, InterruptedException {
        this.response = this.cliente.send(this.request, HttpResponse.BodyHandlers.ofString());
    }

    private VehiculoDTO deserializarDTO(Class<VehiculoDTO> clase) throws JsonProcessingException {
        String json = this.response.body();
        VehiculoDTO almacenDTORespuesta = this.mapper.readValue(json, clase);        
        return almacenDTORespuesta;
    }
    
    private ArrayList<VehiculoDTO> deserializarListaDTO(TypeReference<ArrayList<VehiculoDTO>> typeReference) throws JsonProcessingException {
        String json = this.response.body();
        ArrayList<VehiculoDTO> listaAlmacenes = this.mapper.readValue(json, typeReference);
        return listaAlmacenes;
    }

    private void crearHttpRequestDELETE(Integer almacenId) {
        //AZUL
        this.request = HttpRequest.newBuilder()
                .uri(URI.create(this.url + "/" + almacenId))
                .header("Content-Type", "application/json")
                .DELETE()
                .build();
    }

    private void crearHttpRequestGET() {
        Integer almacenId = null;
        this.crearHttpRequestGET(almacenId);
    }
    
    private void crearHttpRequestGET(Integer almacenId) {
        String URLGET = this.url;
        if (almacenId != null){
            URLGET = URLGET.concat("/" + almacenId);
        }
        //AZUL
        this.request = HttpRequest.newBuilder()
                .uri(URI.create(URLGET))
                .header("Content-Type", "application/json")
                .GET()
                .build();
    }    
}
