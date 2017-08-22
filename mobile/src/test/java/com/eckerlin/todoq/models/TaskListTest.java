package com.eckerlin.todoq.models;

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
        File taskFile = new File(".", "task.list");
/*        if (taskFile.exists()) {
            taskFile.delete();
        }*/
        list = new TaskList(taskFile);
    }

    @After
    public void tearDown() throws IOException {
        list.close();
        File taskFile = new File(".", "task.list");
        if (taskFile.exists())
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
        assertEquals(list.peek(), list.peek());
    }

    @Test
    public void peekAndPopShouldReturnTheSameTask() throws IOException {
        Task t1 = new Task("Test1");
        Task t2 = new Task("Test2");

        list.push(t1);
        list.push(t2);
        assertEquals(list.peek(), list.pop());
    }

    @Test
    public void popShouldDeleteTask() throws IOException {
        Task t1 = new Task("Test1");
        Task t2 = new Task("Test2");

        list.push(t1);
        list.push(t2);
        assertEquals(t1, list.pop());
        assertEquals(t2, list.pop());
    }

    @Test
    public void peekShouldLeaveTaskInQueue() throws IOException {
        Task t1 = new Task("Test1");
        Task t2 = new Task("Test2");

        list.push(t1);
        list.push(t2);
        assertEquals(t1, list.peek());
        assertEquals(t1, list.peek());
    }

    @Test
    public void gettingTaskFromEmptyListShouldThrowException() throws IOException {
        try {
            System.out.println(list.pop());
            list.pop();
            fail();
        } catch (UnsupportedOperationException e) {
        }
    }

}