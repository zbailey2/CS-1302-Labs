package edu.westga.cs1302.project1.test.food;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project1.model.Food;

public class TestConstructor {
	
	@Test
	public void testValidFoodItem() {
	Food foodOne = new Food("Apple", "Fruit");
	assertEquals("Apple", foodOne.getName());
	assertEquals("Fruit", foodOne.getType());
	assertEquals(0, foodOne.getQuantity());
	}
	
	@Test
	public void testNullName() {
		assertThrows(IllegalArgumentException.class, () ->{
			new Food(null, "Fruit");
		});
	}
	
	@Test
	public void testNullType() {
		assertThrows(IllegalArgumentException.class, () ->{
			new Food("Apple", null);
		});
	}
	
	@Test
	public void testBothNull() {
		assertThrows(IllegalArgumentException.class, () ->{
			new Food(null, null);
		});
	}
}
