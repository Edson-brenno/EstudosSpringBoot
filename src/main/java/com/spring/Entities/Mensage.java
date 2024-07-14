package com.spring.Entities;

import java.io.Serializable;

public class Mensage<T> implements Serializable {

    private static final long serialVersionUID = 2L;
    private String message;
    private T data;

    public Mensage(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
