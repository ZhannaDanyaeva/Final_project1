package com.board.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class BasePage {

    protected SelenideElement findElementByText(String text) {
        return $x(".//*[text()='" + text + "']"); // Используем $x для XPath
    }

    protected SelenideElement findElementByPlaceholder(String placeholder) {
        return $("[placeholder='" + placeholder + "']");
    }

    protected SelenideElement findElementByXpath(String xpath) {
        return $x(xpath);
    }

    protected SelenideElement findElementByPartialText(String partialText) {
        return $x(".//*[contains(text(), '" + partialText + "')]");
    }
}