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
    private String email;
    private String password = "123456";
//    private final String testPassword = "Test123!";

    @Given("the user is on the registration page")
    public void userIsOnRegistrationPage() {
        Selenide.open("https://qa-desk.stand.praktikum-services.ru/registration");
        registrationPage = new RegistrationPage();
    }

    @When("the user registers with a unique email")
    public void userRegistersWithUniqueEmail() {
        email = DataGenerator.generateRandomEmail();
        registrationPage.register(email, password);
    }

    @When("the user tries to register with an existing email")
    public void userTriesToRegisterWithExistingEmail() {
        registrationPage.register(email, password);
    }

    @Then("the registration is successful")
    public void registrationIsSuccessful() {
        assertTrue(Selenide.webdriver().driver().url().contains("/registration") ||
                Selenide.webdriver().driver().url().contains("/regiatration"));
    }

    @Then("an error message about registration is displayed")
    public void errorMessageIsDisplayed() {
        registrationPage.getErrorMessage().shouldBe(visible);
    }
}