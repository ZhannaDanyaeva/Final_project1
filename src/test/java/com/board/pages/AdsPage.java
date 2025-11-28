package com.board.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.restassured.response.Response;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static io.restassured.RestAssured.given;

public class AdsPage extends BasePage {

    // ---------- АВТОРИЗАЦИЯ ----------
    private SelenideElement signInButton = $x("//button[contains(text(), 'Вход')]");
    private SelenideElement emailInput = $x("//input[contains(@placeholder, 'Email') or @type='email']");
    private SelenideElement passwordInput = $x("//input[contains(@placeholder, 'Пароль') or @type='password']");
    private SelenideElement loginButton = $x("//button[@type='submit' and contains(@class,'buttonPrimary') and normalize-space()='Войти']");

    // Элемент, который появляется после успешного логина
    public SelenideElement userProfileIcon = $x("//*[contains(@class,'circleSmall')]//*[contains(@class,'svgSmall')]");

    private SelenideElement logOutButton =
            $x("//button[contains(@class, 'Выйти') or @type='submit']");

    // ---------- ОБЪЯВЛЕНИЯ ----------
    private SelenideElement createAdButton = $x("//button[contains(text(), 'Разместить объявление')]");
    private SelenideElement titleInput = $x("//input[contains(@placeholder, 'Название') or @type='text']");
    private SelenideElement descriptionInput = $x("//textarea[contains(@placeholder, 'Описание товара') or @name='description']");
    private SelenideElement priceInput = $x("//input[contains(@placeholder, 'Стоимость') or @name='price']");
    // Кнопка для открытия дропдауна
    private SelenideElement categoryDropdownButton = $x("//button[contains(@class,'dropDownMenu_arrowDown')]");

    // Поле с выбранной категорией (readonly input)
    private SelenideElement testAd = $x("button[contains(text(), 'Zhanna Test QA YP')]");
    private SelenideElement buttonPrimary = $x("//button[@type='submit' and text()='Опубликовать']");


    // ---------- ЛОГИН ----------
    public void clickSignIn() {
        signInButton.click();
    }

    public void enterEmail(String mail) {
        emailInput.setValue("jdanyaeva@yandex.ru");
    }

    public void enterPassword(String number) {
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

    public void clickButtonPrimary(){
        buttonPrimary.click();
    }

    public void enterPrice(int price) {
        priceInput.setValue(String.valueOf(price));
    }

    // Метод для выбора категории по тексту
    public void selectCategory(String categoryName) {
        // Клик по кнопке, чтобы открыть дропдаун
        categoryDropdownButton.shouldBe(Condition.visible).click();

        // Найти категорию в выпадающем списке, подождать её видимости
        SelenideElement categoryOption = $x(
                "//div[contains(@class,'dropDownMenu')]//button//span[text()='" + categoryName + "']"
        ).shouldBe(Condition.visible, Duration.ofSeconds(5));
        // Кликнуть по категории
        categoryOption.click();
    }

    public void checkUserProfileIcon() {

        userProfileIcon.shouldBe(visible);
    }

    public void checkLogOutButton() {
        logOutButton.shouldBe(exist);
    }

    public void clickEditAd(String title) {
        $x("//button[@class='editButton']").click();
    }

    public void clickDeleteAd(String title) {
        $(".delete-button, [onclick*='delete']").click();
    }

    public void confirmDelete() {
        $(".confirm-delete, .btn-danger").click();
    }

    public void createAd(String title, String description, String category, int price) {
        if (signInButton.exists() && signInButton.isDisplayed()) {
            clickSignIn();
            enterEmail("jdanyaeva@yandex.ru");
            enterPassword("123456");
            clickLoginButton();
        }

        checkUserProfileIcon();
        checkLogOutButton();
        clickCreateAdButton();
        enterTitle(title);
        enterDescription(description);
        selectCategory(category);
        enterPrice(price);
        clickButtonPrimary();
    }

    public void clickSaveButton() {
        buttonPrimary.click();
    }

    public SelenideElement getUserProfileButton() {
        return userProfileIcon;
    }

    public void clickUserProfileButton(){
        userProfileIcon.click();
    }
    //редактирование объявления

    public void getAd () {
        testAd.shouldBe(visible);
    }
    public void clickEditAdByTitle(String title) {
        String locator = "//div[contains(@class,'card')][.//h3[text()='" + title + "']]//button[contains(@class,'editButton')]";

        $x(locator)
                .shouldBe(Condition.visible)
                .click();
    }


}
