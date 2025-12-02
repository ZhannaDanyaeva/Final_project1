package com.board.steps;

import com.board.pages.LoginPage;
import com.board.utils.ApiClient;
import com.board.utils.DataGenerator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Condition.visible;

public class LoginSteps {

    private LoginPage loginPage;
//    private LoginPage logOutPage;

    private String registeredEmail;
    private String registeredPassword = "Test123!";

    @Given("a registered user exists")
    public void registeredUserExists() {
        registeredEmail = DataGenerator.generateRandomEmail();
        String username = "user_" + DataGenerator.generateRandomString(6);
    }

    @Given("the user is on the login page")
    public void userIsOnLoginPage() {
        Selenide.open("https://qa-desk.stand.praktikum-services.ru/login");
        loginPage = new LoginPage();
    }

    @When("the user enters valid credentials")
    public void userEntersValidCredentials() {
        loginPage.login(registeredEmail, registeredPassword);
    }

    @Then("the user is successfully logged in")
    public void userIsSuccessfullyLoggedIn() {
        loginPage.getLogOutButton().shouldBe(visible);
    }
}