package com.chen.property.pojo;

public class LoginForm {
    private String account;
    private String password;
    private String verifyCode;
    private String type;

    public LoginForm(String account, String password, String verify, String type) {
        this.account = account;
        this.password = password;
        this.verifyCode = verify;
        this.type = type;
    }

    public LoginForm() {
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "LoginForm{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", verifyCode='" + verifyCode + '\'' +
                ", type=" + type +
                '}';
    }
}
