package br.com.oldmovies.oldmovies.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumeApi {
    public String obtainData (String url){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("accept", "application/json")
                .header("Authorization", "Bearer " + System.getenv("TMDB_API_KEY"))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse response;
        try {
             response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Resposta recebida: " + response.statusCode());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            System.out.println("Erro ao consumir a API: " + e.getMessage());
            throw new RuntimeException(e);
        }
        String json = (String) response.body();
        return json;
    }
}
