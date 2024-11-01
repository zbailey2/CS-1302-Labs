package edu.westga.cs1302.project2.model;

import java.util.Comparator;

/**
 * Sort bill by Type
 * 
 * @author Zachary Bailey
 * @version Fall 2024
 */
public class IngredientTypeComparator implements Comparator<Ingredient> {

	@Override
	public int compare(Ingredient o1, Ingredient o2) {
		return o1.getType().compareToIgnoreCase(o2.getType());
	}
}
