package edu.westga.cs1302.project1.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    private ComboBox<?> foodType;

    @FXML
    private ListView<?> pantryList;
	
    /**
     * Will add food to pantry later when implemented
     * @param event the event that happens when button is pushed
     */
    @FXML
    public void addFoodToPantry(ActionEvent event) {
    	//To-Be Continued
    }
    
    /**
     * Initializes the fxml elements to make sure they can be injected and work
     */
    @FXML
    void initialize() {
        assert this.foodName != null : "fx:id=\"foodName\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.foodType != null : "fx:id=\"foodType\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.pantryList != null : "fx:id=\"pantryList\" was not injected: check your FXML file 'MainWindow.fxml'.";

    }
}
