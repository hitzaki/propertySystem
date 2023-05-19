package com.chen.property.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

public class JsonUtil {
    private final static ObjectMapper mapper = new ObjectMapper();

    public static String asString(Object o){
        try {
            return mapper.writeValueAsString(o);
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("sdddd");
        list.add("s234d");
        list.add("sd324");
        System.out.println(asString(list));
    }

}
