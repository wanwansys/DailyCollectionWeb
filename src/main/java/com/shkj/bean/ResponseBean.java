package com.shkj.bean;

public class ResponseBean {
    private String retCode;
    private String message;
    private String result;

    public ResponseBean(String retCode, String message, String result) {
        this.retCode = retCode;
        this.message = message;
        this.result = result;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
