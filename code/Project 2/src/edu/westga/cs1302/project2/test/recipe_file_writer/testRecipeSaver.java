package edu.westga.cs1302.project2.test.recipe_file_writer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

import edu.westga.cs1302.project2.model.Ingredient;
import edu.westga.cs1302.project2.model.Recipe;
import edu.westga.cs1302.project2.model.RecipeFileWriter;

public class testRecipeSaver {
	
	@Test
	public void testNullRecipe() {
		assertThrows(IllegalArgumentException.class, ()->{
			RecipeFileWriter.recipeSaver(null);
		});
	}
	
	@Test
	public void testValidRecipe() throws IOException{
		String recipeName= "Spaghetti and Meatballs";
		ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
		
		Ingredient ingredient1 = new Ingredient("Spaghetti", "Pasta");
		ingredients.add(ingredient1);
		
		Recipe recipe = new Recipe(recipeName, ingredients);
		RecipeFileWriter.recipeSaver(recipe);
		
		File inputFile = new File(RecipeFileWriter.DATA_FILE);
		try(Scanner reader = new Scanner(inputFile)){
			assertEquals("Spaghetti and Meatballs", reader.nextLine());
			assertEquals("Spaghetti-Pasta", reader.nextLine());
		}
	}
	
	@Test
	public void testMultipleRecipes() throws IOException{
		String recipeName= "Spaghetti and Meatballs";
		ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
		
		Ingredient ingredient1 = new Ingredient("Spaghetti", "Pasta");
		ingredients.add(ingredient1);
		
		Recipe recipe = new Recipe(recipeName, ingredients);
		RecipeFileWriter.recipeSaver(recipe);
		
		String recipeName2 = "Apple";
		ArrayList<Ingredient> ingredients2 = new ArrayList<Ingredient>();
		
		Ingredient ingredient3 = new Ingredient("Apple", "Fruit");
		ingredients2.add(ingredient3);		
		Recipe recipe2 = new Recipe(recipeName2, ingredients2);
		RecipeFileWriter.recipeSaver(recipe2);

		
		File inputFile = new File(RecipeFileWriter.DATA_FILE);
		try(Scanner reader = new Scanner(inputFile)){
			assertEquals("Spaghetti and Meatballs", reader.nextLine());
			assertEquals("Spaghetti-Pasta", reader.nextLine());
			assertEquals("Apple", reader.nextLine());
			assertEquals("Apple-Fruit", reader.nextLine());
		}	
	}
	
	@Test
	public void testSameRecipes() throws IOException {
		String recipeName = "Lasagna";
		ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
		
		Ingredient ingredient1 = new Ingredient("Red Sauce", "Sauce");
		ingredients.add(ingredient1);
		Recipe recipe = new Recipe(recipeName, ingredients);
		RecipeFileWriter.recipeSaver(recipe);
		
		String recipeName2 = "Lasagna";
		ArrayList<Ingredient> ingredients2 = new ArrayList<Ingredient>();
		
		Ingredient ingredient2 = new Ingredient("Red Sauce", "Sauce");
		ingredients2.add(ingredient2);
		Recipe recipe2 = new Recipe(recipeName2, ingredients2);
		
		assertThrows(IllegalStateException.class, ()->{
			RecipeFileWriter.recipeSaver(recipe2);
		});
	}
}
