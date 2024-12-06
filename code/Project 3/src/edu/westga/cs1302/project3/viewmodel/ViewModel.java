package edu.westga.cs1302.project3.viewmodel;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.model.TaskManager;
import edu.westga.cs1302.project3.model.TaskStorageManager;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

/** Manages utilizing the model and makes properties available to bind the UI elements.
 * 
 * @author Zachary Bailey
 * @version Fall 2024
 */
public class ViewModel {
	private TaskManager manageTasks;
	private ListProperty<Task> tasks;
	private StringProperty title;
	private StringProperty description;
	
	/**
	 * Creates a basic object for the properties in the viewModel, creates 2 default tasks to show that it works
	 */
	public ViewModel() {
		this.manageTasks = new TaskManager();
		
		//Creates default task object 1
		this.manageTasks.addTask(new Task("Default Task 1", "This is the first default task"));
		//Creates default task object 2
		this.manageTasks.addTask(new Task("Default Task 2", "This is the second default task"));
		
		this.tasks = new SimpleListProperty<Task>(FXCollections.observableArrayList(this.manageTasks.getTasks()));
		
		//Task implementation
		this.title = new SimpleStringProperty("");
		this.description = new SimpleStringProperty("");
	}
	
	/**
	 * Gets the list of tasks
	 * @return ListProperty tasks
	 */
	public ListProperty<Task> getTasks() {
		return this.tasks;
	}
	
	/**
	 * Gets the current TaskManager object
	 * @return TaskManager the current TaskManager object
	 */
	public TaskManager getTaskManager() {
		return this.manageTasks;
	}
	
	/**
	 * returns the string property for getTitle
	 * @return StringProperty title
	 */
	public StringProperty getTitle() {
		return this.title;
	}
	
	/**
	 * returns the string property for getDescription
	 * @return StringProperty description
	 */
	public StringProperty getDescription() {
		return this.description;
	}
	
	/**
	 * loads a set of files from a designated file area
	 * @param filePath the desired filePath for the download
	 * @throws IOException 
	 */
	public void loadTasks(String filePath) throws IOException, FileNotFoundException {
			this.manageTasks = TaskStorageManager.loadTasks(filePath);
			this.tasks.set(FXCollections.observableArrayList(this.manageTasks.getTasks()));
	}
	
	/**
	 * saves the current tasks to the selected file
	 * @param filePath the selected filePath
	 * @param manageTask the current TaskManager object
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public void saveTasks(TaskManager manageTask, String filePath) throws IOException, FileNotFoundException {
		if (manageTask == null) {
			throw new IllegalArgumentException("manageTasks can not be null");
		}
		TaskStorageManager.saveTasks(this.getTaskManager(), filePath);
	}
	
	/**
	 * Adds a task to the list of tasks for the task manager
	 */
	public void addTask() {
		String title = this.getTitle().get();
		String description = this.getDescription().get();
		if (title == null || description == null || title.isEmpty() || description.isEmpty()) {
			throw new IllegalArgumentException("Must include title and description to create a valid task");
		}
		Task newTask = new Task(title, description);
		this.manageTasks.addTask(newTask);
		this.tasks.set(FXCollections.observableArrayList(this.manageTasks.getTasks()));
	}
}
