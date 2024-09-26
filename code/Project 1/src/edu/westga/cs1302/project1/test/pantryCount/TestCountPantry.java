package edu.westga.cs1302.project1.test.pantryCount;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.Test;

import edu.westga.cs1302.project1.model.Food;
import edu.westga.cs1302.project1.model.PantryCount;

public class TestCountPantry {

	@Test
	public void testWithEmptyArray() {
		ArrayList<Food> foodsInList = new ArrayList<Food>();

		int totalQuantity = PantryCount.countPantry(foodsInList);

		assertEquals(0, totalQuantity);
	}

	@Test
	public void testWithOneFood() {
		ArrayList<Food> foodsInList = new ArrayList<Food>();
		Food foodOne = new Food("Apple", "Fruit");

		foodOne.setQuantity(3);
		foodsInList.add(foodOne);

		int totalQuantity = PantryCount.countPantry(foodsInList);
		assertEquals(3, totalQuantity);
	}

	@Test
	public void testWithTwoFoods() {
		ArrayList<Food> foodsInList = new ArrayList<Food>();
		Food foodOne = new Food("Apple", "Fruit");
		Food foodTwo = new Food("Broccoli", "Vegetable");

		foodTwo.setQuantity(6);
		foodOne.setQuantity(3);
		foodsInList.add(foodOne);
		foodsInList.add(foodTwo);

		int totalQuantity = PantryCount.countPantry(foodsInList);
		assertEquals(9, totalQuantity);
	}

	@Test
	public void testWithMultipleFoods() {
		ArrayList<Food> foodsInList = new ArrayList<Food>();
		Food foodOne = new Food("Apple", "Fruit");
		Food foodTwo = new Food("Broccoli", "Vegetable");
		Food foodThree = new Food("Chicken", "Meat");

		foodTwo.setQuantity(9);
		foodOne.setQuantity(12);
		foodThree.increaseFoodQuantity();
		foodsInList.add(foodOne);
		foodsInList.add(foodTwo);
		foodsInList.add(foodThree);

		int totalQuantity = PantryCount.countPantry(foodsInList);
		assertEquals(22, totalQuantity);
	}

	@Test
	public void testWithLargeQuantities() {
		ArrayList<Food> foodsInList = new ArrayList<Food>();
		Food foodOne = new Food("Apple", "Fruit");
		Food foodTwo = new Food("Broccoli", "Vegetable");
		Food foodThree = new Food("Chicken", "Meat");

		foodTwo.setQuantity(1000);
		foodOne.setQuantity(1200);
		foodThree.setQuantity(1500);
		foodsInList.add(foodOne);
		foodsInList.add(foodTwo);
		foodsInList.add(foodThree);

		int totalQuantity = PantryCount.countPantry(foodsInList);
		assertEquals(3700, totalQuantity);
	}

	@Test
	public void testWithNullList() {
		assertThrows(IllegalArgumentException.class, () -> {
			PantryCount.countPantry(null);
		});
	}

	@Test
	public void testThatValueResetsWhenFoodiSRemoved() {
		ArrayList<Food> foodsInList = new ArrayList<Food>();
		Food foodOne = new Food("Apple", "Fruit");

		foodOne.setQuantity(3);
		foodsInList.add(foodOne);
		int totalQuantity = PantryCount.countPantry(foodsInList);
		foodsInList.remove(foodOne);

		totalQuantity = PantryCount.countPantry(foodsInList);
		assertEquals(0, totalQuantity);
	}
}
