package edu.westga.cs1302.project2.test.comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project2.model.IngredientNameComparator;
import edu.westga.cs1302.project2.model.IngredientTypeComparator;

public class TestToString {
	
	
	@Test
	void testIngredientTypeComparatorToString() {
		IngredientTypeComparator comparator = new IngredientTypeComparator();
		
		String result = comparator.toString();
		
		assertEquals("Type", result);
	}
	
	@Test
	void testIngredientNameComparatorToString() {
		IngredientNameComparator comparator = new IngredientNameComparator();
		String result = comparator.toString();
		
		assertEquals("Name", result);
	}
}
