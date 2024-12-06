package edu.westga.cs1302.project3.test.model.TaskStorageManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.model.TaskManager;
import edu.westga.cs1302.project3.model.TaskStorageManager;

public class TestLoadTasks {
	
	private static final String TEST_LOAD_TASKS = "testLoadTasks.txt";
	
	@BeforeEach
	public void clearFile() throws IOException{
		try(FileWriter writer = new FileWriter(TEST_LOAD_TASKS, false)){
			writer.write("");
		}
	}
	
	@Test
	public void testNonExistentFile() throws FileNotFoundException, IOException {
		new TaskStorageManager();
		
		assertThrows(FileNotFoundException.class, () -> {
			TaskStorageManager.loadTasks("noExistingFile.txt");
		});
	}
	
	@Test
	public void testLoadTasks() throws IOException{
		new TaskStorageManager();
		TaskManager manageTasks = new TaskManager();
		
		manageTasks.addTask(new Task("Code tests", "Now code the tests for the loadTask method"));
		TaskStorageManager.saveTasks(manageTasks, TEST_LOAD_TASKS);
		
		TaskManager loadedManager = TaskStorageManager.loadTasks(TEST_LOAD_TASKS);
		assertEquals("Code tests", loadedManager.getTasks().get(0).getTitle(), "Makes sure that the newly loaded list from file file has correct title field for Task object");
		assertEquals("Now code the tests for the loadTask method", loadedManager.getTasks().get(0).getDescription(), "Makes sure that the newly loaded list from the file has the correct description for the Task object");
		assertEquals(1, loadedManager.getTasks().size());
	}
	
	@Test
	public void testLoadingMultipleTasks() throws IOException{
		TaskManager manageTasks = new TaskManager();
		
		manageTasks.addTask(new Task("Code tests", "Now code the tests for the loadTask method"));
		manageTasks.addTask(new Task("Code everything", "Code every test at every point in time"));
		TaskStorageManager.saveTasks(manageTasks, TEST_LOAD_TASKS);
		
		TaskManager loadedManager = TaskStorageManager.loadTasks(TEST_LOAD_TASKS);
		assertEquals("Code tests", loadedManager.getTasks().get(0).getTitle(), "Makes sure that the newly loaded list from file file has correct title field for Task object");
		assertEquals("Now code the tests for the loadTask method", loadedManager.getTasks().get(0).getDescription(), "Makes sure that the newly loaded list from the file has the correct description for the Task object");
		assertEquals(2, loadedManager.getTasks().size());
		assertEquals("Code everything", loadedManager.getTasks().get(1).getTitle(), "Makes sure that the title matches the second object in lists title");
		assertEquals("Code every test at every point in time", loadedManager.getTasks().get(1).getDescription(), "Make sure the newly loaded list has the second object with the right description");
	}
	
	@Test
	public void testLoadEmptyList() throws IOException{
		
		assertThrows(IllegalArgumentException.class, () -> {
			TaskStorageManager.loadTasks(TEST_LOAD_TASKS);
		});
	}
	
	@Test
	public void testWithNullFilepath() throws IOException {
		TaskManager manageTasks = new TaskManager();
		
		manageTasks.addTask(new Task("Code tests", "Now code the tests for the loadTask method"));
		manageTasks.addTask(new Task("Code everything", "Code every test at every point in time"));
		TaskStorageManager.saveTasks(manageTasks, TEST_LOAD_TASKS);
		
		assertThrows(IllegalArgumentException.class, () -> {
			TaskStorageManager.loadTasks(null);
		});
	}
	
	@Test
	public void testWithInvalidFileType() throws IOException{
		
		assertThrows(FileNotFoundException.class, () -> {
			TaskStorageManager.loadTasks("nonExistentFileType.png");
		});
	}
	
	@Test
	public void testLoadInvalidDataFromTask() throws IOException{
		TaskManager manageTasks = new TaskManager();
		
		manageTasks.addTask(new Task("Code tests,", "Now code the tests for the loadTask method"));
		TaskStorageManager.saveTasks(manageTasks, TEST_LOAD_TASKS);
		
		assertThrows(IllegalArgumentException.class, () -> {
			TaskStorageManager.loadTasks(TEST_LOAD_TASKS);
		});
	}
}
