package edu.westga.cs1302.project3.model;

import java.util.ArrayList;

/** Manage methods for specific tasks
 * 
 * @author Zachary Bailey
 * @version Fall 2024
 */
public class TaskManager {
	private ArrayList<Task> tasks;
	
	/**
	 * Constructor for creating a new TaskManager with a list of tasks
	 */
	public TaskManager() {
		this.tasks = new ArrayList<Task>();
	}
	
	/**
	 * Adds the selected task into a ArrayList of task items
	 * @param task the task to be added
	 */
	public void addTask(Task task) {
		if (task == null) {
			throw new IllegalArgumentException("Task can not be null");
		}
		this.tasks.add(task);
	}
	
	/**
	 * Removes the selected task out of the ArrayList of task items
	 * @param task the task to be removed
	 */
	public void removeTask(Task task) {
		if (task == null) {
			throw new IllegalArgumentException("Task can not be null");
		}
		if (this.tasks.isEmpty()) {
			throw new IllegalArgumentException("No tasks inside task manager, cannot remove anything");
		}
		if (!this.tasks.contains(task)) {
			throw new IllegalArgumentException("Task does not exist, can not remove it");
		}
		this.tasks.remove(task);
	}
	
	//Getters for TaskManager
	
	/**
	 * Returns the current ArrayList of tasks in the task manager
	 * @return tasks, an array list of tasks
	 */
	public ArrayList<Task> getTasks() {
		return this.tasks;
	}
}
