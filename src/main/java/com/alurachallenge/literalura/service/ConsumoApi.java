package com.alurachallenge.literalura.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoApi {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public String obternerDatos(String url) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = null;

        try{
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        }catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error al obtener los datos de la API", e);
        }

        String json = response.body();
        if (json == null || json.isEmpty()) {
            throw new RuntimeException("La respuesta de la API está vacía");
        }

        try {
            // Convertir el JSON a un JsonNode
            JsonNode rootNode = objectMapper.readTree(json);
            JsonNode resultsNode = rootNode.get("results");

            if (resultsNode == null || !resultsNode.isArray() || resultsNode.size() == 0) {
                throw new RuntimeException("\n****************************************\n¡No existe el título del Libro o el nombre del Autor en la API! \n*************************************\n");
            }

            JsonNode primerLibroNode = resultsNode.get(0); // Obtener el primer libro de la lista
            return primerLibroNode.toString();
        } catch (IOException e) {
            throw new RuntimeException("Error al procesar el JSON de la API", e);
        }
    }
}
