package com.chen.property.pojo;

public class Info {
    private String account;
    private String type;
    private String photo;
    private String name;
    private String sex;
    private String phone;

    public Info(String account, String type, String photo, String name, String sex, String phone) {
        this.account = account;
        this.type = type;
        this.photo = photo;
        this.name = name;
        this.sex = sex;
        this.phone = phone;
    }

    public Info(String account, String type, String photo, String name) {
        this.account = account;
        this.type = type;
        this.photo = photo;
        this.name = name;
    }

    public Info() {
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
