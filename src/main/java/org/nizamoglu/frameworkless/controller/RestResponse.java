package org.nizamoglu.frameworkless.controller;

public class RestResponse {
    private String message;
    private Object data;

    public RestResponse(String message) {
        this.message = message;
    }

    public RestResponse(Object data, String message) {
        this.data = data;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
