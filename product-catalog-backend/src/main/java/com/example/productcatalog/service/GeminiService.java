package com.example.productcatalog.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import java.util.*;

@Service
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Sends the uploaded image to Google Gemini Multimodal API
     * and receives JSON formatted title, category, and description.
     */
    public String analyzeProductImage(MultipartFile file) {
        // Gemini API endpoint URL using latest gemini-2.5-flash model
        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-3.6-flash:generateContent?key=" + apiKey.trim();

        try {
            // Encode binary image data to Base64
            String base64Image = Base64.getEncoder().encodeToString(file.getBytes());
            String mimeType = file.getContentType() != null ? file.getContentType() : "image/jpeg";

            // Prompt asking for structured JSON output
            String promptText = "Analyze this product image. Provide a JSON response with three fields: 'name' (short title), 'category' (e.g. Electronics, Fashion), and 'description' (a 2-sentence catchy pitch in English). Return ONLY raw JSON without markdown codeblock syntax.";

            Map<String, Object> textPart = Map.of("text", promptText);
            Map<String, Object> imagePart = Map.of(
                    "inline_data", Map.of("mime_type", mimeType, "data", base64Image)
            );

            Map<String, Object> contents = Map.of("contents", List.of(
                    Map.of("parts", List.of(textPart, imagePart))
            ));

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(contents, headers);
            ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);

            // Extract the string response from API structure
            List candidates = (List) response.getBody().get("candidates");
            Map candidate = (Map) candidates.get(0);
            Map content = (Map) candidate.get("content");
            List partsList = (List) content.get("parts");
            Map firstPart = (Map) partsList.get(0);

            return (String) firstPart.get("text");
        } catch (Exception e) {
            return "{\"error\": \"" + e.getMessage().replace("\"", "'") + "\"}";
        }
    }
}