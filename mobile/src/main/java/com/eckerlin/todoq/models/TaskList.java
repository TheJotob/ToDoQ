package com.eckerlin.todoq.models;

import android.util.Log;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Observable;

public class TaskList extends Observable {
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
        setChanged();
        notifyObservers();
        return t;
    }

    private Task getNextTask() throws IOException, UnsupportedOperationException {
        file.seek(0);
        String s = file.readLine();
        Log.d("ToDoQ", "" + file.length());
        /*for (byte b : s.getBytes())
            Log.d("ToDoQ", "" + b);*/

        if (s == null)
            throw new UnsupportedOperationException();

        return new Task(s.trim());
    }

    private void deleteFirstLine() throws IOException {
        file.seek(0);
        String st = file.readLine();
        String all = "";
        while(all.length() < file.length() - st.length())
            all += file.readLine() + '\n';

        file.close();
        taskFile.delete();
        taskFile.createNewFile();
        file = new RandomAccessFile(taskFile, "rw");

        Log.d("ToDoQ", "LengthSt" + st.length());
        Log.d("ToDoQ", "LengthAll" + all.length());

        all = all.trim();
        file.writeChars(all);
    }

    public void push(Task todo) throws IOException {
        file.seek(file.length());
        file.writeUTF(todo.toString() + '\n');
        setChanged();
        notifyObservers();
    }

    public long length() {
        try {
            return file.length();
        } catch (IOException e) {
            return 0;
        }
    }

    public void close() throws IOException {
        file.close();
    }
}
