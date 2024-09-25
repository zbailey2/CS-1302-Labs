package edu.westga.cs1302.project1.test.food;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project1.model.Food;

public class TestConstructor {
	
	@Test
	void testValidFoodItem() {
	Food foodOne = new Food("Apple", "Fruit");
	assertEquals("Apple", foodOne.getName());
	assertEquals("Fruit", foodOne.getType());
	assertEquals(0, foodOne.getQuantity());
	}
	
	@Test
	void testNullName() {
		assertThrows(IllegalArgumentException.class, () ->{
			new Food(null, "Fruit");
		});
	}
	
	@Test
	void testNullType() {
		assertThrows(IllegalArgumentException.class, () ->{
			new Food("Apple", null);
		});
	}
	
	@Test
	void testBothNull() {
		assertThrows(IllegalArgumentException.class, () ->{
			new Food(null, null);
		});
	}
}
