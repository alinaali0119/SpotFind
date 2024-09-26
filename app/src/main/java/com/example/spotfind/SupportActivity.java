package com.example.spotfind;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SupportActivity extends AppCompatActivity {

    private RecyclerView recyclerViewChat;
    private ChatAdapter chatAdapter;
    private List<ChatMessage> chatMessageList;
    private EditText editTextMessage;
    private Button buttonSend;
    private ApiClient apiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_support);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerViewChat = findViewById(R.id.recyclerView_chat);
        editTextMessage = findViewById(R.id.editText_message);
        buttonSend = findViewById(R.id.button_send);

        // Initialize chat message list and adapter
        chatMessageList = new ArrayList<>();
        chatAdapter = new ChatAdapter(chatMessageList);
        recyclerViewChat.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewChat.setAdapter(chatAdapter);

        // Initialize the API client
        apiClient = new ApiClient();

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = editTextMessage.getText().toString().trim();
                if (!message.isEmpty()) {
                    // Add user message to chat
                    chatMessageList.add(new ChatMessage(message, ChatMessage.SENDER_USER));
                    chatAdapter.notifyDataSetChanged();
                    recyclerViewChat.scrollToPosition(chatMessageList.size() - 1);

                    // Clear the input field
                    editTextMessage.setText("");

                    // Call a method to send the message to the chatbot and get a response
                    getChatbotResponse(message);
                }
            }
        });
    }

    private void getChatbotResponse(String message) {
        // Run the network request on a background thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String response = apiClient.getChatbotResponse(message);

                    // Update the UI with the chatbot response
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            chatMessageList.add(new ChatMessage(response, ChatMessage.SENDER_BOT));
                            chatAdapter.notifyDataSetChanged();
                            recyclerViewChat.scrollToPosition(chatMessageList.size() - 1);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();

                    // Show an error message
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(SupportActivity.this, "Failed to get chatbot response", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).start();
    }

    // ApiClient class handles the API requests
    private class ApiClient {
        private static final String API_URL = "https://api.openai.com/v1/chat/completions";
        private static final String API_KEY = "your_openai_api_key_here"; // Replace with your OpenAI API key

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

            // Create the list of messages
            JsonArray messagesArray = new JsonArray();
            JsonObject userMessageObject = new JsonObject();
            userMessageObject.addProperty("role", "user");
            userMessageObject.addProperty("content", userMessage);
            messagesArray.add(userMessageObject);

            requestBodyJson.add("messages", messagesArray);

            RequestBody requestBody = RequestBody.create(
                    MediaType.get("application/json; charset=utf-8"),
                    requestBodyJson.toString()
            );

            // Build the request
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
}
