package edu.westga.cs1302.project3.test.model.TaskManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.model.TaskManager;

public class TestRemoveTask {
	
	@Test
	public void removeValidTask() {
		Task task1= new Task("Title", "Description");
		TaskManager manageTasks = new TaskManager();
		manageTasks.addTask(task1);
		manageTasks.removeTask(task1);
		assertTrue(manageTasks.getTasks().isEmpty(),"Ensures that the list now contains no objects");
	}
	
	@Test
	public void removeNonExistingTaskInList() {
		Task task1= new Task("Title", "Description");
		Task task2 = new Task("Title2", "Descrition2");
		TaskManager manageTasks = new TaskManager();
		manageTasks.addTask(task2);
		assertThrows(IllegalArgumentException.class, () -> {
			manageTasks.removeTask(task1);
		});
	}
	
	@Test
	public void removeNullTask() {
		TaskManager manageTasks = new TaskManager();
		assertThrows(IllegalArgumentException.class, () -> {
			manageTasks.removeTask(null);
		});
	}
	
	@Test
	public void testAddingThenRemoving() {
		Task task1= new Task("Title", "Description");
		TaskManager manageTasks = new TaskManager();
		manageTasks.addTask(task1);
		assertEquals(1, manageTasks.getTasks().size(),"Ensures that the task in fact did exist inside of the list before removal");
		manageTasks.removeTask(task1);
		assertTrue(manageTasks.getTasks().isEmpty(), "Ensures that the list contains no objects once task is removed");
	}
	
	@Test
	public void removeAlltasks() {
		Task task1= new Task("Title", "Description");
		Task task2 = new Task("Title2", "Description2");
		TaskManager manageTasks = new TaskManager();
		manageTasks.addTask(task1);
		manageTasks.addTask(task2);
		assertEquals(2, manageTasks.getTasks().size(),"Ensures that the task in fact did exist inside of the list before removal");
		manageTasks.removeTask(task1);
		manageTasks.removeTask(task2);
		assertEquals(0, manageTasks.getTasks().size(), "Ensures that the list is now completely empty");
	}
	
	@Test
	public void removingOneOfMany() {
		Task task1= new Task("Title", "Description");
		Task task2 = new Task("Title2", "Description2");
		TaskManager manageTasks = new TaskManager();
		manageTasks.addTask(task1);
		manageTasks.addTask(task2);
		assertEquals(2, manageTasks.getTasks().size(),"Ensures that the task in fact did exist inside of the list before removal");
		manageTasks.removeTask(task1);
		assertEquals(task2, manageTasks.getTasks().get(0), "Ensures that the list contains no objects once task is removed");
		assertEquals(1, manageTasks.getTasks().size(), "Ensures that the list still contains non-removed object");
	}
	
	@Test
	public void removeFromEmptyList() {
		Task task1 = new Task("Title", "Description");
		TaskManager manageTasks = new TaskManager();
		assertThrows(IllegalArgumentException.class, () -> {
			manageTasks.removeTask(task1);
		});
	}
}
