package com.hart.Supermarket.employee.security.models;

import java.io.Serializable;

public class TwoFactorAuthenticationRequest implements Serializable {


    private String username;
    private String twoFactorAuthCode;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTwoFactorAuthCode() {
        return twoFactorAuthCode;
    }

    public void setTwoFactorAuthCode(String twoFactorAuthCode) {
        this.twoFactorAuthCode = twoFactorAuthCode;
    }

    //need default constructor for JSON Parsing
    public TwoFactorAuthenticationRequest()
    {

    }

    public TwoFactorAuthenticationRequest(String username, String twoFactorAuthCode) {
        this.setUsername(username);
        this.setTwoFactorAuthCode(twoFactorAuthCode);
    }
}