package com.board.steps;

import com.board.utils.ApiClient;
import com.board.utils.models.AuthResponse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeleteAdTest {

    @Test
    public void deleteAdTest() {
        // 1. Регистрация пользователя
        AuthResponse auth = ApiClient.register(
                "Test User",
                "test" + System.currentTimeMillis() + "@mail.com",
                "Password123"
        );

        String token = auth.accessToken;

        // 2. Создание объявления
        String adId = ApiClient.createAd(
                "Тестовое объявление",
                "Описание тут",
                token
        );

        // 3. Удаление объявления
        int deleteStatus = ApiClient.deleteAd(adId, token);
        assertEquals(204, deleteStatus, "Ad was not deleted");

        // 4. Проверка — объявление должно быть удалено
        int getStatus = ApiClient.getAdStatus(adId);
        assertEquals(404, getStatus, "Ad still exists after deletion");
    }
}

