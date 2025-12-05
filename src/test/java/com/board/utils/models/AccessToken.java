package com.board.utils.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccessToken {
    @JsonProperty("access_token")
    public String accessToken;
    public AccessToken(){

    }

}
