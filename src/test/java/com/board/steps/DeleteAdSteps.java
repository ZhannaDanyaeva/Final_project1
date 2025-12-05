package com.board.steps;

import com.board.utils.ApiClient;
import com.board.utils.DataGenerator;
import com.board.utils.models.AuthResponse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeleteAdSteps {

    @Test
    public void deleteAdTest() {
        String email = DataGenerator.generateRandomEmail();
        String password = "123456";

        AuthResponse auth = ApiClient.register(
                email,
                password,
                password
        );

        String token = auth.accessToken.accessToken;

        String adId = ApiClient.createAd(
                DataGenerator.generateRandomName(),
                DataGenerator.generateRandomDescription(),
                DataGenerator.generateRandomPrice(),
                "Книги",
                token
        );

        int deleteStatus = ApiClient.deleteAd(adId, token);
        assertEquals(204, deleteStatus, "Ad was not deleted");

        int getStatus = ApiClient.getAdStatus(adId);
        assertEquals(404, getStatus, "Ad still exists after deletion");
    }
}

