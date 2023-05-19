package com.chen.property.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("t_standard")
public class Standard {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    private String name;
    private String unit;
    private float univalence;

    public Standard(int id, String name, String unit, float univalence) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.univalence = univalence;
    }

    public Standard() {
    }

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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public float getUnivalence() {
        return univalence;
    }

    public void setUnivalence(float univalence) {
        this.univalence = univalence;
    }
}
