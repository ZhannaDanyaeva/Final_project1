package com.board.utils;

import com.codeborne.selenide.Selenide;
import static com.codeborne.selenide.Selenide.*;

public class SiteExplorer {

    public static void exploreRegistrationPage() {
        System.out.println("=== EXPLORING REGISTRATION PAGE ===");
        Selenide.open("https://qa-desk.stand.praktikum-services.ru/register");
        System.out.println("Page title: " + title());
        System.out.println("Current URL: " + Selenide.webdriver().driver().url());

        // Ищем все input поля
        System.out.println("\n=== INPUT FIELDS ===");
        $$("input").forEach(input -> {
            String placeholder = input.getAttribute("placeholder");
            String type = input.getAttribute("type");
            String name = input.getAttribute("name");
            String id = input.getAttribute("id");
            System.out.println("Input - placeholder: '" + placeholder + "', type: " + type + ", name: " + name + ", id: " + id);
        });

        // Ищем все кнопки
        System.out.println("\n=== BUTTONS ===");
        $$("button").forEach(button -> {
            String text = button.getText();
            String type = button.getAttribute("type");
            if (!text.trim().isEmpty()) {
                System.out.println("Button - text: '" + text + "', type: " + type);
            }
        });

        // Делаем скриншот
        Selenide.screenshot("registration_page_exploration");
    }

    public static void exploreLoginPage() {
        System.out.println("=== EXPLORING LOGIN PAGE ===");
        System.out.println("Page title: " + title());
        System.out.println("Current URL: " + Selenide.webdriver().driver().url());

        // Ищем все input поля
        System.out.println("\n=== INPUT FIELDS ===");
        $$("input").forEach(input -> {
            String placeholder = input.getAttribute("placeholder");
            String type = input.getAttribute("type");
            String name = input.getAttribute("name");
            System.out.println("Input - placeholder: '" + placeholder + "', type: " + type + ", name: " + name);
        });

        // Делаем скриншот
        Selenide.screenshot("login_page_exploration");
    }
}