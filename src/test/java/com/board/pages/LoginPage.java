package com.board.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage extends BasePage {

    private SelenideElement emailInput =
            $x("//input[contains(@placeholder, 'Email') or @type='email']");

    private SelenideElement passwordInput =
            $x("//input[contains(@placeholder, 'Пароль') or @type='password']");

    private SelenideElement loginButton =
            $x("//button[contains(@class,'buttonPrimary') and normalize-space()='Войти']");

    private SelenideElement registerLink =
            $x("//button[contains(text(), 'Нет аккаунта')]");

    private SelenideElement logOutButton =
            $x("//button[contains(text(), 'Выйти')]");

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


    public void shouldBe(Condition visible) {
        logOutButton.shouldBe(visible);
    }

    public void closeModalIfVisible() {
        SelenideElement modal = $(".homePage_modal__zSdUB");
        if (modal.is(Condition.visible)) {
            modal.click();
        }
    }

    public SelenideElement getLogOutButton() {
        return logOutButton;
    }
}
