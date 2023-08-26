package com.taiquan.exception;

public class NoSuchObjectException extends Exception {
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public NoSuchObjectException(String msg){
        super(msg);
    }
}
