package com.chen.property.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("t_estate")
public class Estate {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    private String account;
    private String name;
    private String address;
    private String purpose;

    public Estate() {
    }

    public Estate(int id, String account, String name, String address, String purpose) {
        this.id = id;
        this.account = account;
        this.name = name;
        this.address = address;
        this.purpose = purpose;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
}
