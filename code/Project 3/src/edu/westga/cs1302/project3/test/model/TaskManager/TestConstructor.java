package edu.westga.cs1302.project3.test.model.TaskManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.TaskManager;

public class TestConstructor {
	
	@Test
	public void testValidConstructor() {
		TaskManager manageTask = new TaskManager();
		assertEquals(0, manageTask.getTasks().size(), "Ensures that the list is initalized empty");
		assertEquals(0, manageTask.getMap().size(), "Ensures that the map is initalzied empty");
	}
}
