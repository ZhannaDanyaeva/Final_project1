package com.board.steps;

import com.board.pages.RegistrationPage;
import com.board.utils.DataGenerator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Condition.visible;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationSteps {

    private RegistrationPage registrationPage;
    private String testEmail;
    private String testUsername;
    private final String testPassword = "Test123!";

    @Given("the user is on the registration page")
    public void userIsOnRegistrationPage() {
        Selenide.open("https://qa-desk.stand.praktikum-services.ru/register");
        registrationPage = new RegistrationPage();
    }

    @When("the user registers with a unique email")
    public void userRegistersWithUniqueEmail() {
        testEmail = DataGenerator.generateRandomEmail();
        testUsername = "user_" + DataGenerator.generateRandomString(6);

        registrationPage.register(testEmail, testUsername, testPassword);
    }

    @When("the user tries to register with an existing email")
    public void userTriesToRegisterWithExistingEmail() {
        registrationPage.register(testEmail, "another_user", testPassword);
    }

    @Then("the registration is successful")
    public void registrationIsSuccessful() {
        // Check if redirected to main page or success message appears
        assertTrue(Selenide.webdriver().driver().url().contains("/ads") ||
                Selenide.webdriver().driver().url().contains("/login"));
    }

    @Then("an error message about registration is displayed")
    public void errorMessageIsDisplayed() {
        registrationPage.getErrorMessage().shouldBe(visible);
    }
}