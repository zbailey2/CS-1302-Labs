package edu.westga.cs1302.project1.model;

import java.util.ArrayList;

/**
 * Creates and manages a pantry
 * 
 * @author Zachary Bailey
 * @version Fall 2024
 */
public class PantryCount {

	/**
	 * Calculates the quantity of the foods in the list
	 * 
	 * @param foodsInList the foods in list and their associated quantities
	 * @return totalQuantity the total quantity
	 */
	public static int countPantry(ArrayList<Food> foodsInList) {
		int totalQuantity = 0;
		if (foodsInList == null) {
			throw new IllegalArgumentException("List should not be null");
		}
		for (Food food : foodsInList) {
			totalQuantity += food.getQuantity();
		}
		return totalQuantity;
	}
}
