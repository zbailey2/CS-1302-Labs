package edu.westga.cs1302.project3.view;

import java.io.File;
import java.io.IOException;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.viewmodel.ViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/** Codebehind for the Main Window of the application.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class MainWindow {
	@FXML private ListView<Task> taskListView;
	@FXML private MenuItem menuItemLoad;
	
	private ViewModel vm;

    @FXML
    void initialize() {
    	this.vm = new ViewModel();
    	this.taskListView.itemsProperty().bind(this.vm.getTasks());
    	
    	//Menu Item
    	this.menuItemLoad.setOnAction((event) -> {
    		FileChooser fileChooser = new FileChooser();
        	fileChooser.setTitle("Open Task File");
        	fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
 
        	File file = fileChooser.showOpenDialog(null);
        	if (file != null) {
        		try {
        			this.vm.loadTasks(file.getAbsolutePath());
        		} catch (IOException ioException) {
        			Alert alert = new Alert(AlertType.ERROR);
        			alert.setTitle("Error loading file");
        			alert.setContentText("Error loading task " + ioException.getMessage());
        			alert.showAndWait();
        		} catch (IllegalArgumentException illegalFile) {
        			Alert alert = new Alert(AlertType.ERROR);
        			alert.setTitle("Not valid file type");
        			alert.setContentText("Error loading task " + illegalFile.getMessage());
        			alert.showAndWait();
        		}
        	}
    	});
    }
}
