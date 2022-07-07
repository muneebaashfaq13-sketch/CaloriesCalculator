package com.toptal.calories.models;

import java.sql.Timestamp;

public class AuthObject {

    private Long userId;
    private String token;
    private Timestamp expiry;

    public AuthObject(Long userId, String token, Timestamp expiry) {
        this.userId = userId;
        this.token = token;
        this.expiry = expiry;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getExpiry() {
        return expiry;
    }

    public void setExpiry(Timestamp expiry) {
        this.expiry = expiry;
    }
}
