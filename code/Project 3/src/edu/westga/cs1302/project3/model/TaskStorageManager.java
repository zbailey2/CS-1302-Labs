package edu.westga.cs1302.project3.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

/**
 * Manage methods for specific tasks
 * 
 * @author Zachary Bailey
 * @version Fall 2024
 */
public class TaskStorageManager {

	/**
	 * Saves the tasks provided from a TaskManager to a specified file
	 * 
	 * @param tasks    the TaskManager containing tasks to save
	 * @param filePath the file path for the file to be downloaded too
	 * @throws IOException
	 */
	public static void saveTasks(TaskManager tasks, String filePath) throws IOException {
		if (tasks == null || tasks.getTasks().isEmpty()) {
			throw new IllegalArgumentException("Tasks can not be empty");
		}
		// Saves too selected file
		if (filePath == null || filePath.isEmpty()) {
			throw new IllegalArgumentException("Must include a file path");
		} else if (!filePath.endsWith(".txt")) {
			throw new IllegalArgumentException("Can not write to file, try selecting a .txt file");
		}
		File file = new File(filePath);
		if (!file.canWrite()) {
			throw new IllegalArgumentException("Can not write to file");
		}
		// Saves utilizing the Comma Separated Values(CSV) form we used in earlier labs
		try (FileWriter writer = new FileWriter(filePath)) {
			for (Task task : tasks.getTasks()) {
				writer.write(task.getTitle() + "," + task.getDescription() + System.lineSeparator());
			}
		}
	}

	/**
	 * loads the tasks that are saved in the file path
	 * 
	 * @param filePath the file path provided to load from
	 * @return TaskManager the TaskManager object that returns a populated ArrayList
	 *         with all loaded values
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static TaskManager loadTasks(String filePath) throws FileNotFoundException, IOException {
		if (filePath == null || filePath.isEmpty()) {
			throw new IllegalArgumentException("Must be a valid text document file");
		}
		TaskManager manageTasks = new TaskManager();
		Map<String, Task> uniqueTasks = manageTasks.getMap();
		ArrayList<Task> tasks = manageTasks.getTasks();
		File inputFile = new File(filePath);
		try (Scanner reader = new Scanner(inputFile)) {
			for (int lineNumber = 1; reader.hasNextLine(); lineNumber++) {
				String baseLine = reader.nextLine();
				String strippedLine = baseLine.strip();
				String[] parts = strippedLine.split(",");
				if (parts.length != 2) {
					throw new IllegalArgumentException("Unable to create list of tasks, bad title/description on line "
							+ lineNumber + " : " + strippedLine);
				}
				String title = parts[0];
				String description = parts[1];
				Task currentTask = new Task(title, description);
				tasks.add(currentTask);
				uniqueTasks.put(currentTask.getTitle(), currentTask);
			}
		}
		if (manageTasks.getTasks().isEmpty()) {
			throw new IllegalArgumentException("File is not in correct format, make sure that the document is a text document with a CSV format");
		}
		return manageTasks;
	}
}