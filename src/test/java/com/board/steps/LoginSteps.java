package com.board.steps;

import com.board.config.Config;
import com.board.pages.LoginPage;
import com.board.utils.ApiClient;
import com.board.utils.DataGenerator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import com.codeborne.selenide.Selenide;

import static com.board.config.Config.LOGIN_PAGE;
import static com.codeborne.selenide.Condition.visible;

public class LoginSteps {

    private LoginPage loginPage;

    private String registeredEmail;
    private String registeredPassword;

    @Given("a registered user exists")
    public void registeredUserExists() {
        registeredEmail = DataGenerator.generateRandomEmail();
        registeredPassword = "123456";

        var username = "user_" + DataGenerator.generateRandomString(6);
        var registerResponse = ApiClient.register(registeredEmail, registeredPassword, username);

        if (registerResponse.statusCode() != 201) {
            throw new RuntimeException("User was not registered! Status: " + registerResponse.statusCode());
        }
    }

    @Given("the user is on the login page")
    public void userIsOnLoginPage() {
        Selenide.open(Config.LOGIN_PAGE);
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