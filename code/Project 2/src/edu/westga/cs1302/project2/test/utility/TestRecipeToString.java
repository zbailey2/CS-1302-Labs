package edu.westga.cs1302.project2.test.utility;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.Test;

import edu.westga.cs1302.project2.model.Ingredient;
import edu.westga.cs1302.project2.model.Recipe;
import edu.westga.cs1302.project2.model.Utility;

public class TestRecipeToString {
	
	@Test
	public void testValidRecipe() {
		String recipeName = "Spaghetti and Meatballs";
		ArrayList<Ingredient> ingredients = new ArrayList<>();
		Ingredient ingredient1 = new Ingredient("Spaghetti", "Pasta");
		ingredients.add(ingredient1);
		Ingredient ingredient2 = new Ingredient("Meatballs", "Meat");
		ingredients.add(ingredient2);
		
		Recipe recipe = new Recipe(recipeName, ingredients);
		String expected = "Spaghetti and Meatballs"+System.lineSeparator()+"Spaghetti-Pasta,Meatballs-Meat"+System.lineSeparator();
		assertEquals(expected, Utility.recipeToString(recipe));
	}
	
	@Test
	public void testInvalidRecipe() {
		assertThrows(IllegalArgumentException.class, ()->{Utility.recipeToString(null);});
	}
	
	@Test
	public void testWithOneIngredient() {
		String recipeName = "Apple";
		ArrayList<Ingredient> ingredients = new ArrayList<>();
		Ingredient ingredient1 = new Ingredient("Apple", "Fruit");
		ingredients.add(ingredient1);
		
		Recipe recipe = new Recipe(recipeName, ingredients);
		String expected = "Apple"+System.lineSeparator()+"Apple-Fruit"+System.lineSeparator();
		assertEquals(expected, Utility.recipeToString(recipe));
	}
}
