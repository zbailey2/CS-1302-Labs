package edu.westga.cs1302.project1.view;

import edu.westga.cs1302.project1.model.Food;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/** Codebehind for the Main Window of the application.
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
	
    /**
     * Will add food to pantry later when implemented
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
    		errorPopup.setContentText("Unable to create food: " + errorName.getMessage()
    		+ ". Please reenter and try again.");
    		errorPopup.showAndWait();
    	}
    }
    
    /**
     * Initializes the fxml elements to make sure they can be injected and work
     */
    @FXML
    void initialize() {
        assert this.foodName != null : "fx:id=\"foodName\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.foodType != null : "fx:id=\"foodType\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.pantryList != null : "fx:id=\"pantryList\" was not injected: check your FXML file 'MainWindow.fxml'.";
        this.foodType.getItems().addAll("Vegetable", "Meat", "Bread", "Fruit", "Dessert", "Ingredient");
    }
}
