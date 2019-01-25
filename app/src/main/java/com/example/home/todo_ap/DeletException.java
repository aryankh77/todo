package com.example.home.todo_ap;

public class DeletException extends Exception {

    public DeletException() {
        System.out.println("Task Not Found");
    }
}
