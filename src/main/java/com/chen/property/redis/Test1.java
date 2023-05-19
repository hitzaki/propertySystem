package com.chen.property.redis;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

public class Test1 {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.224.131", 6379);
        String value = jedis.ping();
        System.out.println(value);
    }

    @Test
    public void test1(){
        int[] arr = new int[500];
        long time = System.currentTimeMillis();
        for(int i = 0; i<arr.length; i++){
            int a = 32;
            a++;
            System.out.println(a);
        }
        time = System.currentTimeMillis() - time;


        long time2 = System.currentTimeMillis();
        int len = arr.length;
        for(int i = 0; i<len; i++){
            int a = 32;
            a++;
            System.out.println(a);
        }
        time2 = System.currentTimeMillis() - time2;


        System.out.println(time);
        System.out.println(time2);
    }

    @Test
    public void tet4(){
        int $中文 = 322;
        System.out.println($中文);
    }
}
