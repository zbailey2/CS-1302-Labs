package edu.westga.cs1302.project2.test.comparator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project2.model.Ingredient;
import edu.westga.cs1302.project2.model.IngredientTypeComparator;

public class TestTypeComparator {
	
	//No tests for null name/type since Ingredient can never be null
	
	@Test
	public void testWithDifferentTypes() {
		IngredientTypeComparator comparator = new IngredientTypeComparator();
		Ingredient ingredient1 = new Ingredient("Apple", "Fruit");
		Ingredient ingredient2 = new Ingredient("Broccoli", "Vegetable");
		
		assertTrue(comparator.compare(ingredient1, ingredient2) < 0);
		assertTrue(comparator.compare(ingredient2, ingredient1) > 0);
	}
	
	@Test
	public void testWithSameTypes() {
		IngredientTypeComparator comparator = new IngredientTypeComparator();
		Ingredient ingredient1 = new Ingredient("Apple", "Fruit");
		Ingredient ingredient2 = new Ingredient("Pear", "Fruit");
		
		assertTrue(comparator.compare(ingredient1, ingredient2) == 0);
	}
	
	@Test
	public void testWithSimilarTypes() {
		IngredientTypeComparator comparator = new IngredientTypeComparator();
		Ingredient ingredient1 = new Ingredient("Apple", "Fruit");
		Ingredient ingredient2 = new Ingredient("Pineapple", "Fruity");
		
		assertTrue(comparator.compare(ingredient1, ingredient2) < 0);
		assertTrue(comparator.compare(ingredient2, ingredient1) > 0);
	}
	
	@Test
	public void testCaseSensitive() {
		IngredientTypeComparator comparator = new IngredientTypeComparator();
		Ingredient ingredient1 = new Ingredient("Apple", "Fruit");
		Ingredient ingredient2 = new Ingredient("Pineapple", "fruit");
		
		assertTrue(comparator.compare(ingredient1, ingredient2) == 0);
	}
	
	@Test
	public void testWithMultipleTypes() {
		IngredientTypeComparator comparator = new IngredientTypeComparator();
		ArrayList<Ingredient> ingredients = new ArrayList<>();
		Ingredient ingredient1 = new Ingredient("Apple", "Fruit");
		Ingredient ingredient2 = new Ingredient("Broccoli", "Vegetable");
		Ingredient ingredient3 = new Ingredient("Steak", "Meat");
		Ingredient ingredient4 = new Ingredient("Chicken", "Meat");
		Ingredient ingredient5 = new Ingredient("Baguette", "Bread");
		
		ingredients.add(ingredient1);
		ingredients.add(ingredient2);
		ingredients.add(ingredient3);
		ingredients.add(ingredient4);
		ingredients.add(ingredient5);
		
		ingredients.sort(comparator);
		
		assertEquals(ingredient5, ingredients.get(0));
		assertEquals(ingredient1, ingredients.get(1));
		assertEquals(ingredient3, ingredients.get(2));
		assertEquals(ingredient4, ingredients.get(3));
		assertEquals(ingredient2, ingredients.get(4));
	}
}
