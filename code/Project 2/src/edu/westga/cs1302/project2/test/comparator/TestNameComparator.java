package edu.westga.cs1302.project2.test.comparator;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project2.model.Ingredient;
import edu.westga.cs1302.project2.model.IngredientNameComparator;

public class TestNameComparator {
	
	//No tests for null name/type since Ingredient can never be null
	
	@Test
	public void testDifferentNames() {
		IngredientNameComparator comparator = new IngredientNameComparator();
		Ingredient ingredient1 = new Ingredient("Apple", "Fruit");
		Ingredient ingredient2 = new Ingredient("Broccoli", "Vegetable");
		
		
		assertTrue(comparator.compare(ingredient1, ingredient2) < 0);
		assertTrue(comparator.compare(ingredient2, ingredient1) > 0);
	}
	
	@Test
	public void testSameNames() {
		IngredientNameComparator comparator = new IngredientNameComparator();
		Ingredient ingredient1 = new Ingredient("Apple", "Fruit");
		Ingredient ingredient2 = new Ingredient("Apple", "Fruit");
		
		assertEquals(0,comparator.compare(ingredient1, ingredient2));
	}
	
	@Test
	public void testSimilarNames() {
		IngredientNameComparator comparator = new IngredientNameComparator();
		Ingredient ingredient1 = new Ingredient("Apple", "Fruit");
		Ingredient ingredient2 = new Ingredient("Apricot", "Fruit");
		
		assertTrue(comparator.compare(ingredient1, ingredient2) < 0);
		assertTrue(comparator.compare(ingredient2, ingredient1) > 0);
	}
	
	@Test
	public void testCaseSensitive() {
		IngredientNameComparator comparator = new IngredientNameComparator();
		Ingredient ingredient1 = new Ingredient("Apple", "Fruit");
		Ingredient ingredient2 = new Ingredient("apple", "Fruit");
		
		assertEquals(0, comparator.compare(ingredient1, ingredient2));
	}
	
	@Test
	public void testWithMultipleIngredients() {
		IngredientNameComparator comparator = new IngredientNameComparator();
		ArrayList<Ingredient> ingredients = new ArrayList<>();
		Ingredient ingredient1 = new Ingredient("Apple", "Fruit");
		Ingredient ingredient2 = new Ingredient("Yam", "Vegetable");
		Ingredient ingredient3 = new Ingredient("Steak", "Meat");
		
		ingredients.add(ingredient1);
		ingredients.add(ingredient2);
		ingredients.add(ingredient3);
		
		ingredients.sort(comparator);
		
		assertEquals(ingredient1, ingredients.get(0));
		assertEquals(ingredient3, ingredients.get(1));
		assertEquals(ingredient2, ingredients.get(2));
	}
}
