package com.hart.Supermarket.employee.security.models;

import java.io.Serializable;

public class TwoFactorAuthenticationRequest implements Serializable {


    private String username;
    private String password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //need default constructor for JSON Parsing
    public TwoFactorAuthenticationRequest()
    {

    }

    public TwoFactorAuthenticationRequest(String username, String twoFactorAuthCode, String password) {
        this.setUsername(username);
        this.setTwoFactorAuthCode(twoFactorAuthCode);
        this.setPassword(password);
    }
}