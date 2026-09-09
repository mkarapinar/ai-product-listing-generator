# 📸 AI-Powered Product Listing & Catalog System

A full-stack web application built with **Spring Boot** and **Next.js** that uses **Google Gemini Multimodal AI** to automatically extract product details (Title, Category, and Marketing Description) directly from uploaded product images.

---

## ✨ Key Features

- **AI Vision Analysis**: Upload an image to auto-generate title, category, and sales copy.
- **RESTful API**: Clean Spring Boot architecture handling file multipart processing and AI integrations.
- **Environment Security**: Safe API Key handling via external configuration without committing secrets.
- **Responsive UI**: Modern dashboard built with Next.js (App Router), TypeScript, and Tailwind CSS.

---

## 🛠️ Tech Stack

- **Backend**: Java 17+, Spring Boot 3.x, Spring Web, RestTemplate
- **AI Integration**: Google Gemini API (`gemini-3.6-flash`)
- **Frontend**: Next.js 14+ (App Router), TypeScript, Tailwind CSS

---

## ⚡ Setup & Installation

### Prerequisites
- Java JDK 17+
- Node.js 18+
- Gemini API Key from [Google AI Studio](https://aistudio.google.com/)

### 1. Backend Setup
1. Open `product-catalog-backend` in your IDE.
2. Set your Gemini API key in `src/main/resources/application.properties`:
   ```properties
   gemini.api.key=YOUR_GEMINI_API_KEY