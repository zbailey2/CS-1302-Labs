package edu.westga.cs1302.project1.test.food;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

import edu.westga.cs1302.project1.model.Food;

public class TestQuantityMethods {

	// The following tests, test for the setQuantity() method
	@Test
	public void testSetQuantity() {
		Food foodOne = new Food("Apple", "Fruit");

		foodOne.setQuantity(5);

		assertEquals(5, foodOne.getQuantity());
	}

	@Test
	public void testNegativeInputForNegativeValue() {
		Food foodOne = new Food("Apple", "Fruit");

		assertThrows(IllegalArgumentException.class, () -> {
			foodOne.setQuantity(-1);
		});
	}

	@Test
	public void testWhenQuantityChangedMultipleTimes() {
		Food foodOne = new Food("Apple", "Fruit");

		foodOne.setQuantity(5);
		foodOne.setQuantity(20);

		assertEquals(20, foodOne.getQuantity());
	}

	@Test
	public void testWithMultipleFoods() {
		Food foodOne = new Food("Steak", "Meat");
		Food foodTwo = new Food("Eggs", "Ingredient");

		foodOne.setQuantity(3);
		foodTwo.setQuantity(6);

		assertEquals(3, foodOne.getQuantity());
		assertEquals(6, foodTwo.getQuantity());
	}

	// The following tests, test the increaseFoodQuantity() method
	@Test
	public void testIncreaseQuantity() {
		Food foodOne = new Food("Cabbage", "Vegetable");

		foodOne.increaseFoodQuantity();

		assertEquals(1, foodOne.getQuantity());
	}

	@Test
	public void testWhenQuantityIsSet() {
		Food foodOne = new Food("Chicken", "Meat");

		foodOne.setQuantity(5);
		foodOne.increaseFoodQuantity();

		assertEquals(6, foodOne.getQuantity());
	}

	@Test
	public void testWithMultipleIncrements() {
		Food foodOne = new Food("Broccoli", "Vegetable");

		foodOne.increaseFoodQuantity();
		foodOne.increaseFoodQuantity();
		foodOne.increaseFoodQuantity();
		foodOne.increaseFoodQuantity();

		assertEquals(4, foodOne.getQuantity());
	}

	@Test
	public void testWithMultipleObjectsAndIncrements() {
		Food foodOne = new Food("Broccoli", "Vegetable");
		Food foodTwo = new Food("Lamb", "Meat");

		foodTwo.increaseFoodQuantity();
		foodTwo.increaseFoodQuantity();

		foodOne.increaseFoodQuantity();
		foodOne.increaseFoodQuantity();
		foodOne.increaseFoodQuantity();
		foodOne.increaseFoodQuantity();

		assertEquals(4, foodOne.getQuantity());
		assertEquals(2, foodTwo.getQuantity());
	}

	// The following tests the decreaseFoodQuantity() method
	@Test
	public void testDecreaseQuantityAtZero() {
		Food foodOne = new Food("Cinnamon", "Ingredient");

		assertThrows(IllegalArgumentException.class, () -> {
			foodOne.decreaseFoodQuantity();
		});
	}

	@Test
	public void testDecreaseQuantityAtPositiveValue() {
		Food foodOne = new Food("Sugar", "Ingredient");

		foodOne.setQuantity(3);
		foodOne.decreaseFoodQuantity();

		assertEquals(2, foodOne.getQuantity());
	}

	@Test
	public void testDecreaseQuantityToZero() {
		Food foodOne = new Food("Cake", "Dessert");

		foodOne.setQuantity(1);
		foodOne.decreaseFoodQuantity();

		assertEquals(0, foodOne.getQuantity());
	}

	@Test
	public void testWhenDecreasingMultipleTimes() {
		Food foodOne = new Food("Eggs", "Ingredients");

		foodOne.setQuantity(6);
		foodOne.decreaseFoodQuantity();
		foodOne.decreaseFoodQuantity();
		foodOne.decreaseFoodQuantity();

		assertEquals(3, foodOne.getQuantity());
	}
}
