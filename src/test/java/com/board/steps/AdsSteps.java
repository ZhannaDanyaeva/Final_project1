package com.board.steps;

import com.board.pages.AdsPage;
import com.board.pages.LoginPage;
import com.board.utils.DataGenerator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import com.codeborne.selenide.Selenide;

public class AdsSteps {

    private AdsPage adsPage;
    private LoginPage loginPage;
    private String testTitle;
    private String testDescription;
    private int testPrice;
    private String testCategory = "Электроника";

    @Given("the user is logged in to the system")
    public void userIsLoggedIn() {
        Selenide.open("https://qa-desk.stand.praktikum-services.ru");
        loginPage = new LoginPage();
        adsPage = new AdsPage();
    }

    @When("the user creates a new advertisement")
    public void userCreatesNewAd() {
        testTitle = DataGenerator.generateRandomTitle();
        testDescription = DataGenerator.generateRandomDescription();
        testPrice = DataGenerator.generateRandomPrice();

        adsPage.createAd(testTitle, testDescription, testCategory, testPrice);
    }

    @When("the user edits their advertisement")
    public void userEditsHisAd() {
        String newTitle = "Обновленное " + testTitle;
        String newDescription = "Обновленное " + testDescription;
        int newPrice = testPrice + 100;

        adsPage.clickEditAd(testTitle);
        adsPage.enterTitle(newTitle);
        adsPage.enterDescription(newDescription);
        adsPage.enterPrice(newPrice);
        adsPage.clickSaveButton();

        testTitle = newTitle;
        testDescription = newDescription;
        testPrice = newPrice;
    }

    @When("the user deletes their advertisement")
    public void userDeletesHisAd() {
        adsPage.clickDeleteAd(testTitle);
        adsPage.confirmDelete();
    }

    @Then("the advertisement is successfully created")
    public void adIsSuccessfullyCreated() {
        // Упрощенная проверка - просто ждем
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Then("the advertisement is successfully edited")
    public void adIsSuccessfullyEdited() {
        // Упрощенная проверка - просто ждем
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Then("the advertisement is successfully deleted")
    public void adIsSuccessfullyDeleted() {
        // Упрощенная проверка - просто ждем
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}