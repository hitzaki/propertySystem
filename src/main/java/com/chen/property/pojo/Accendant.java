package com.chen.property.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("t_accendant")
public class Accendant {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    private String name;
    private String phone;
    private String type;

    public Accendant(int id, String name, String phone, String type) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.type = type;
    }

    public Accendant() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
