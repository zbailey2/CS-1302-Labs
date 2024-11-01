package edu.westga.cs1302.project2.model;

import java.util.Comparator;

/**
 * Sort ingredients according to name
 * 
 * @author Zachary Bailey
 * @version Fall 2024
 */
public class IngredientNameComparator implements Comparator<Ingredient> {

	@Override
	public int compare(Ingredient o1, Ingredient o2) {
		return o1.getName().compareToIgnoreCase(o2.getName());
	}
	
	@Override
	public String toString() {
		return "Name";
	}

}