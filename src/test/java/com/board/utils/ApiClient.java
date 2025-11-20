package com.board.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class ApiClient {
    private static final String BASE_URL = "https://your-board-service.com/api";

    static {
        RestAssured.baseURI = BASE_URL;
    }

    public static Response registerUser(String email, String password, String username) {
        Map<String, String> userData = new HashMap<>();
        userData.put("email", email);
        userData.put("password", password);
        userData.put("username", username);

        return RestAssured.given()
                .contentType("application/json")
                .body(userData)
                .when()
                .post("/auth/register");
    }

    public static String loginAndGetToken(String email, String password) {
        Map<String, String> loginData = new HashMap<>();
        loginData.put("email", email);
        loginData.put("password", password);

        Response response = RestAssured.given()
                .contentType("application/json")
                .body(loginData)
                .when()
                .post("/auth/login");

        return response.jsonPath().getString("token");
    }
}