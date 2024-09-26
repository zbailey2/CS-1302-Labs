package edu.westga.cs1302.project1.view;

import java.util.ArrayList;

import edu.westga.cs1302.project1.model.Food;
import edu.westga.cs1302.project1.model.PantryCount;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * Codebehind for the Main Window of the application.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class MainWindow {

	@FXML
	private TextField foodName;

	@FXML
	private ComboBox<String> foodType;

	@FXML
	private ListView<Food> pantryList;

	@FXML
	private TextField quantity;

	/**
	 * Will add food to pantry later when implemented
	 * 
	 * @param event the event that happens when button is pushed
	 */
	@FXML
	public void addFoodToPantry(ActionEvent event) {
		String type = this.foodType.getValue();
		String name = this.foodName.getText();
		try {
			Food currentFood = new Food(name, type);
			this.pantryList.getItems().add(currentFood);
			this.foodName.clear();
		} catch (IllegalArgumentException errorName) {
			Alert errorPopup = new Alert(Alert.AlertType.ERROR);
			errorPopup.setContentText(
					"Unable to create food: " + errorName.getMessage() + ". Please reenter and try again.");
			errorPopup.showAndWait();
		}
	}

	/**
	 * Sets the quantity of the selected food to whatever value was entered into the
	 * text field
	 * 
	 * @param event the event that happens when button is pushed
	 */
	@FXML
	public void setQuantity(ActionEvent event) {
		Food selectedFood = this.pantryList.getSelectionModel().getSelectedItem();
		try {
			int quantity = Integer.parseInt(this.quantity.getText());
			selectedFood.setQuantity(quantity);
			this.pantryList.refresh();
			this.quantity.clear();
		} catch (IllegalArgumentException invalidQuantity) {
			Alert errorPopup = new Alert(Alert.AlertType.ERROR);
			errorPopup.setContentText("Unable to set quantity please enter valid number and try again.");
			errorPopup.showAndWait();
		} catch (NullPointerException nullFood) {
			Alert errorPopup = new Alert(Alert.AlertType.ERROR);
			errorPopup.setContentText("No food selected, try again");
			errorPopup.showAndWait();
		}
	}

	/**
	 * Increases the quantity of the selected food by 1
	 * 
	 * @param event the event that happens when button is pushed
	 */
	@FXML
	public void increaseQuantity(ActionEvent event) {
		Food selectedFood = this.pantryList.getSelectionModel().getSelectedItem();
		try {
			selectedFood.increaseFoodQuantity();
			this.pantryList.refresh();
		} catch (NullPointerException nullFood) {
			Alert errorPopup = new Alert(Alert.AlertType.ERROR);
			errorPopup.setContentText("No food selected, try again");
			errorPopup.showAndWait();
		}
	}

	/**
	 * Decreases quantity of selected food by 1
	 * 
	 * @param event the event that happens when button is pushed
	 */
	@FXML
	public void decreaseQuantity(ActionEvent event) {
		Food selectedFood = this.pantryList.getSelectionModel().getSelectedItem();
		try {
			selectedFood.decreaseFoodQuantity();
			this.pantryList.refresh();
		} catch (NullPointerException nullFood) {
			Alert errorPopup = new Alert(Alert.AlertType.ERROR);
			errorPopup.setContentText("No food selected, try again");
			errorPopup.showAndWait();
		} catch (IllegalArgumentException belowZero) {
			Alert errorPopup = new Alert(Alert.AlertType.ERROR);
			errorPopup.setContentText("Unable to decrease further: " + belowZero.getMessage() + ".");
			errorPopup.showAndWait();
		}
	}

	/**
	 * Removes the selected food from the list view
	 * 
	 * @param event the event that happens when the button is pushed
	 */
	@FXML
	public void removeFood(ActionEvent event) {
		Food selectedFood = this.pantryList.getSelectionModel().getSelectedItem();
		if (selectedFood != null) {
			this.pantryList.getItems().remove(selectedFood);
		} else {
			Alert errorPopup = new Alert(Alert.AlertType.ERROR);
			errorPopup.setContentText("Unable to remove student, please select student to remove.");
			errorPopup.showAndWait();
		}
	}

	/**
	 * Checks the current count of objects in the pantry
	 * 
	 * @param event the event that happens when the button is pushed
	 */
	@FXML
	public void checkCount(ActionEvent event) {
		ArrayList<Food> foodsInList = new ArrayList<Food>();
		foodsInList.addAll(this.pantryList.getItems());
		if (foodsInList.isEmpty()) {
			Alert emptyAlert = new Alert(Alert.AlertType.INFORMATION);
			emptyAlert.setContentText("The pantry is currently empty, add food to check the quantity!");
			emptyAlert.showAndWait();
		} else {
			Alert countAlert = new Alert(Alert.AlertType.INFORMATION);
			countAlert.setContentText(
					"The current quantity of items in this list is " + PantryCount.countPantry(foodsInList));
			countAlert.showAndWait();
		}
	}

	/**
	 * Initializes the fxml elements to make sure they can be injected and work
	 */
	@FXML
	void initialize() {
		assert this.foodName != null : "fx:id=\"foodName\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.foodType != null : "fx:id=\"foodType\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.pantryList != null
				: "fx:id=\"pantryList\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.quantity != null : "fx:id=\"quantity\" was not injected: check your FXML file 'MainWindow.fxml'.";
		this.foodType.getItems().addAll("Vegetable", "Meat", "Bread", "Fruit", "Dessert", "Ingredient");
	}
}
