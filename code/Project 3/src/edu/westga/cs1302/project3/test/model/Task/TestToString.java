package edu.westga.cs1302.project3.test.model.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;

public class TestToString {
	
	@Test
	public void testToString() {
		Task task = new Task("Task 1", "This is the first task");
		assertEquals("Task 1", task.toString());
	}
}
