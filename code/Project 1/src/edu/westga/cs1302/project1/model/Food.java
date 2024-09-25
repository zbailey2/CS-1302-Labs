package edu.westga.cs1302.project1.model;

/**
 * Creates and manages a pantry
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class Food {
	private final String name;
	private final String type;
	private int quantity;
	
	/**
	 * Creates a new Food object with a type, name, and quantity value
	 * @precondition name != null && type != null
	 * @param name the name of the food
	 * @param type the type of food it is
	 */
	public Food(String name, String type) throws IllegalArgumentException {
		if (name == null) {
			throw new IllegalArgumentException("Name can not be null");
		}
		if (name.isEmpty()) {
			throw new IllegalArgumentException("Name can not be empty");
		}
		if (type == null) {
			throw new IllegalArgumentException("Type can not be null");
		}
		this.name = name;
		this.type = type;
		this.quantity = 0;
	}
	
	/**
	 * returns the name of the food object
	 * @return name the name of the food
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * returns the type of the food object
	 * @return type the type of food it is
	 */
	public String getType() {
		return this.type;
	}
	
	/**
	 * Returns the quantity for the given food value
	 * @return quantity the quantity for the food item
	 */
	public int getQuantity() {
		return this.quantity;
	}
	
	/**
	 * returns the name and quantity as a string in this format: name - quantity
	 * @return returns the name and quantity as a string
	 */
	public String toString() {
		return this.name + " - " + this.quantity;
	}
	
	/**
	 * Increases the value of the quantity by one
	 */
	public void increaseFoodQuantity() {
		this.quantity++;
	}
	
	/**
	 * decreases the quantity by one
	 */
	public void decreaseFoodQuantity() {
		if (this.quantity > 0) {
			this.quantity--;
		} else {
			throw new IllegalArgumentException("Quantity cannot be less than 0");
		}
	}
	
	/**
	 * Sets quantity to whatever value is input into it
	 * @param newQuantity the quantity passed in by the user
	 */
	public void setQuantity(int newQuantity) {
		if (newQuantity < 0) {
			throw new IllegalArgumentException("The quantity can not be set to a number beloew 0");
		}
		this.quantity = newQuantity;
	}
}
