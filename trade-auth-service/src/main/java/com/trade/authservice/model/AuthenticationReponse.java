package com.trade.authservice.model;

import lombok.Data;

@Data
public class AuthenticationReponse {

    private final String jwt;

    public AuthenticationReponse(String jwt) {
        this.jwt = jwt;
    }
}