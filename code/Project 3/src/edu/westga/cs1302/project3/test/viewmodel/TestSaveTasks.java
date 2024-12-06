package edu.westga.cs1302.project3.test.viewmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.viewmodel.ViewModel;

public class TestSaveTasks {
	private static final String TEST_SAVE_TASKS = "testSaveTasks.txt";
	private static final String TEST_LOAD_TASKS = "testLoadTasks.txt";
	private static final String LOCKED_FILE = "LockedFile.txt";
	
	@Test
	public void testSavedDefault() throws FileNotFoundException, IOException {
		ViewModel vm = new ViewModel();
		
		vm.saveTasks(vm.getTaskManager(), TEST_SAVE_TASKS);
		
		File inputFile = new File(TEST_SAVE_TASKS);
		
		try(Scanner reader = new Scanner(inputFile)) {
			assertEquals("Default Task 1", vm.getTasks().get(0).getTitle(), "Checks to make sure that the first task is the default and has correct title");
			assertEquals("This is the first default task", vm.getTasks().get(0).getDescription(), "Checks to make sure that the first default task was saved with correct description");
			assertEquals("Default Task 2", vm.getTasks().get(1).getTitle(), "Checks to make sure that the second task is the default and has correct title");
			assertEquals("This is the second default task", vm.getTasks().get(1).getDescription(), "Checks to make sure that the second default task was saved with correct description");
		}
	}
	
	@Test
	public void testNullFilePath() throws IOException {
		ViewModel vm = new ViewModel();
		
		assertThrows(IllegalArgumentException.class, () -> {
			vm.saveTasks(vm.getTaskManager(), null);
		});
	}
	
	@Test
	public void testNullTaskManager() throws IOException{
		ViewModel vm = new ViewModel();
		
		assertThrows(IllegalArgumentException.class, () -> {
			vm.saveTasks(null, TEST_SAVE_TASKS);
		});
	}
	
	@Test
	public void testEmptyFilePath() throws IOException{
		ViewModel vm = new ViewModel();
		
		assertThrows(IllegalArgumentException.class, () -> {
			vm.saveTasks(vm.getTaskManager(), "");
		});
	}
	
	@Test
	public void testSavingLoadedFileIntoEmptyFile() throws IOException{
		ViewModel vm = new ViewModel();
		vm.loadTasks(TEST_LOAD_TASKS);
		
		assertEquals(1, vm.getTasks().getSize());
		
		vm.saveTasks(vm.getTaskManager(), TEST_SAVE_TASKS);
		File inputFile = new File(TEST_SAVE_TASKS);
		try(Scanner reader = new Scanner(inputFile)) {
			assertEquals("Test valid data", vm.getTasks().get(0).getTitle(), "Checks to make sure that the first task is the loaded task and has correct title");
			assertEquals("Valid data", vm.getTasks().get(0).getDescription(), "Checks to make sure that the first loaded task was saved with correct description");
		}
	}
}
