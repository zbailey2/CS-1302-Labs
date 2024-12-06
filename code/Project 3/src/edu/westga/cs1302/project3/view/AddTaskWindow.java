package edu.westga.cs1302.project3.view;

import edu.westga.cs1302.project3.viewmodel.ViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/** Codebehind for the Add Task Window of the application.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class AddTaskWindow {
	
	@FXML private Button cancelButton;
	@FXML private AnchorPane addTaskGUI;
	
	/**
	 * Binds the ViewModel to the codeBehind for the AddTaskWindow
	 * @param vm the ViewModel object
	 */
	public void bindToVM(ViewModel vm) {
		//Binds the cancel button on the Add Task window to VM
		this.cancelButton.setOnAction((event) -> {
			Stage stage = (Stage) (this.addTaskGUI).getScene().getWindow();
			stage.close();
		});
	}
}
