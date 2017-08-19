package com.eckerlin.todoq.models;

public class Task {
    private String text;
    public Task(String text) {
        this.setText(text);
    }

    private void setText(String text) {
        this.text = text;
    }

    public String toString() {
        return this.text;
    }
}
