package com.eckerlin.todoq.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class TaskTest {
    @Test
    public void tasksAreEqualIfTheyHaveTheSameText() {
        Task t1 = new Task("Test");
        Task t2 = new Task("Test");
        assertEquals(true, t1.equals(t2));
    }
}
