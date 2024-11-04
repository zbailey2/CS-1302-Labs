package edu.westga.cs1302.project2.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Save the information currently stored in the recipe to a file
 * 
 * @author Zachary Bailey
 * @version Fall 2024
 */
public class RecipeFileWriter {

	public static final String DATA_FILE = "recipes.txt";

	/**
	 * Writes the current recipe into a save file containing other recipes
	 * 
	 * @param recipe the current recipe being saved
	 * @throws IllegalStateException if a recipe with the same name already exists
	 * @throws IOException           if there is any other issues in to IO process
	 * 
	 */
	public void recipeSaver(Recipe recipe) throws IOException, IllegalStateException, FileNotFoundException {
		if (recipe == null) {
			throw new IllegalArgumentException("Recipe can not be null");
		}
		File inputFile = new File(DATA_FILE);
		try (Scanner reader = new Scanner(inputFile)) {
			while (reader.hasNextLine()) {
				String currentLine = reader.nextLine();
				if (currentLine.equalsIgnoreCase(recipe.getName())) {
					throw new IllegalStateException("recipe already exists");
				}
			}
		} catch (FileNotFoundException fileNotFound) {
			throw new FileNotFoundException("Error, file could not be found");
		}
		try (FileWriter writer = new FileWriter(DATA_FILE, true)) {
			writer.write(Utility.recipeToString(recipe));
		} catch (IOException ioError) {
			throw new IOException("Error writing to file");
		}
	}
}
