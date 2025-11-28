package com.board.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiClient {

    public static String getAdIdByTitle(String title) {
        System.out.println("Simulating API call to find ad by title: " + title);
        return "mock-ad-id-" + System.currentTimeMillis();
    }

    public static int getAdStatus(String adId) {
        System.out.println("Simulating API call to get ad status for: " + adId);
        return 200;
    }

    public static int deleteAd(String adId) {
        System.out.println("Simulating API call to delete ad: " + adId);
        return 200;
    }

    public static int getLastCreatedAdStatus(String adId) {
        return getAdStatus(adId);
    }

    public static int getDeleteStatus(String adId) {
        System.out.println("Simulating API call to check deletion status for: " + adId);
        return 404;
    }
}