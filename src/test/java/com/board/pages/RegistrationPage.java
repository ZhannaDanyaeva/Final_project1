package com.board.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationPage extends BasePage {

    // Упрощенные и более надежные локаторы
    private SelenideElement emailInput = $("input[type='text'], [placeholder*='Введите Email'], [name*='email']");
    private SelenideElement usernameInput = $("input[name='username'], [placeholder*='имя'], [placeholder*='Имя']");
    private SelenideElement passwordInput = $("input[type='password'], [placeholder*='Пароль'], [name*='password']");
    private SelenideElement confirmPasswordInput = $("input[type='password'], [placeholder*='Повторите пароль'], [name*='submitPassword']");
    private SelenideElement registerButton = $("button[type='submit'], [text='Создать аккаунт']");
    private SelenideElement errorMessage = $("span[text='Создать аккаунт']");

    public void enterEmail(String email) {
        emailInput.setValue(email);
    }

    public void enterUsername(String username) {
        usernameInput.setValue(username);
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

    public void register(String email, String username, String password) {
        enterEmail(email);
        enterUsername(username);
        enterPassword(password);
        enterConfirmPassword(password);
        clickRegisterButton();
    }

    public SelenideElement getErrorMessage() {
        return errorMessage;
    }
}