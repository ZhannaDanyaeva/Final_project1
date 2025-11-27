package com.board.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiClient {
    private static final String BASE_URL = "https://qa-desk.stand.praktikum-services.ru/api";

    static {
        RestAssured.baseURI = BASE_URL;
    }

    private static RequestSpecification getRequest() {
        return RestAssured.given()
                .contentType("application/json")
                .log().all();
    }

    // Получение токена авторизации
    public static String loginAndGetToken(String email, String password) {
        Map<String, String> loginData = new HashMap<>();
        loginData.put("email", email);
        loginData.put("password", password);

        Response response = getRequest()
                .body(loginData)
                .when()
                .post("/auth/login");

        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Login failed. Status: " + response.getStatusCode());
        }

        return response.jsonPath().getString("token");
    }

    // Создание объявления через API
    public static String createAd(String title, String description, String category, int price) {
        String token = loginAndGetToken("jdanyaeva@yandex.ru", "123456");

        Map<String, Object> adData = new HashMap<>();
        adData.put("title", title);
        adData.put("description", description);
        adData.put("category", category);
        adData.put("price", price);

        Response response = getRequest()
                .header("Authorization", "Bearer " + token)
                .body(adData)  // RestAssured автоматически сериализует Map в JSON
                .when()
                .post("/create-listing");

        System.out.println("Create Ad Response: " + response.getStatusCode());
        System.out.println("Create Ad Body: " + response.getBody().asString());

        if (response.getStatusCode() != 201) {
            throw new RuntimeException("Failed to create ad. Status: " + response.getStatusCode());
        }

        return response.jsonPath().getString("id");
    }

    // Получение статуса объявления
    public static int getAdStatus(String adId) {
        Response response = getRequest()
                .when()
                .get("/create-listing/" + adId);

        System.out.println("Get Ad Status for ID " + adId + ": " + response.getStatusCode());
        return response.getStatusCode();
    }

    // Удаление объявления
    public static int deleteAd(String adId) {
        String token = loginAndGetToken("jdanyaeva@yandex.ru", "123456");

        Response response = getRequest()
                .header("Authorization", "Bearer " + token)
                .when()
                .delete("/create-listing/" + adId);

        System.out.println("Delete Ad Response: " + response.getStatusCode());
        return response.getStatusCode();
    }

    // Получение статуса удаления (должен вернуть 404 после удаления)
    public static int getDeleteStatus(String adId) {
        return getAdStatus(adId);
    }

    // Получение статуса последнего созданного объявления
    public static int getLastCreatedAdStatus(String adId) {
        return getAdStatus(adId);
    }

    // Поиск ID объявления по названию
    public static String getAdIdByTitle(String title) {
        String token = loginAndGetToken("jdanyaeva@yandex.ru", "123456");

        Response response = getRequest()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/create-listing");

        System.out.println("Get All Ads Status: " + response.getStatusCode());
        System.out.println("Get All Ads Body: " + response.getBody().asString());

        // Парсим ответ чтобы найти объявление по title
        List<Map<String, Object>> ads = response.jsonPath().getList("");

        System.out.println("Searching for ad with title: '" + title + "'");
        System.out.println("Total ads found: " + (ads != null ? ads.size() : 0));

        if (ads != null) {
            for (Map<String, Object> ad : ads) {
                String adTitle = (String) ad.get("title");
                Object adId = ad.get("id");
                System.out.println("Available ad: '" + adTitle + "' (ID: " + adId + ")");

                if (title.equals(adTitle)) {
                    System.out.println("Found matching ad with ID: " + adId);
                    return adId.toString();
                }
            }
        }

        System.out.println("Ad with title '" + title + "' not found");
        return null;
    }

    // Альтернативный метод получения ID - создаем через API и сразу получаем ID
    public static String createAdAndGetId(String title, String description, String category, int price) {
        return createAd(title, description, category, price);
    }
}