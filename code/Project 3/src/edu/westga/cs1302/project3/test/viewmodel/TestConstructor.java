package edu.westga.cs1302.project3.test.viewmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.viewmodel.ViewModel;

public class TestConstructor {
	
	@Test
	public void testDefautViewModel() {
		ViewModel vm = new ViewModel();
		assertEquals(2, vm.getTasks().size(), "Makes sure that the default viewmodel comes with the two default tasks");
		assertEquals("Default Task 1", vm.getTasks().get(0).getTitle(), "Makes sure that the default viewmodel's first default task is initialized with right title");
		assertEquals("Default Task 2", vm.getTasks().get(1).getTitle(), "Makes sure that the default viewmodel's second default task is initialized with right title");
		assertEquals("This is the first default task", vm.getTasks().get(0).getDescription(), "Makes sure that the default viewmodel's first default task is initialized with right description");
		assertEquals("This is the second default task", vm.getTasks().get(1).getDescription(),  "Makes sure that the default viewmodel's first default task is initialized with right description");
		assertEquals("", vm.getTitle().get());
		assertEquals("", vm.getDescription().get());
		assertEquals(null,vm.getSelectedTask().get(),"Ensures that when the viewmodel is constructed that the selectedTask does not contain a value yet");
	}
}
