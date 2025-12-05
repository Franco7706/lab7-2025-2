package com.mycompany.transitsoftclientews;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public abstract class BOImplBase<T> {

    protected HttpClient cliente;
    protected HttpRequest request;
    protected HttpResponse<String> response;
    protected String url;
    protected ObjectMapper mapper;

    public BOImplBase(String url) {
        this.url = url;
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

    protected T deserializar(Class<T> clase) throws JsonProcessingException {
        if (this.response.body() == null || this.response.body().isEmpty()) return null;
        return mapper.readValue(response.body(), clase);
    }

    protected ArrayList<T> deserializarListaDTO(TypeReference<ArrayList<T>> typeReference) throws JsonProcessingException {
        if (this.response.body() == null || this.response.body().isEmpty()) return new ArrayList<>();
        return this.mapper.readValue(this.response.body(), typeReference);
    }
}