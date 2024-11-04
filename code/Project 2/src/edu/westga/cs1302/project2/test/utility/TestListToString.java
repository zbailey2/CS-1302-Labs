package edu.westga.cs1302.project2.test.utility;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.Test;

import edu.westga.cs1302.project2.model.Ingredient;
import edu.westga.cs1302.project2.model.Recipe;
import edu.westga.cs1302.project2.model.Utility;

public class TestListToString {
	
	@Test
	public void testWhenListEmpty() {
		ArrayList<Recipe> recipes = new ArrayList<>();
		String result = Utility.listToString(recipes);
		assertEquals("", result);
	}
	
	@Test
	public void testWithOneRecipe() {
		ArrayList<Recipe> recipes = new ArrayList<>();
		ArrayList<Ingredient> ingredients = new ArrayList<>();
		
		Ingredient ingredient1 = new Ingredient("Chicken", "Meat");
		ingredients.add(ingredient1);
		
		Recipe recipe = new Recipe("Chicken Dinner", ingredients);
		recipes.add(recipe);
		
		String result = Utility.listToString(recipes);
		assertEquals("Chicken Dinner"+System.lineSeparator()+"Chicken-Meat"+System.lineSeparator(), result);
	}
	
	@Test
	public void testWithMultipleRecipes() {
		ArrayList<Recipe> recipes = new ArrayList<>();
		ArrayList<Ingredient> ingredients = new ArrayList<>();
		ArrayList<Ingredient> ingredients2 = new ArrayList<>();
		
		Ingredient ingredient1 = new Ingredient("Chicken", "Meat");
		ingredients.add(ingredient1);
		
		Ingredient ingredient2 = new Ingredient("Ham", "Meat");
		ingredients2.add(ingredient2);
		
		Recipe recipe = new Recipe("Chicken Dinner", ingredients);
		recipes.add(recipe);
		
		Recipe recipe2 = new Recipe("Ham Dinner", ingredients2);
		recipes.add(recipe2);
		
		String result = Utility.listToString(recipes);
		assertEquals("Chicken Dinner"+System.lineSeparator()+"Chicken-Meat"+System.lineSeparator()+System.lineSeparator()+"Ham Dinner" + System.lineSeparator()+"Ham-Meat"+System.lineSeparator(), result);
	}
	
	@Test
	public void testNullRecipes() {
		assertThrows(IllegalArgumentException.class, ()->{Utility.listToString(null);});
	}
}
