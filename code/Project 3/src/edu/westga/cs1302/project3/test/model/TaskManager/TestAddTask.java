package edu.westga.cs1302.project3.test.model.TaskManager;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.model.TaskManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TestAddTask {
	
	@Test
	public void addSingleTask() {
		Task task = new Task("Title", "Description");
		TaskManager manageTasks = new TaskManager();
		manageTasks.addTask(task);
		assertEquals(task, manageTasks.getTasks().get(0),"Makes sure that it is the same object");
		assertEquals(1, manageTasks.getTasks().size(), "Ensures that the object is added to the list");
		assertEquals("Title", manageTasks.getTasks().get(0).getTitle(),"Ensures that the objects title field did not change");
		assertEquals("Description", manageTasks.getTasks().get(0).getDescription(), "Ensures that the description field did not change");
	}
	
	@Test
	public void addMultipleTasks() {
		Task task1= new Task("Title", "Description");
		Task task2 = new Task("Title2", "Description2");
		TaskManager manageTasks = new TaskManager();
		manageTasks.addTask(task1);
		manageTasks.addTask(task2);
		assertEquals(task1, manageTasks.getTasks().get(0)," Ensures that the object is equal to the first added object");
		assertEquals(task2, manageTasks.getTasks().get(1), "Makes sure that the object is equal to the second added object");
		assertEquals(2, manageTasks.getTasks().size(), "Makes sure that the list contains two objects");
		assertEquals("Title", manageTasks.getTasks().get(0).getTitle(), "Ensuring that the first items title field was not changed");
		assertEquals("Description", manageTasks.getTasks().get(0).getDescription(), "Ensuring that the first items description field was not changed");
		assertEquals("Title2", manageTasks.getTasks().get(1).getTitle(), "Ensuring that the second items title field were not changed");
		assertEquals("Description2", manageTasks.getTasks().get(1).getDescription(), "Makes sure that the seconds items description field was not changed");
	}
	
	@Test
	public void testNullTask() {
		TaskManager manageTasks = new TaskManager();
		assertThrows(IllegalArgumentException.class, () -> {
			manageTasks.addTask(null);
		});
	}
	
	@Test
	public void testAddingSameTaskTwice() {
		Task task1= new Task("Title", "Description");
		TaskManager manageTasks = new TaskManager();
		manageTasks.addTask(task1);
		manageTasks.addTask(task1);
		assertEquals(task1, manageTasks.getTasks().get(0)," Ensures that the object is equal to the first added object");
		assertEquals(task1, manageTasks.getTasks().get(1), "Makes sure that the object is equal to the second added object");
		assertEquals(2, manageTasks.getTasks().size(), "Makes sure that the list contains two objects");
		assertEquals("Title", manageTasks.getTasks().get(0).getTitle(), "Ensuring that the first items title field was not changed");
		assertEquals("Description", manageTasks.getTasks().get(0).getDescription(), "Ensuring that the first items description field was not changed");
		assertEquals("Title", manageTasks.getTasks().get(1).getTitle(), "Ensuring that the second items title field were not changed");
		assertEquals("Description", manageTasks.getTasks().get(1).getDescription(), "Makes sure that the seconds items description field was not changed");
	}
}
