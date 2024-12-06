package edu.westga.cs1302.project3.test.viewmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.viewmodel.ViewModel;

public class TestAddTask {
	
	@Test
	public void testValidTask() {
		ViewModel vm = new ViewModel();
		vm.getTitle().setValue("Task 1");
		vm.getDescription().setValue("This is task 1");
		vm.addTask();
		
		assertEquals("Task 1", vm.getTasks().get(2).getTitle(), "Makes sure that newly added task has the correct Title");
		assertEquals("This is task 1", vm.getTasks().get(2).getDescription(),"Makes sure that the newly added task has the correct description");
		assertEquals(3, vm.getTasks().getSize(),"Makes sure that the list contains the two default tasks, and added task");
	}
	
	@Test
	public void testMultipleValidTasks() {
		ViewModel vm = new ViewModel();
		vm.getTitle().setValue("Task 1");
		vm.getDescription().setValue("This is task 1");
		vm.addTask();
		
		vm.getTitle().setValue("Task 2");
		vm.getDescription().setValue("This is task 2");
		vm.addTask();
		
		assertEquals("Task 1", vm.getTasks().get(2).getTitle(), "Makes sure that newly added task has the correct Title");
		assertEquals("This is task 1", vm.getTasks().get(2).getDescription(),"Makes sure that the newly added task has the correct description");
		assertEquals("Task 2", vm.getTasks().get(3).getTitle(), "Makes sure that newly second added task has the correct Title");
		assertEquals("This is task 2", vm.getTasks().get(3).getDescription(),"Makes sure that the second newly added task has the correct description");
		assertEquals(4, vm.getTasks().getSize(),"Makes sure that the list contains the two default tasks, and added task");
	}
	
	@Test
	public void testWhenTitleNull() {
		ViewModel vm = new ViewModel();
		vm.getTitle().setValue(null);
		vm.getDescription().setValue("This is task 1");
		assertThrows(IllegalArgumentException.class, () -> {
			vm.addTask();
		});
	}
	
	@Test
	public void testWhenTitleEmpty() {
		ViewModel vm = new ViewModel();
		vm.getTitle().setValue("");
		vm.getDescription().setValue("This is task 1");
		assertThrows(IllegalArgumentException.class, () -> {
			vm.addTask();
		});
	}
	
	@Test
	public void testWhenDecriptionNull() {
		ViewModel vm = new ViewModel();
		vm.getTitle().setValue("Task 1");
		vm.getDescription().setValue(null);
		assertThrows(IllegalArgumentException.class, () -> {
			vm.addTask();
		});
	}
	
	@Test
	public void testWhenDescriptionEmpty() {
		ViewModel vm = new ViewModel();
		vm.getTitle().setValue("Task 1");
		vm.getDescription().setValue("");
		assertThrows(IllegalArgumentException.class, () -> {
			vm.addTask();
		});
	}
}
