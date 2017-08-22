package com.eckerlin.todoq.models;

public class Task {
    private String text;

    public Task(String text) {
        this.setText(text);
    }

    public String getText() {
        return this.text;
    }

    private void setText(String text) {
        this.text = text;
    }

    public String toString() {
        return this.text;
    }

    @Override
    public boolean equals(Object obj) {
        if (!obj.getClass().equals(this.getClass()))
            return false;

        Task t = (Task) obj;
        return text.equals(t.getText());
    }
}
