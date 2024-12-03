package edu.westga.cs1302.project3.test.model;

import org.junit.jupiter.api.Test;
import edu.westga.cs1302.project3.model.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestTaskConstructor {
	
	@Test
	public void testValidTask() {
		String title = "Title";
		String description = "Description";
		Task task = new Task(title, description);
		assertEquals("Title", task.getTitle());
		assertEquals("Description", task.getDescription());
	}
	
	@Test
	public void testNullTitle() {
		String title = null;
		String description = "Description";
		assertThrows(IllegalArgumentException.class, () -> {
			new Task(title, description);
		});
	}
	
	@Test
	public void testNullDescription() {
		String title = "Title";
		String description = null;
		assertThrows(IllegalArgumentException.class, () -> {
			new Task(title, description);
		});
	}
	
	@Test
	public void testBothNull() {
		String title = null;
		String description = null;
		assertThrows(IllegalArgumentException.class, () -> {
			new Task(title, description);
		});
	}
	
	@Test
	public void testTitleEmpty() {
		String title = "";
		String description = "Description";
		assertThrows(IllegalArgumentException.class, () -> {
			new Task(title, description);
		});
	}
	
	@Test
	public void testDescriptionEmpty() {
		String title = "Title";
		String description = "";
		assertThrows(IllegalArgumentException.class, () -> {
			new Task(title, description);
		});
	}
	
	@Test
	public void testBothEmpty() {
		String title = "";
		String description = "";
		assertThrows(IllegalArgumentException.class, () -> {
			new Task(title, description);
		});
	}
}
