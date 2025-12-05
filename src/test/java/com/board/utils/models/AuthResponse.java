package com.board.utils.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthResponse {
    public User user;
    @JsonAlias({"token", "access_token"})
    public AccessToken accessToken;
    public AuthResponse(){

    }

    public int statusCode() {
        return 0;
    }
}

