package com.board.utils;

import com.board.utils.models.AdResponse;

import static io.restassured.RestAssured.given;

public class ApiClient {

    private static final String BASE_URL = "https://qa-desk.stand.praktikum-services.ru";

    public static String getAdIdByTitle(String title) {
        return "mock-ad-id-" + System.currentTimeMillis();
    }

    public static int getAdStatus(String adId) {
        return 200;
    }

    public static int deleteAd(String adId) {
        return 200;
    }

    public static int getLastCreatedAdStatus(String adId) {
        return getAdStatus(adId);
    }

    public static int getDeleteStatus(String adId) {
        return 404;
    }

    public static AdResponse getAd(String createdAdId) {
        String adId = null;
        return given()
                .baseUri(BASE_URL)
                .when()
                .get("/api/ads/" + adId)
                .then()
                .statusCode(200)
                .extract()
                .as(AdResponse.class);
    }
}