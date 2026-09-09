package com.example.productcatalog.controller;

import com.example.productcatalog.service.GeminiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private GeminiService geminiService;

    /**
     * Endpoint to analyze product images via Gemini Multimodal AI
     */
    @PostMapping(value = "/analyze-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> analyzeImage(@RequestParam("file") MultipartFile file) {
        String resultJson = geminiService.analyzeProductImage(file);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(resultJson);
    }
}