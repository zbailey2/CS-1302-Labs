package edu.westga.cs1302.project2.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Loads recipes from a file, and can finds a recipe and return a list of
 * recipes with a specified ingredient
 * 
 * @author Zachary Bailey
 * @version Fall 2024
 */
public class RecipeFileReader {
	public static final String DATA_FILE = "recipes.txt";

	/**
	 * Loads the current list of recipes
	 * 
	 * @return recipe, an ArrayList of recipes
	 */
	public ArrayList<Recipe> loadRecipes() throws IOException, FileNotFoundException {
		ArrayList<Recipe> recipes = new ArrayList<>();
		File inputFile = new File(DATA_FILE);
		if (!inputFile.exists()) {
			return recipes;
		}
		try (Scanner reader = new Scanner(inputFile)) {
			while (reader.hasNextLine()) {
				String recipeName = reader.nextLine();
				String baseLine = reader.nextLine();
				String strippedLine = baseLine.strip();
				String[] parts = strippedLine.split(",");
				ArrayList<Ingredient> allIngredients = new ArrayList<>();
				for (String ingredients : parts) {
					String[] itemParts = ingredients.split("-");
					String ingredientName = itemParts[0];
					String ingredientType = itemParts[1];
					Ingredient currentIngredient = new Ingredient(ingredientName, ingredientType);
					allIngredients.add(currentIngredient);
				}
				Recipe currentRecipe = new Recipe(recipeName, allIngredients);
				recipes.add(currentRecipe);
			}
		} catch (IOException ioError) {
			throw new IOException("There was an error on line");
		}
		return recipes;
	}
	
	/**
	 * Returns a list of recipes that include the given ingredient name
	 * @param ingredientName name of the ingredient being looked for
	 * @return an arrayList of recipes that contain that ingredient
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public ArrayList<Recipe> specificRecipe(String ingredientName) throws FileNotFoundException, IOException {
		ArrayList<Recipe> allRecipes = this.loadRecipes();
		ArrayList<Recipe> specificRecipes = new ArrayList<>();
		File inputFile = new File(DATA_FILE);
		if (!inputFile.exists() || inputFile.length() == 0) {
			return specificRecipes;
		}
		for (Recipe recipe : allRecipes) {
			for (Ingredient ingredient : recipe.getIngredients()) {
				if (ingredient.getName().equalsIgnoreCase(ingredientName)) {
					specificRecipes.add(recipe);
				}
			}
		}
		return specificRecipes;
	}
}
