package edu.westga.cs1302.project2.model;

import java.util.ArrayList;

/**
 * A utility class that helps with converting recipes to Strings
 * 
 * @author Zachary Bailey
 * @version Fall 2024
 */
public class Utility {
	
	/**
	 * Converts the current recipe into a String format
	 * 
	 * @param recipe the Recipe that needs to be converted
	 * @return A String representation of the Recipe
	 */
	public static String recipeToString(Recipe recipe) {
		if (recipe == null) {
			throw new IllegalArgumentException("Recipe can not be null");
		}
		String result = recipe.getName() + System.lineSeparator();
		ArrayList<Ingredient> ingredients = recipe.getIngredients();
		
		for (int index = 0; index < ingredients.size(); index++) {
			result += ingredients.get(index).toString() + System.lineSeparator();
			if (index < ingredients.size() - 1) {
				result += ", ";
			}
		}
		return result;
	}
}
