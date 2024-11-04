package edu.westga.cs1302.project2.view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import edu.westga.cs1302.project2.model.IngredientNameComparator;
import edu.westga.cs1302.project2.model.IngredientTypeComparator;
import edu.westga.cs1302.project2.model.Recipe;
import edu.westga.cs1302.project2.model.RecipeFileReader;
import edu.westga.cs1302.project2.model.RecipeFileWriter;
import edu.westga.cs1302.project2.model.Utility;
import edu.westga.cs1302.project2.model.Ingredient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Codebehind for the Main Window of the application.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class MainWindow {
	@FXML private ComboBox<String> ingredientType;
	@FXML private ListView<Ingredient> ingredientsList;
	@FXML private TextField ingredientName;
	@FXML private ComboBox<Comparator<Ingredient>> sortType;
	@FXML private ListView<Ingredient> recipeList;
	@FXML private TextField recipeName;
	@FXML private TextArea recipeBook;

	@FXML
	void addIngredient(ActionEvent event) {
		try {
			this.ingredientsList.getItems().add(new Ingredient(this.ingredientName.getText(), this.ingredientType.getValue()));
			this.ingredientName.clear();
			this.ingredientType.getSelectionModel().clearSelection();
			this.sortIngredients(event);
		} catch (IllegalArgumentException error) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText("Unable to add ingredient");
			alert.setContentText(error.getMessage());
			alert.showAndWait();
		}
	}

	@FXML
	void removeIngredient(ActionEvent event) {
		Ingredient selectedIngredient = this.ingredientsList.getSelectionModel().getSelectedItem();
		if (selectedIngredient != null) {
			this.ingredientsList.getItems().remove(selectedIngredient);
		}
		this.sortIngredients(event);
	}
	
	@FXML
	void sortIngredients(ActionEvent event) {
		this.ingredientsList.getItems().sort(this.sortType.getValue());
	}
	
	@FXML
	void addIngredientToRecipe(ActionEvent event) {
		Ingredient selectedIngredient = this.ingredientsList.getSelectionModel().getSelectedItem();  
		if (selectedIngredient == null) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText("Unable to add ingredient");
			alert.setContentText("No ingredient chosen please try again");
			alert.showAndWait();
		} else {
			this.recipeList.getItems().add(selectedIngredient);
		}
	}
	
	@FXML
	void createRecipe(ActionEvent event) {
		Recipe recipe = null;
		String recipeName = this.recipeName.getText();
		ArrayList<Ingredient> ingredients = new ArrayList<>();
		for (Ingredient ingredient : this.recipeList.getItems()) {
			ingredients.add(ingredient);
		}
		try {
			recipe = new Recipe(recipeName, ingredients);
			try {
				RecipeFileWriter writer = new RecipeFileWriter();						
				writer.recipeSaver(recipe);
				this.recipeName.clear();
				this.recipeList.getItems().clear();
			} catch (FileNotFoundException fileNotFound) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("File Not Found");
				alert.setContentText("The file to save to could not be found");
				alert.showAndWait();
			} catch (IOException ioException) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error Saving Recipe");
				alert.setContentText("There was an error saving the recipe");
				alert.showAndWait();
			} catch (IllegalStateException repeatRecipe) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Recipe Already Exists");
				alert.setContentText("The recipe youre attempting to save already exists");
				alert.showAndWait();
			}
		} catch (IllegalArgumentException illegalArguments) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("More information needed");
			alert.setContentText("Please enter a name and at least one ingredient");
			alert.showAndWait();
		}
	}
	
	@FXML 
	void specificIngredient(ActionEvent event) throws IOException, FileNotFoundException {
		Ingredient ingredient = this.ingredientsList.getSelectionModel().getSelectedItem();
		if (ingredient == null) {
			Alert errorPopup = new Alert(Alert.AlertType.ERROR);
			errorPopup.setHeaderText("Cannot show recipes");
			errorPopup.setContentText("Please select an ingredient");
			errorPopup.showAndWait();
		} else {
			String ingredientName = ingredient.getName();
			try {
				RecipeFileReader reader = new RecipeFileReader();
				ArrayList<Recipe> allRecipes = reader.specificRecipe(ingredientName);
				try {
					String recipeBook = Utility.listToString(allRecipes);
					this.recipeBook.setText(recipeBook);
				} catch (IllegalArgumentException illegalArgument) {
					Alert errorPopup = new Alert(Alert.AlertType.ERROR);
					errorPopup.setHeaderText("Cannot show recipes");
					errorPopup.setContentText("Make sure recipes exist before searching");
					errorPopup.showAndWait();
				}
			} catch (FileNotFoundException fileNotFound) {
				Alert errorPopup = new Alert(Alert.AlertType.ERROR);
				errorPopup.setHeaderText("File Not Located");
				errorPopup.setContentText("The file could not be found! Creating file with empty list");
				errorPopup.showAndWait();
			} catch (IOException ioException) {
				Alert errorPopup = new Alert(Alert.AlertType.ERROR);
				errorPopup.setHeaderText("Error loading recipes");
				errorPopup.setContentText("An error has occured loading the recipes");
				errorPopup.showAndWait();
			}
		}
	}

	@FXML
	void initialize() {
		//Assertions added from SceneBuilder controller skeleton
		assert this.ingredientName != null : "fx:id=\"ingredientName\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.ingredientType != null : "fx:id=\"ingredientType\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.ingredientsList != null : "fx:id=\"ingredientsList\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.sortType != null : "fx:id=\"sortType\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.recipeList != null : "fx:id=\"recipeList\" was not injected: check your FXML file 'MainWindow.fxml'.";
	    assert this.recipeName != null : "fx:id=\"recipeName\" was not injected: check your FXML file 'MainWindow.fxml'.";
	    assert this.recipeBook != null : "fx:id=\"recipeBook\" was not injected: check your FXML file 'MainWindow.fxml'.";
		
		//Initializes the Ingredients Type ComboBox
		this.ingredientType.getItems().add("Vegetable");
		this.ingredientType.getItems().add("Meat");
		this.ingredientType.getItems().add("Bread");
		this.ingredientType.getItems().add("Fruit");
		this.ingredientType.getItems().add("Spice");
		
		//Initializes the Ingredients sorting method ComboBox
		this.sortType.getItems().add(new IngredientNameComparator());
		this.sortType.getItems().add(new IngredientTypeComparator());
		this.sortType.setValue(this.sortType.getItems().get(0));
	}
}
