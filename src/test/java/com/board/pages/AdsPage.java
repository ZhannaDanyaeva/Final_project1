package com.board.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.visible;

public class AdsPage extends BasePage {

    // ---------- АВТОРИЗАЦИЯ ----------
    private SelenideElement signInButton = $("button:contains('Вход'), button:contains('регистра')");
    private SelenideElement emailInput = $("input[placeholder*='Email'], input[type='email']");
    private SelenideElement passwordInput = $("input[type='password'], input[placeholder*='Пароль']");
    private SelenideElement loginButton = $("button[type='submit'], button:contains('Войти')");

    // Элемент, который появляется после успешного логина
    private SelenideElement userProfileIcon =
            $("img[alt*='avatar'], .user-info, .profile, a[href*='profile'], button:contains('Выйти')");

    // ---------- ОБЪЯВЛЕНИЯ ----------
    private SelenideElement createAdButton = $("button:contains('Разместить'), a:contains('Разместить')");
    private SelenideElement titleInput = $("input[name='title'], input[placeholder*='назв']");
    private SelenideElement descriptionInput = $("textarea, [placeholder*='опис']");
    private SelenideElement priceInput = $("input[name='price'], input[placeholder*='цен']");
    private SelenideElement categorySelect = $("select[name='category']");
    private SelenideElement submitButton = $("button:contains('Создать')");
    private SelenideElement saveButton = $("button:contains('Сохранить')");

    // ---------- ЛОГИН ----------
    public void clickSignIn() {
        signInButton.click();
    }

    public void enterEmail() {
        emailInput.setValue("jdanyaeva@yandex.ru");
    }

    public void enterPassword() {
        passwordInput.setValue("123456");
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    // ---------- ПРОВЕРКА ЛОГИНА ----------
    public boolean isUserLoggedIn() {
        return userProfileIcon.shouldBe(visible).isDisplayed();
    }

    // ---------- СОЗДАНИЕ ОБЪЯВЛЕНИЯ ----------
    public void clickCreateAdButton() {
        createAdButton.click();
    }

    public void enterTitle(String title) {
        titleInput.setValue(title);
    }

    public void enterDescription(String description) {
        descriptionInput.setValue(description);
    }

    public void enterPrice(int price) {
        priceInput.setValue(String.valueOf(price));
    }

    public void selectCategory(String category) {
        categorySelect.selectOption(category);
    }

    public void clickSubmitButton() {
        submitButton.click();
    }

    public void clickSaveButton() {
        saveButton.click();
    }

    public void clickEditAd(String title) {
        $(".edit-button, [href*='edit']").click();
    }

    public void clickDeleteAd(String title) {
        $(".delete-button, [onclick*='delete']").click();
    }

    public void confirmDelete() {
        $(".confirm-delete, .btn-danger").click();
    }

    public void createAd(String title, String description, String category, int price) {
        clickSignIn();
        enterEmail();
        enterPassword();
        clickLoginButton();
        isUserLoggedIn();
        clickCreateAdButton();
        enterTitle(title);
        enterDescription(description);
        selectCategory(category);
        enterPrice(price);
        clickSubmitButton();
    }
}
