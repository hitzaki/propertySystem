package com.chen.property.utils;

public class Result {
    private int state;
    private String msg;
    private Object data;

    public static Result ok(){
        return new Result(200);
    }

    public static Result fail(){
        return new Result(400);
    }

    public Result msg(String msg){
        this.msg = msg;
        return this;
    }

    public Result data(Object data){
        this.data = data;
        return this;
    }

    public Result(int state) {
        this.state = state;
    }

    public Result() {
    }

    public Result(int state, String msg, Object data) {
        this.state = state;
        this.msg = msg;
        this.data = data;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
