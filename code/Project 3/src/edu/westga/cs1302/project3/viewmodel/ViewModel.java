package edu.westga.cs1302.project3.viewmodel;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.model.TaskManager;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

/** Manages utilizing the model and makes properties available to bind the UI elements.
 * 
 * @author Zachary Bailey
 * @version Fall 2024
 */
public class ViewModel {
	private TaskManager defaultTasks;
	private ListProperty<Task> tasks;
	
	/**
	 * Creates a basic object for the properties in the viewModel, creates 2 default tasks to show that it works
	 */
	public ViewModel() {
		this.defaultTasks = new TaskManager();
		
		//Creates default task object 1
		this.defaultTasks.addTask(new Task("Default Task 1", "This is the first default task"));
		//Creates default task object 2
		this.defaultTasks.addTask(new Task("Default Task 2", "This is the second default task"));
		this.tasks = new SimpleListProperty<Task>(FXCollections.observableArrayList(this.defaultTasks.getTasks()));
	}
	
	/**
	 * Gets the list of tasks
	 * @return ListProperty tasks
	 */
	public ListProperty<Task> getTasks() {
		return this.tasks;
	}
}
