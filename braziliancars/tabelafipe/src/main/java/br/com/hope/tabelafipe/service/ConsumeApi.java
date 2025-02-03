package br.com.hope.tabelafipe.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumeApi {

    public String obtainData(String url) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

        HttpResponse response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException var6) {
            IOException e = var6;
            throw new RuntimeException(e);
        } catch (InterruptedException var7) {
            InterruptedException e = var7;
            throw new RuntimeException(e);
        }

        String json = (String)response.body();
        return json;
    }
}
