package com.chen.property.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("t_pay")
public class Pay {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    private String account;
    private String name;
    private float price;
    private String state;

    public Pay(int id, String account, String name, float price, String state) {
        this.id = id;
        this.account = account;
        this.name = name;
        this.price = price;
        this.state = state;
    }

    public Pay() {}

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
