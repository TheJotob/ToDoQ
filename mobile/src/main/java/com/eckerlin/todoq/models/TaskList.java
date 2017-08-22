package com.eckerlin.todoq.models;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

public class TaskList {
    private RandomAccessFile file;
    private File taskFile;

    public TaskList(File taskFile) throws IOException {
        this.taskFile = taskFile;
        if (!this.taskFile.exists())
            this.taskFile.createNewFile();

        file = new RandomAccessFile(taskFile, "rw");
    }

    public boolean empty() throws IOException {
        file.seek(0);
        return file.read() == -1;
    }

    public Task peek() throws IOException {
        return getNextTask();
    }

    public Task pop() throws IOException {
        Task t = getNextTask();
        deleteFirstLine();
        return t;
    }

    private Task getNextTask() throws IOException, UnsupportedOperationException {
        file.seek(0);
        String s = file.readLine();
        if (s == null)
            throw new UnsupportedOperationException();

        return new Task(s.trim());
    }

    private void deleteFirstLine() throws IOException {
        file.seek(0);
        file.readLine();
        try {
            String cache = file.readUTF();
            file.close();
            if (taskFile.delete() && taskFile.createNewFile())
                file = new RandomAccessFile(taskFile, "rw");
            file.writeUTF(cache);
        } catch (EOFException e) {}
    }

    public void push(Task todo) throws IOException {
        file.seek(file.length());
        file.writeUTF(todo.toString() + '\n');
    }

    public void close() throws IOException {
        file.close();
    }
}
