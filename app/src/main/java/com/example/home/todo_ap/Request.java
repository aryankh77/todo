package com.example.home.todo_ap;

import java.io.Serializable;

public class Request implements Serializable {
    private static final long serialVersionUID=1234;
    private String message;
    private String type;
    private Serializable serializable;

    public Request(String message, String type, Serializable serializable) {
        this.message = message;
        this.type = type;
        this.serializable=serializable;
    }

    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }

    public Serializable getSerializable() {
        return serializable;
    }
}
