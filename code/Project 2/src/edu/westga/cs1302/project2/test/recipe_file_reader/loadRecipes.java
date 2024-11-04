package edu.westga.cs1302.project2.test.recipe_file_reader;

import static org.junit.Assert.assertEquals;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.westga.cs1302.project2.model.Ingredient;
import edu.westga.cs1302.project2.model.Recipe;
import edu.westga.cs1302.project2.model.RecipeFileReader;
import edu.westga.cs1302.project2.model.RecipeFileWriter;

public class loadRecipes {
	
	@Before
	public void clearFile() throws IOException{
		try(FileWriter writer = new FileWriter(RecipeFileWriter.DATA_FILE, false)){
			writer.write("");
		}
	}

	@Test
	public void testWithOneRecipe() throws IOException {
		String recipeName= "Spaghetti and Meatballs";
		ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
		
		Ingredient ingredient1 = new Ingredient("Spaghetti", "Pasta");
		ingredients.add(ingredient1);
		
		Recipe recipe = new Recipe(recipeName, ingredients);
		RecipeFileWriter writer = new RecipeFileWriter();
		writer.recipeSaver(recipe);
		
		RecipeFileReader reader = new RecipeFileReader();
		ArrayList<Recipe> recipes = reader.loadRecipes();
		Recipe loadedRecipe = recipes.get(0);
		
		assertEquals(recipeName, loadedRecipe.getName());
		assertEquals("Pasta", loadedRecipe.getIngredients().get(0).getType());
		assertEquals("Spaghetti", loadedRecipe.getIngredients().get(0).getName());
	}
	
	@Test
	public void testWithMultipleIngredients() throws IOException{
		String recipeName= "Spaghetti and Meatballs";
		ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
		
		Ingredient ingredient1 = new Ingredient("Spaghetti", "Pasta");
		ingredients.add(ingredient1);
		Ingredient ingredient2 = new Ingredient("Cheese", "Dairy");
		ingredients.add(ingredient2);
		
		Recipe recipe = new Recipe(recipeName, ingredients);
		RecipeFileWriter writer = new RecipeFileWriter();
		writer.recipeSaver(recipe);
		
		RecipeFileReader reader = new RecipeFileReader();
		ArrayList<Recipe> recipes = reader.loadRecipes();
		Recipe loadedRecipe = recipes.get(0);
		
		assertEquals(recipeName, loadedRecipe.getName());
		assertEquals("Pasta", loadedRecipe.getIngredients().get(0).getType());
		assertEquals("Spaghetti", loadedRecipe.getIngredients().get(0).getName());
		assertEquals("Dairy", loadedRecipe.getIngredients().get(1).getType());
		assertEquals("Cheese", loadedRecipe.getIngredients().get(1).getName());
	}
	
	@Test
	public void testEmptyFile() throws IOException {
		RecipeFileReader reader = new RecipeFileReader();
		ArrayList<Recipe> recipes = reader.loadRecipes();
		assertEquals(0, recipes.size());
	}
	
	@Test
	public void testMultipleRecipes() throws IOException{
		String recipeName= "Spaghetti and Meatballs";
		String recipeName2 = "Ham dinner";
		ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
		ArrayList<Ingredient> ingredients2 = new ArrayList<Ingredient>();
		
		Ingredient ingredient1 = new Ingredient("Spaghetti", "Pasta");
		ingredients.add(ingredient1);
		Ingredient ingredient2 = new Ingredient("Cheese", "Dairy");
		ingredients2.add(ingredient2);
		
		Recipe recipe = new Recipe(recipeName, ingredients);
		Recipe recipe2 = new Recipe(recipeName2, ingredients2);
		RecipeFileWriter writer = new RecipeFileWriter();
		writer.recipeSaver(recipe);
		writer.recipeSaver(recipe2);
		
		RecipeFileReader reader = new RecipeFileReader();
		ArrayList<Recipe> recipes = reader.loadRecipes();
		Recipe loadedRecipe = recipes.get(0);
		Recipe otherLoadedRecipe = recipes.get(1);
		
		assertEquals(recipeName, loadedRecipe.getName());
		assertEquals("Pasta", loadedRecipe.getIngredients().get(0).getType());
		assertEquals("Spaghetti", loadedRecipe.getIngredients().get(0).getName());
		assertEquals(recipeName2, otherLoadedRecipe.getName());
		assertEquals("Dairy", otherLoadedRecipe.getIngredients().get(0).getType());
		assertEquals("Cheese", otherLoadedRecipe.getIngredients().get(0).getName());
	}
}
