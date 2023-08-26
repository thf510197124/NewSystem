package com.taiquan.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class NoAhuthrityException extends Exception {
    private String userName;
    private String companyName;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public NoAhuthrityException(String msg) {
        super(msg);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public NoAhuthrityException(String msg, String userName,String companyName) {
        super(msg);
        this.userName=userName;
        this.companyName = companyName;
    }
}
