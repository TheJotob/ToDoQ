package com.eckerlin.todoq.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

public class TaskList {
    private RandomAccessFile file;

    public TaskList(File taskFile) throws IOException {
        if (!taskFile.exists())
            taskFile.createNewFile();

        file = new RandomAccessFile(taskFile, "rw");
    }

    public boolean empty() throws IOException {
        file.seek(0);
        return (file.read() == -1);
    }

    public Task peek() throws IOException {
        return getNextTask();
    }

    public Task pop() throws IOException {
        Task t = getNextTask();
        return t;
    }

    private Task getNextTask() throws IOException, UnsupportedOperationException {
        file.seek(0);
        String s = file.readLine();
           if (s == null)
                throw new UnsupportedOperationException();

            return new Task(s);
    }

    private void deleteFirstLine() throws IOException {

    }

    public void push(Task todo) throws IOException {
        file.seek(file.length());
        file.writeChars(todo.toString() + '\n');
    }

    public void close() throws IOException {
        file.close();
    }
}
