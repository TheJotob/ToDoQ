package com.eckerlin.todoq.models;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class TaskListTest {
    private TaskList list;

    @Before
    public void setUp() throws IOException {
        Context appContext = InstrumentationRegistry.getTargetContext();
        File taskFile = new File(appContext.getFilesDir().getPath(), "task.list");

        list = new TaskList(taskFile);
    }

    @After
    public void tearDown() throws IOException {
        Context appContext = InstrumentationRegistry.getTargetContext();
        File taskFile = new File(appContext.getFilesDir().getPath(), "task.list");

        taskFile.delete();
    }

    @Test
    public void emptyShouldReturnTrueOnEmptyList() throws IOException {
        assertEquals(true, list.empty());
    }

    @Test
    public void emptyShouldReturnFalseOnNotEmptyList() throws IOException {
        Task t = new Task("Test");
        list.push(t);
        assertEquals(false, list.empty());
    }

    @Test
    public void peekShouldReturnTheSameResultUntilItIsDeleted() throws IOException {
        Task t1 = new Task("Test1");
        Task t2 = new Task("Test2");

        list.push(t1);
        list.push(t2);
        assertEquals(t1, list.peek());
    }

    @Test
    public void gettingTaskFromEmptyListShouldThrowException() {
        try {
            System.out.println(list.pop());
            list.pop();
            fail();
        } catch (UnsupportedOperationException e) {
        }
    }

}