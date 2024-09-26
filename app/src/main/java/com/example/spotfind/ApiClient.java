package com.example.spotfind;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;

public class ApiClient {
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    private static final String API_KEY = "sk-N3YkJWjhMGDv3kBoidvELFAWU51yMmmRJ5XM97nb84T3BlbkFJWUDnfbso6Zx2lKWovsy7b3MTCw99_EnlKsGHkgvloA"; // Replace with your actual OpenAI API key

    private OkHttpClient client;
    private Gson gson;

    public ApiClient() {
        client = new OkHttpClient();
        gson = new Gson();
    }

    public String getChatbotResponse(String userMessage) throws IOException {
        // Create the request body
        JsonObject requestBodyJson = new JsonObject();
        requestBodyJson.addProperty("model", "gpt-3.5-turbo");
        requestBodyJson.add("messages", gson.toJsonTree(new ChatMessage[] {
                new ChatMessage("user", userMessage)
        }));

        RequestBody requestBody = RequestBody.create(
                MediaType.get("application/json; charset=utf-8"),
                requestBodyJson.toString()
        );

        // Build the request with the Authorization header
        Request request = new Request.Builder()
                .url(API_URL)
                .header("Authorization", "Bearer " + API_KEY)
                .post(requestBody)
                .build();

        // Execute the request and get the response
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            JsonObject jsonResponse = gson.fromJson(response.body().string(), JsonObject.class);
            return jsonResponse.get("choices").getAsJsonArray()
                    .get(0).getAsJsonObject()
                    .get("message").getAsJsonObject()
                    .get("content").getAsString();
        }
    }
}
