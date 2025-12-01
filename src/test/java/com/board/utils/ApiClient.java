package com.board.utils;

import com.board.utils.models.AdResponse;
import com.board.utils.models.AuthResponse;
import com.board.utils.models.RegisterRequest;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiClient {

    private static final String BASE_URL = "/https://qa-desk.stand.praktikum-services.ru/api";

    public static String getAdIdByTitle(String title) {
        Response response = given()
                .baseUri(BASE_URL)
                .when()
                .get("/create-listing")
                .then()
                .statusCode(200)
                .extract().response();

        List<Map<String, Object>> ads = response.jsonPath().getList("items");

        for (Map<String, Object> ad : ads) {
            if (title.equals(ad.get("title"))) {
                return ad.get("id").toString();
            }
        }
        return null;
    }

    public static int getAdStatus(String adId) {
        return given()
                .baseUri(BASE_URL)
                .when()
                .get("/create-listing/" + adId)
                .andReturn()
                .statusCode();
    }


    public static int deleteAd(String adId, String token) {
        return given()
                .baseUri(BASE_URL)
                .header("Authorization", "Bearer " + token)
                .when()
                .delete("/create-listing/" + adId)
                .then()
                .extract()
                .statusCode();
    }


    public static AdResponse getAd(String adId) {
        return given()
                .baseUri(BASE_URL)
                .when()
                .get("/create-listing/" + adId)
                .then()
                .statusCode(200)
                .extract()
                .as(AdResponse.class);
    }

    public static AuthResponse register(String name, String email, String password) {
        return given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .body(new RegisterRequest(name, email, password))
                .when()
                .post("/auth/registration")
                .then()
                .statusCode(201)
                .extract()
                .as(AuthResponse.class);
    }

    public static AuthResponse login(String email, String password) {
        return given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .body(Map.of(
                        "email", email,
                        "password", password
                ))
                .when()
                .post("/signin")
                .then()
                .statusCode(200)
                .extract()
                .as(AuthResponse.class);
    }


    public static String createAd(String title, String description, String token) {
        return given()
                .baseUri(BASE_URL)
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(Map.of(
                        "title", title,
                        "description", description
                ))
                .when()
                .post("/create-listing")
                .then()
                .statusCode(201)
                .extract()
                .jsonPath()
                .getString("id");
    }

}
