package edu.westga.cs1302.project1.model;

/**
 * Creates and manages a pantry
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class Pantry {
	
	private String name;
	
	/**
	 * Create a new student with the specified name
	 * 
	 * @precondition name != null
	 * @postcondition getName() == name
	 * 
	 * @param name  the name of the item
	 * @throws IllegalArgumentException when precondition is violated
	 */
	public Pantry(String name) throws IllegalArgumentException {
		
		this.setName(name);
	}
	
	/**
	 * Return the name of the student
	 * 
	 * @return the name of the student
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Sets the name of the object
	 * 
	 * @param name a string name
	 */
	public void setName(String name) {
		this.name = name;
	}
}
