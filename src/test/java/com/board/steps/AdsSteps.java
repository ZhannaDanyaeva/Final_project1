package com.board.steps;

import com.board.pages.AdsPage;
import com.board.utils.ApiClient;
import com.board.utils.DataGenerator;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static org.junit.jupiter.api.Assertions.*;

public class AdsSteps {

    private final AdsPage adsPage = new AdsPage();
    private final SharedTestContext context;

    public AdsSteps(SharedTestContext context) {
        this.context = context;
    }

    @Given("the user is logged in to the system")
    public void userIsLoggedIn() {

        Selenide.open("https://qa-desk.stand.praktikum-services.ru");

        if (adsPage.getUserProfileButton().exists() &&
                adsPage.getUserProfileButton().isDisplayed()) {
            var auth = ApiClient.login("jdanyaeva@yandex.ru", "123456");
            context.token = auth.accessToken;
            return;
        }

        adsPage.clickSignIn();
        adsPage.enterEmail("jdanyaeva@yandex.ru");
        adsPage.enterPassword("123456");
        adsPage.clickLoginButton();

        adsPage.getUserProfileButton().shouldBe(Condition.visible);
        var auth = ApiClient.login("jdanyaeva@yandex.ru", "123456");
        context.token = auth.accessToken;
    }

    @When("the user creates a new advertisement")
    public void userCreatesNewAd() {

        context.adData.clear();
        context.adData.put("title", DataGenerator.generateRandomTitle());
        context.adData.put("description", DataGenerator.generateRandomDescription());
        context.adData.put("price", DataGenerator.generateRandomPrice());
        context.adData.put("category", "Книги");

        adsPage.createAd(
                (String) context.adData.get("title"),
                (String) context.adData.get("description"),
                (String) context.adData.get("category"),
                (int) context.adData.get("price")
        );

        adsPage.getSuccessBanner().shouldBe(Condition.visible);

        context.createdAdId = ApiClient.getAdIdByTitle((String) context.adData.get("title"));
        assertNotNull(context.createdAdId, "Created ad ID was not found via API");
    }

    @When("the user edits their advertisement")
    public void userEditsHisAd() {

        assertNotNull(context.adData.get("title"), "No ad created before editing");

        context.updatedData.clear();
        context.updatedData.put("title", "Updated " + context.adData.get("title"));
        context.updatedData.put("description", "Updated " + context.adData.get("description"));
        context.updatedData.put("price", ((int) context.adData.get("price")) + 100);

        adsPage.openUserProfile();
        adsPage.clickEditAdByTitle((String) context.adData.get("title"));

        adsPage.enterTitle((String) context.updatedData.get("title"));
        adsPage.enterDescription((String) context.updatedData.get("description"));
        adsPage.enterPrice((int) context.updatedData.get("price"));
        adsPage.clickSaveButton();

        adsPage.getSuccessBanner().shouldBe(Condition.visible);

        context.adData.putAll(context.updatedData);
    }


    @When("the user deletes their advertisement")
    public void userDeletesHisAd() {
        assertNotNull(context.createdAdId, "Ad ID is missing");

        int status = ApiClient.deleteAd(context.createdAdId, context.token);
        assertEquals(200, status, "Delete request failed");
    }


    @Then("the advertisement is successfully created")
    public void adIsSuccessfullyCreated() {
        adsPage.openUserProfile();
        adsPage.openMyAds();
        adsPage.shouldSeeAdWithTitle(context.adData.get("title"));
        int status = ApiClient.getAdStatus(context.createdAdId);
        assertEquals(200, status, "Ad was not created or not found via API");
    }

    @Then("the advertisement is successfully edited")
    public void adIsSuccessfullyEdited() {

        var response = ApiClient.getAd(context.createdAdId);

        assertEquals(context.updatedData.get("title"), response.getTitle());
        assertEquals(context.updatedData.get("description"), response.getDescription());
        assertEquals(context.updatedData.get("price"), response.getPrice());
    }

    @Then("the advertisement is successfully deleted")
    public void adIsSuccessfullyDeleted() {

        int status = ApiClient.getAdStatus(context.createdAdId);
        assertEquals(404, status, "Ad still exists after deletion");
    }
}
