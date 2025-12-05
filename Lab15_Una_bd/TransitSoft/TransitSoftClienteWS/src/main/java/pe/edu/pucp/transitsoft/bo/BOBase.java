package pe.edu.pucp.transitsoft.bo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Properties;

public abstract class BOBase<T> {
    private final String ARCHIVO_CONFIGURACION="service.properties";
    protected HttpClient cliente;
    protected HttpRequest request;
    protected HttpResponse<String> response;
    protected String url;
    protected ObjectMapper mapper;

    public BOBase(String propiedad) {
        this.url = obtenerUrl(propiedad);
        this.mapper = new ObjectMapper();
    }

    protected void crearHttpClient() {
        this.cliente = HttpClient.newHttpClient();
    }

    protected void cerrarHttpClient() {
        this.cliente = null; 
    }

    protected void crearHttpRequestPOST(String jsonRequest) {
        this.request = HttpRequest.newBuilder()
                .uri(URI.create(this.url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                .build();
    }

    protected void crearHttpRequestPUT(String jsonRequest) {
        this.request = HttpRequest.newBuilder()
                .uri(URI.create(this.url))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(jsonRequest))
                .build();
    }
    
    protected void crearHttpRequestGET() {
        this.crearHttpRequestGET(null);
    }

    protected void crearHttpRequestGET(Integer id) {
        String urlFinal = this.url;
        if (id != null) {
            urlFinal = urlFinal.concat("/" + id);
        }
        this.request = HttpRequest.newBuilder()
                .uri(URI.create(urlFinal))
                .header("Content-Type", "application/json")
                .GET()
                .build();
    }

    protected void crearHttpRequestDELETE(Integer id) {
        this.request = HttpRequest.newBuilder()
                .uri(URI.create(this.url + "/" + id))
                .header("Content-Type", "application/json")
                .DELETE()
                .build();
    }

    protected void enviarRequest() throws IOException, InterruptedException {
        this.response = this.cliente.send(this.request, HttpResponse.BodyHandlers.ofString());
    }

    protected String serializarDTO(T dto) throws JsonProcessingException {
        return this.mapper.writeValueAsString(dto);
    }

    protected T deserializarDTO(Class<T> clase) throws JsonProcessingException {
        if (this.response.body() == null || this.response.body().isEmpty()) return null;
        return mapper.readValue(response.body(), clase);
    }

    protected ArrayList<T> deserializarListaDTO(TypeReference<ArrayList<T>> typeReference) throws JsonProcessingException {
        if (this.response.body() == null || this.response.body().isEmpty()) return new ArrayList<>();
        return this.mapper.readValue(this.response.body(), typeReference);
    }
    
    private String obtenerUrl(String propiedad) {
        Properties properties = new Properties();
        String url_bo;
        try {
            String nmArchivoConf = "/" + ARCHIVO_CONFIGURACION;
            properties.load(this.getClass().getResourceAsStream(nmArchivoConf));
            url_bo = properties.getProperty(propiedad);
            return url_bo;
        } catch (FileNotFoundException ex) {
            System.err.println("Error al leer el archivo de propiedades - " + ex);
        } catch (IOException ex) {
            System.err.println("Error al leer el archivo de propiedades - " + ex);
        }
        return "";
    }
    
        
    protected void crearHttpRequestPOSTConUrlCustom(String jsonRequest,String url) {
        this.request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                .build();
    }

    protected void crearHttpRequestGETConQueryParam(Integer vehiculoId, String qparam) {
        String URLGET = this.url;
        if (vehiculoId != null){
            URLGET = URLGET.concat("/" + vehiculoId + qparam);
        }
        
        this.request = HttpRequest.newBuilder()
                .uri(URI.create(URLGET))
                .header("Content-Type", "application/json")
                .GET()
                .build();
    }
    
}