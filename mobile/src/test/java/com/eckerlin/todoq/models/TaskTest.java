package com.eckerlin.todoq.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class TaskTest {
    @Test
    public void tasksAreEqualIfTheyHaveTheSameText() {
        Task t1 = new Task("Test");
        Task t2 = new Task("Test");
        assertEquals(t1, t2);
    }

    @Test
    public void tasksAreNotEqualIfTheyDontHaveTheSameText() {
        Task t1 = new Task("Test1");
        Task t2 = new Task("Test2");
        assertNotEquals(t1, t2);
    }

    @Test
    public void toStringReturnsText() {
        Task t = new Task("Test");
        assertEquals("Test", t.toString());
    }
}
