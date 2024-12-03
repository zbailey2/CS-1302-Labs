package edu.westga.cs1302.project3.model;

/** Store information for a single Task
 * 
 * @author Zachary Bailey
 * @version Fall 2024
 */
public class Task {
	
	private String title;
	private String description;
	
	/** Create a new task with a title and a description
	 * 
	 * @precondition title != null && !title.isEmpty() &&
	 * 				 description != null && !description.isEmpty()
	 * @postcondition getName() == name
	 * 				  getType() == type
	 * 
	 * @param title the title of the task
	 * @param description the description of the task
	 */
	public Task(String title, String description) {
		if (title == null || title.isEmpty()) {
			throw new IllegalArgumentException("Title cannot be null or empty");
		}
		if (description == null || description.isEmpty()) {
			throw new IllegalArgumentException("Description cannot be null or empty");
		}
		this.title = title;
		this.description = description;
	}
	
	//Getters for fields
	
	/**
	 * Returns the value for title
	 * @return title the title of the task
	 */
	public String getTitle() {
		return this.title;
	}
	
	/**
	 * Returns the description of the task
	 * @return description the description of the task
	 */
	public String getDescription() {
		return this.description;
	}
}
