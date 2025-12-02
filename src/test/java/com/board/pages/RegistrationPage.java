package com.board.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class RegistrationPage extends BasePage {

    private SelenideElement noAcc = $x("//button[text()='Нет аккаунта']");
    private SelenideElement emailInput = $x("//input[contains(@placeholder, 'Email') or @type='email']");
    private SelenideElement passwordInput = $x("//input[contains(@placeholder, 'Пароль') or @type='password']");
    private SelenideElement confirmPasswordInput = $x("//input[contains(@placeholder, 'Повторите пароль') or @name='submitPassword']");
    private SelenideElement registerButton = $x("//button[contains(text(), 'Создать аккаунт')]");
    private SelenideElement errorMessage = $x("span[contains(text(), 'Ошибка')]");

    public void enterEmail(String email) {
        emailInput.setValue(email);
    }

    public void enterPassword(String password) {
        passwordInput.setValue(password);
    }

    public void enterConfirmPassword(String password) {
        confirmPasswordInput.setValue(password);
    }

    public void clickRegisterButton() {
        registerButton.click();
    }
    public void clickNoAccButton() {
        noAcc.click();
    }


    public void register(String email, String password) {
        clickNoAccButton();
        enterEmail(email);
        enterPassword(password);
        enterConfirmPassword(password);
        clickRegisterButton();
    }

    public SelenideElement getErrorMessage() {
        return errorMessage;
    }


}