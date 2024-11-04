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
			result += ingredients.get(index);
			if (index < ingredients.size() - 1) {
				result += ",";
			}
		}
		return result + System.lineSeparator();
	}
	
	/**
	 * Takes a list of recipes and converts them to a String
	 * @param recipes an ArrayList of recipes
	 * @return a string of all recipes
	 */
	public static String listToString(ArrayList<Recipe> recipes) {
		if (recipes == null) {
			throw new IllegalArgumentException("recipe can not be null");
		}
		String result = "";
		for (int index = 0; index < recipes.size(); index++) {
			result += recipeToString(recipes.get(index));
			if (index < recipes.size() - 1) {
				result += System.lineSeparator();
			}
		}
		return result;
	}
}
