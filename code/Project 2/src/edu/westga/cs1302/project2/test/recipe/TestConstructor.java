package edu.westga.cs1302.project2.test.recipe;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.Test;

import edu.westga.cs1302.project2.model.Ingredient;
import edu.westga.cs1302.project2.model.Recipe;

public class TestConstructor {

	
	@Test
	public void testNullName() {
		String recipeName= null;
		ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
		Ingredient ingredient1 = new Ingredient("Potato", "Vegetable");
		ingredients.add(ingredient1);
		assertThrows(IllegalArgumentException.class, ()->{new Recipe(recipeName, ingredients);});
	}
	
	@Test
	public void testNullList() {
		String recipeName = "Spaghetti and Meatballs";
		ArrayList<Ingredient> ingredients = null;
		assertThrows(IllegalArgumentException.class, ()->{new Recipe(recipeName, ingredients);});
	}
	
	@Test
	public void testEmptyName() {
		String recipeName= "";
		ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
		Ingredient ingredient1 = new Ingredient("Potato", "Vegetable");
		ingredients.add(ingredient1);
		assertThrows(IllegalArgumentException.class, ()->{new Recipe(recipeName, ingredients);});
	}
	
	@Test
	public void testEmptyList() {
		String recipeName= "Lasagna";
		ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
		assertThrows(IllegalArgumentException.class, ()->{new Recipe(recipeName, ingredients);});
	}
	
	@Test
	public void testValidRecipe() {
		String recipeName= "Spaghetti and Meatballs";
		ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
		
		Ingredient ingredient1 = new Ingredient("Spaghetti", "Pasta");
		ingredients.add(ingredient1);
		
		Ingredient ingredient2 = new Ingredient("Meatball", "Meat");
		ingredients.add(ingredient2);
		
		Recipe recipe = new Recipe(recipeName, ingredients);
		
		assertEquals(recipeName, recipe.getName());
		assertEquals(ingredient1, recipe.getIngredients().get(0));
		assertEquals(ingredient2, recipe.getIngredients().get(1));
	}
}
