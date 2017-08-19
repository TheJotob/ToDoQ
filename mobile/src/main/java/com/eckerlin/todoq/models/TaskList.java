package com.eckerlin.todoq.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class TaskList {
    private Task currentTask;
    private BufferedReader in;
    private FileWriter out;

    public TaskList(File taskFile) {
        try {
            in = new BufferedReader(new FileReader(taskFile));
            out = new FileWriter(taskFile);
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
    }

    public boolean empty() throws IOException {
        try {
            boolean empty = (in.read() == -1);
            in.reset();
            return empty;
        } catch (IOException e) {
            return true;
        }
    }

    public Task peek() {
        return new Task("peek");
    }

    public Task pop() throws IOException {
        return getNextTask();
    }

    private Task getNextTask() throws IOException {
        String s = in.readLine();
        return new Task(s);
    }

    public void push(Task todo) throws IOException {
        out.write(todo.toString() + '\n');
    }
}
