package edu.westga.cs1302.project3.test.model.TaskStorageManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.model.TaskManager;
import edu.westga.cs1302.project3.model.TaskStorageManager;

public class TestSaveTasks {
	private static final String SAVE_TASK_TEST = "testSaveTask.txt";
	
	@Test
	public void testSavingOneTask() throws IOException {
		TaskManager manageTasks = new TaskManager();
		manageTasks.addTask(new Task("Complete Project 3 Part 1", "Code the tests for the save tasks method"));
		
		TaskStorageManager.saveTasks(manageTasks, SAVE_TASK_TEST);
		
		File inputFile = new File(SAVE_TASK_TEST);
		try(Scanner reader = new Scanner(inputFile)){
			assertEquals("Complete Project 3 Part 1,Code the tests for the save tasks method", reader.nextLine(), "Checks to make sure that the line was correctly saved exactly as is in file");
		}
		
	}
	
	@Test
	public void testSaveMultipleTasks() throws IOException{
		TaskManager manageTasks = new TaskManager();
		manageTasks.addTask(new Task("Complete Project 3 Part 1", "Code the tests for the save tasks method"));
		manageTasks.addTask(new Task("Eat Food", "Dont forget to eat food at some point sometime"));
		
		TaskStorageManager.saveTasks(manageTasks, SAVE_TASK_TEST);
		
		File inputFile = new File(SAVE_TASK_TEST);
		try(Scanner reader = new Scanner(inputFile)){
			assertEquals("Complete Project 3 Part 1,Code the tests for the save tasks method", reader.nextLine(), "Checks to make sure that the line was correctly saved exactly as is in file");
			assertEquals("Eat Food,Dont forget to eat food at some point sometime", reader.nextLine(), "Checks to make sure that the correct title and description were printed on the next line");
		}
	}
	
	@Test
	public void testSavingWithNullTasks() throws IOException{
		assertThrows(IllegalArgumentException.class, () -> {
			TaskStorageManager.saveTasks(null, SAVE_TASK_TEST);
		});
	}
	
	@Test
	public void testSavingWithNullFilePath() throws IOException{
		TaskManager manageTasks = new TaskManager();
		manageTasks.addTask(new Task("Complete Project 3 Part 1", "Code the tests for the save tasks method"));
		
		assertThrows(IllegalArgumentException.class, () -> {
			TaskStorageManager.saveTasks(manageTasks, null);
		});
	}
	
	@Test
	public void testEmptyTaskManager() throws IOException {
		TaskManager manageTasks = new TaskManager();
		
		assertThrows(IllegalArgumentException.class, () -> {
			TaskStorageManager.saveTasks(manageTasks, SAVE_TASK_TEST);
		});
	}
	
	@Test
	public void testInvalidFilepath() throws IOException{
		TaskManager manageTasks = new TaskManager();
		manageTasks.addTask(new Task("Complete Project 3 Part 1", "Code the tests for the save tasks method"));
		
		assertThrows(IllegalArgumentException.class, () -> {
			TaskStorageManager.saveTasks(manageTasks, "testInvalidFile.img");
		});
	}
}
