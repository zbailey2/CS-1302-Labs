package edu.westga.cs1302.project3.test.viewmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.viewmodel.ViewModel;

public class testRemoveTask {
	
	@Test
	public void removeOneTask() {
		ViewModel vm = new ViewModel();
		Task firstTask = vm.getTasks().get(0);
		vm.getSelectedTask().set(firstTask);
		
		vm.removeTask();
		assertFalse(vm.getTasks().contains(firstTask));
		assertEquals(1, vm.getTasks().size());
	}
	
	@Test
	public void removeMultipleTasks() {
		ViewModel vm = new ViewModel();
		Task firstTask = vm.getTasks().get(0);
		vm.getSelectedTask().set(firstTask);
		vm.removeTask();
		
		Task secondTask = vm.getTasks().get(0);
		vm.getSelectedTask().set(secondTask);
		vm.removeTask();
		
		assertFalse(vm.getTasks().contains(secondTask));
		assertEquals(0, vm.getTasks().size());
	}
	
	@Test
	public void removeNullTask() {
		ViewModel vm = new ViewModel();
		Task firstTask = null;
		vm.getSelectedTask().set(firstTask);
		
		assertThrows(IllegalArgumentException.class, () -> {
			vm.removeTask();
		});
	}
}
