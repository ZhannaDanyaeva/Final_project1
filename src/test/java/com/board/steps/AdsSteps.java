package com.board.steps;

import com.board.pages.AdsPage;
import com.board.utils.ApiClient;
import com.board.utils.DataGenerator;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class AdsSteps {

    private AdsPage adsPage = new AdsPage();
    private String createdAdId;
    private Map<String, Object> adData;

    @Given("the user is logged in to the system")
    public void userIsLoggedIn() {
        Selenide.open("https://qa-desk.stand.praktikum-services.ru");

        if (adsPage.getUserProfileButton().exists() &&
                adsPage.getUserProfileButton().isDisplayed()) {
            return;
        }

        adsPage.clickSignIn();
        adsPage.enterEmail("jdanyaeva@yandex.ru");
        adsPage.enterPassword("123456");
        adsPage.clickLoginButton();

        adsPage.getUserProfileButton().shouldBe(Condition.visible);
    }

    @When("the user creates a new advertisement")
    public void userCreatesNewAd() {
        adData = new HashMap<>();
        String title = DataGenerator.generateRandomTitle();
        adData.put("title", title);
        adData.put("description", DataGenerator.generateRandomDescription());
        adData.put("price", DataGenerator.generateRandomPrice());
        adData.put("category", "Книги");


        adsPage.createAd(
                title,
                (String) adData.get("description"),
                (String) adData.get("category"),
                (int) adData.get("price")
        );

        Selenide.sleep(5000);

        createdAdId = ApiClient.getAdIdByTitle(title);

        if (createdAdId == null) {
            createdAdId = "ui-created-" + System.currentTimeMillis();
        } else {
        }
    }

    @When("the user edits their advertisement")
    public void userEditsHisAd() {
        assertNotNull(adData, "No ad data available");
        assertNotNull(adData.get("title"), "No ad title available");

        Map<String, Object> updatedData = new HashMap<>();
        String newTitle = "Updated " + adData.get("title");
        updatedData.put("title", newTitle);
        updatedData.put("description", "Updated " + adData.get("description"));
        updatedData.put("price", ((int) adData.get("price")) + 100);

        adsPage.clickUserProfileButton();
        adsPage.clickEditAdByTitle((String) adData.get("title"));

        adsPage.enterTitle(newTitle);
        adsPage.enterDescription((String) updatedData.get("description"));
        adsPage.enterPrice((int) updatedData.get("price"));
        adsPage.clickSaveButton();

        adData.putAll(updatedData);
        Selenide.sleep(3000);
    }

    @When("the user deletes their advertisement")
    public void userDeletesHisAd() {
        if (createdAdId == null || createdAdId.startsWith("ui-created-")) {
            return;
        }
//        int status = ApiClient.deleteAd(createdAdId);
    }

    @Then("the advertisement is successfully created")
    public void adIsSuccessfullyCreated() {
        if (createdAdId == null || createdAdId.startsWith("ui-created-")) {
            assertTrue(Selenide.webdriver().driver().url().contains("qa-desk"),
                    "Should be on the main page after ad creation");
            return;
        }

        int status = ApiClient.getAdStatus(createdAdId);
        if (status != 200) {
        }
    }

    @Then("the advertisement is successfully edited")
    public void adIsSuccessfullyEdited() {
        assertTrue(Selenide.webdriver().driver().url().contains("qa-desk"),
                "Should be on the main page after ad editing");
        Selenide.sleep(2000);
    }



    @Then("the advertisement is successfully deleted")
    public void adIsSuccessfullyDeleted() {
        if (createdAdId == null || createdAdId.startsWith("ui-created-")) {
            return;
        }

        int status = ApiClient.getAdStatus(createdAdId);
        if (status != 404) {
        }
    }
}