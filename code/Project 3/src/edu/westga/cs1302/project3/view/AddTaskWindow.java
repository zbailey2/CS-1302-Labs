package edu.westga.cs1302.project3.view;

import edu.westga.cs1302.project3.viewmodel.ViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
	@FXML private Button addTaskButton;
	@FXML private TextArea taskDescription;
	@FXML private TextField taskTitle;
	
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
		
		vm.getTitle().bind(this.taskTitle.textProperty());
		vm.getDescription().bind(this.taskDescription.textProperty());
		
		this.addTaskButton.setOnAction((event) -> {
			try {
				vm.addTask();
				vm.getTasks();
			} catch (IllegalArgumentException missingValue) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Invalid task");
				alert.setContentText(missingValue.getMessage());
				alert.showAndWait();
			}
			Stage stage = (Stage) (this.addTaskGUI).getScene().getWindow();
			stage.close();
		});
	}
}
