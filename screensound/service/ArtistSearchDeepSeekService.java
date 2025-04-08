package com.freesong.screensound.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArtistSearchDeepSeekService {

    private final OkHttpClient client;
    private final String apiKey;
    private final String apiUrl;

    public ArtistSearchDeepSeekService(
            @Value("${deepseek.api.key}") String apiKey,
            @Value("${deepseek.api.url}") String apiUrl) {
        this.client = new OkHttpClient();
        this.apiKey = apiKey;
        this.apiUrl = apiUrl;
    }

    public List<String> searchArtistData(String artistName) {
        // Monta o prompt para a API
        String prompt = "Forneça informações detalhadas sobre o artista musical '" + artistName + "', incluindo biografia, gênero musical e principais obras.";

        // Cria o corpo da requisição
        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("model", "meta-llama/llama-3.1-8b-instruct:free"); // Modelo alternativo

        JsonArray messages = new JsonArray();
        JsonObject systemMessage = new JsonObject();
        systemMessage.addProperty("role", "system");
        systemMessage.addProperty("content", "Você é um assistente útil especializado em informações sobre artistas musicais.");
        messages.add(systemMessage);

        JsonObject userMessage = new JsonObject();
        userMessage.addProperty("role", "user");
        userMessage.addProperty("content", prompt);
        messages.add(userMessage);

        requestBody.add("messages", messages);
        requestBody.addProperty("max_tokens", 500);

        // Monta a requisição HTTP
        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"),
                requestBody.toString()
        );

        Request request = new Request.Builder()
                .url(apiUrl + "/chat/completions")
                .addHeader("Authorization", "Bearer " + apiKey)
                .addHeader("Content-Type", "application/json")
                .post(body)
                .build();

        // Faz a chamada à API
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                String errorBody = response.body() != null ? response.body().string() : "No response body";
                throw new IOException("Erro na requisição: " + response.code() + " - " + response.message() + " - Corpo: " + errorBody);
            }

            // Processa a resposta
            String responseBody = response.body().string();
            System.out.println("Resposta da API: " + responseBody); // Log para depuração
            JsonObject jsonResponse = new Gson().fromJson(responseBody, JsonObject.class);
            String content = jsonResponse.getAsJsonArray("choices")
                    .get(0).getAsJsonObject()
                    .getAsJsonObject("message")
                    .get("content").getAsString();

            // Divide a resposta em linhas para facilitar a exibição
            List<String> results = new ArrayList<>();
            for (String line : content.split("\n")) {
                if (!line.trim().isEmpty()) {
                    results.add(line.trim());
                }
            }
            return results;
        } catch (Exception e) {
            List<String> errorResult = new ArrayList<>();
            errorResult.add("Erro ao buscar dados do artista: " + e.getMessage());
            return errorResult;
        }
    }
}