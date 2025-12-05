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
import pe.edu.pucp.transitsoft.model.PropietarioDTO;

public class PropietarioBO {
    private final String ARCHIVO_CONFIGURACION="service.properties";
    private HttpClient cliente;
    private HttpRequest request;
    private HttpResponse<String> response;
    private String url;
    private ObjectMapper mapper;
    
    public PropietarioBO(){
        this.url = obtenerUrl();
        this.mapper = new ObjectMapper();
    }
    
    private String obtenerUrl() {
        Properties properties = new Properties();
        String url;
        try {
            String nmArchivoConf = "/" + ARCHIVO_CONFIGURACION;
            properties.load(this.getClass().getResourceAsStream(nmArchivoConf));
            url = properties.getProperty("urlpropietarios");
            return url;
        } catch (FileNotFoundException ex) {
            System.err.println("Error al leer el archivo de propiedades - " + ex);
        } catch (IOException ex) {
            System.err.println("Error al leer el archivo de propiedades - " + ex);
        }
        return "";
    }
    
    public PropietarioDTO obtenerPropietarioVehiculo(Integer vehiculoId) throws JsonProcessingException, IOException, InterruptedException{        
        this.crearHttpClient();
        this.crearHttpRequestGET(vehiculoId);
        this.enviarRequest();
        PropietarioDTO propietario = this.deserializarDTO(PropietarioDTO.class);                
        this.cerrarHttpClient();
        if (response.statusCode() != Response.Status.NOT_FOUND.getStatusCode())
            return propietario;
        return new PropietarioDTO();                
    }
     
         
    private void crearHttpClient() {
        this.cliente = HttpClient.newHttpClient();
    }

    private void cerrarHttpClient() {
        this.cliente.close();
    }

    private PropietarioDTO crearDTO(Integer id, String dni, String nombres, String apellidos, String direccion) {
        PropietarioDTO propietarioDTO = new PropietarioDTO();
        propietarioDTO.setId(id);
        propietarioDTO.setDni(dni);
        propietarioDTO.setNombres(nombres);
        propietarioDTO.setApellidos(apellidos);
        propietarioDTO.setDireccion(direccion);
        
        return propietarioDTO;
    }

    private String serializarDTO(PropietarioDTO propietarioDTO) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonRequest = mapper.writeValueAsString(propietarioDTO);
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

    private PropietarioDTO deserializarDTO(Class<PropietarioDTO> clase) throws JsonProcessingException {
        String json = this.response.body();
        PropietarioDTO propietarioDTORespuesta = this.mapper.readValue(json, clase);        
        return propietarioDTORespuesta;
    }
    
    private ArrayList<PropietarioDTO> deserializarListaDTO(TypeReference<ArrayList<PropietarioDTO>> typeReference) throws JsonProcessingException {
        String json = this.response.body();
        ArrayList<PropietarioDTO> listaAlmacenes = this.mapper.readValue(json, typeReference);
        return listaAlmacenes;
    }

    private void crearHttpRequestDELETE(Integer propietarioId) {
        //AZUL
        this.request = HttpRequest.newBuilder()
                .uri(URI.create(this.url + "/" + propietarioId))
                .header("Content-Type", "application/json")
                .DELETE()
                .build();
    }

    private void crearHttpRequestGET(Integer vehiculoId, String qparam) {
        String URLGET = this.url;
        if (vehiculoId != null){
            URLGET = URLGET.concat("/" + vehiculoId + qparam);
        }
        //AZUL
        this.request = HttpRequest.newBuilder()
                .uri(URI.create(URLGET))
                .header("Content-Type", "application/json")
                .GET()
                .build();
    }
    
    private void crearHttpRequestGET() {
        Integer propietarioId = null;
        this.crearHttpRequestGET(propietarioId);
    }
    
    private void crearHttpRequestGET(Integer propietarioId) {
        String URLGET = this.url;
        if (propietarioId != null){
            URLGET = URLGET.concat("/" + propietarioId);
        }
        //AZUL
        this.request = HttpRequest.newBuilder()
                .uri(URI.create(URLGET))
                .header("Content-Type", "application/json")
                .GET()
                .build();
    }    
}
