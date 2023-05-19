package com.chen.property.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("t_repair")
public class Repair {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    private String account;
    private String phone;
    private String address;
    private String info;
    private String state;
    private String name;

    public Repair(int id, String account, String phone, String address, String info, String state, String name) {
        this.id = id;
        this.account = account;
        this.phone = phone;
        this.address = address;
        this.info = info;
        this.state = state;
        this.name = name;
    }

    public Repair() {}

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
