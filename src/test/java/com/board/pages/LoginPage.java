package com.board.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends BasePage {

    // ---- КОРРЕКТНЫЕ И НАДЁЖНЫЕ ЛОКАТОРЫ ----

    private SelenideElement emailInput =
            $("input[placeholder*='Email'], input[type='email']");

    private SelenideElement passwordInput =
            $("input[type='password'], input[placeholder*='Пароль']");

    private SelenideElement loginButton =
            $("button[type='submit'], button:contains('Войти')");

    private SelenideElement registerLink =
            $("button:contains('Нет аккаунта'), a:contains('Нет аккаунта')");


    // ---- ДЕЙСТВИЯ ----

    public void enterEmail(String email) {
        emailInput.setValue(email);
    }

    public void enterPassword(String password) {
        passwordInput.setValue(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void clickRegisterLink() {
        registerLink.click();
    }

    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }
}
