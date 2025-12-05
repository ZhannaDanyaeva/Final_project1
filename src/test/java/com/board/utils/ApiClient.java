package com.board.utils;

import com.board.config.Config;
import com.board.utils.models.AdResponse;
import com.board.utils.models.AuthResponse;
import com.board.utils.models.Credentials;
import com.board.utils.models.RegisterRequest;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiClient {

    public static String getAdIdByTitle(String name, String token) {
        Response response = given()
                .baseUri(Config.BASE_URL)
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/listings/")
                .then()
                .statusCode(200)
                .extract().response();

        List<Map<String, Object>> ads = response.jsonPath().getList("items");

        for (Map<String, Object> ad : ads) {
            if (name.equals(ad.get("name"))) {
                return ad.get("id").toString();
            }
        }
        return null;
    }

    public static int getAdStatus(String adId) {
        return given()
                .baseUri(Config.BASE_URL)
                .when()
                .get("/listings/" + adId)
                .andReturn()
                .statusCode();
    }


    public static int deleteAd(String adId, String token) {
        Response response = given()
                .baseUri(Config.BASE_URL)
                .header("Authorization", "Bearer " + token)
                .when()
                .delete("/listings/" + adId)
                .then()
                .extract()
                .response();
        return response.getStatusCode();

    }


    public static AdResponse getAd(String adId) {
        return given()
                .baseUri(Config.BASE_URL)
                .when()
                .get("/listings/" + adId)
                .then()
                .statusCode(200)
                .extract()
                .as(AdResponse.class);
    }

    public static AuthResponse register(String email, String password, String submitPassword) {
        return given()
                .baseUri(Config.BASE_URL)
                .header("Content-Type", "application/json")
                .body(new RegisterRequest(email, password, submitPassword))
                .when()
                .post("/signup")
                .then()
                .statusCode(201)
                .extract()
                .as(AuthResponse.class);
    }

    public static AuthResponse login(Credentials creds) {
        return given()
                .baseUri(Config.BASE_URL)
                .header("Content-Type", "application/json")
                .body(creds)
                .when()
                .post("/signin")
                .then()
                .statusCode(201)
                .extract()
                .as(AuthResponse.class);
    }


    public static String createAd(String name, String description, int price, String category, String token) {
        return given()
                .baseUri(Config.BASE_URL)
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(Map.of(
                        "name", name,
                        "description", description,
                        "price", price,
                        "category", category
                ))
                .when()
                .post("/listings")
                .then()
                .statusCode(201)
                .extract()
                .jsonPath()
                .getString("id");
    }


}
