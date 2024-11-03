package edu.westga.cs1302.project2.model;

import java.util.ArrayList;

/**
 * Class for creating a recipe, and all of its helper methods
 * 
 * @author Zachary Bailey
 * @version Fall 2024
 */
public class Recipe {
	
	private String recipeName;
	private ArrayList<Ingredient> ingredients;
	
	/** Create a new recipe with the specified name and list of ingredients
	 * 
	 * @precondition name != null && !name.isEmpty() &&
	 * 				 ingredients != null && !ingredients.isEmpty()
	 * @postcondition getName() == name &&
	 * 				  getIngredients() == ingredients
	 * 
	 * @param recipeName the name of the recipe
	 * @param ingredients the list of ingredients in the recipe
	 */
	public Recipe(String recipeName, ArrayList<Ingredient> ingredients) {
		if (recipeName == null || recipeName.isEmpty()) {
			throw new IllegalArgumentException("The Recipe Name can not be null or empty");
		}
		if (ingredients == null || ingredients.isEmpty()) {
			throw new IllegalArgumentException("The ingredients can not be null or empty");
		}
		this.recipeName = recipeName;
		this.ingredients = new ArrayList<>(ingredients);
	}
	
	/** Return the name of the Recipe
	 * 
	 * @return the name of the Recipe
	 */
	public String getName() {
		return this.recipeName;
	}
	
	/** Return the list of ingredients  in the recipe
	 * 
	 * @return the list of ingredients
	 */
	public ArrayList<Ingredient> getIngredients() {
		return this.ingredients;
	}
}
