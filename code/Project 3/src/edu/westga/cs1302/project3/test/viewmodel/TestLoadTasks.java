package edu.westga.cs1302.project3.test.viewmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.viewmodel.ViewModel;

public class TestLoadTasks {
	private static final String TEST_LOAD_TASKS = "testLoadTasks.txt";
	
	@BeforeEach
	public void clearFile() throws IOException{
		try(FileWriter writer = new FileWriter(TEST_LOAD_TASKS, false)){
			writer.write("");
		}
	}
	
	@Test
	public void testLoadTasks() throws IOException {
		ViewModel vm = new ViewModel();
		FileWriter writer = new FileWriter(TEST_LOAD_TASKS);
		writer.write("Test valid data,Valid data");
		writer.close();
		
		vm.loadTasks(TEST_LOAD_TASKS);
		
		assertEquals("Test valid data", vm.getTasks().get(0).getTitle());
		assertEquals("Valid data", vm.getTasks().get(0).getDescription());
		assertEquals(1, vm.getTasks().size());
	}
	
	@Test
	public void testInvalidFile() throws IOException{
		ViewModel vm = new ViewModel();
		
		assertThrows(FileNotFoundException.class, () -> {
			vm.loadTasks("images.png");
		});
	}
	
	@Test
	public void testInvalidData() throws IOException{
		ViewModel vm = new ViewModel();
		
		FileWriter writer = new FileWriter(TEST_LOAD_TASKS);
		writer.write("Test invalid data");
		writer.close();
		assertThrows(IllegalArgumentException.class, () -> {
			vm.loadTasks(TEST_LOAD_TASKS);
		});
		
		
	}
	
	@Test
	public void testNullFilepath() throws IOException{
		ViewModel vm = new ViewModel();
		
		assertThrows(IllegalArgumentException.class, () -> {
			vm.loadTasks(null);
		});
	}
	
	@Test
	public void testEmptyFile() throws IOException {
		ViewModel vm = new ViewModel();
		assertThrows(IllegalArgumentException.class, () -> {
			vm.loadTasks(TEST_LOAD_TASKS);
		});
	}
	
	@Test
	public void testMultipleTasks() throws IOException{
		ViewModel vm = new ViewModel();
		FileWriter writer = new FileWriter(TEST_LOAD_TASKS);
		writer.write("Test valid data,Valid data\n");
		writer.write("Another valid task,I hate testing code");
		writer.close();
		
		vm.loadTasks(TEST_LOAD_TASKS);
		
		assertEquals("Test valid data", vm.getTasks().get(0).getTitle(), "Makes sure that the first task loaded contains correct title");
		assertEquals("Valid data", vm.getTasks().get(0).getDescription(),"Makes sure that the first task loaded contains the correct description");
		assertEquals("Another valid task", vm.getTasks().get(1).getTitle(),"Makes sure that the second task loaded contains the correct title");
		assertEquals("I hate testing code", vm.getTasks().get(1).getDescription(),"Makes sure that the second task loaded contains the correct description");
		assertEquals(2, vm.getTasks().size(),"Makes sure that there are only the two tasks loaded from the file in the list");
	}
}
