package com.chen.property.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("t_admin")
public class Admin {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    private String account;
    private String password;
    private String name;
    private String sex;
    private String phone;
    private String photo;

    public Admin(int id, String account, String password, String name, String sex, String phone, String photo) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.name = name;
        this.sex = sex;
        this.phone = phone;
        this.photo = photo;
    }
    public Admin() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
