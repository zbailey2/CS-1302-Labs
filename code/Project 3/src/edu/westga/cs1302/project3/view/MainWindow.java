package edu.westga.cs1302.project3.view;

import java.io.File;
import java.io.FileNotFoundException;
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
	@FXML private MenuItem menuItemSave;
	
	private ViewModel vm;

    @FXML
    void initialize() {
    	this.vm = new ViewModel();
    	this.taskListView.itemsProperty().bind(this.vm.getTasks());
    	
    	//Menu Item
    	this.menuItemLoad.setOnAction((event) -> {
    		this.helpLoadFile();
    	});
    	
    	//Menu Item save Task
    	this.menuItemSave.setOnAction((event) -> {
    		this.helpSaveFile();
    	});
    }
    
    /**
     * Helper function for loading fileChooser to initalize, helps reduce size of initialize() method
     * @throws IOException 
     */
    public void helpLoadFile() {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Open Task File");
    	fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt", "*.pdf", "*.docx"),
    		new ExtensionFilter("All Files", "*.*"));

    	File file = fileChooser.showOpenDialog(null);
    	if (file != null) {
    		try {
    			this.vm.loadTasks(file.getAbsolutePath());
    		} catch (FileNotFoundException noFile) {
    			Alert alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Error loading file");
    			alert.setContentText("Error loading task " + noFile.getMessage());
    			alert.showAndWait();
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
    }
    
    /**
     * Helper function for saving fileChooser to initalize, helps reduce size of initialize() method
     */
    public void helpSaveFile() {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Save Task File");
    	fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt", "*.pdf", "*.docx"),
    			new ExtensionFilter("All Files", "*.*"));

    	File file = fileChooser.showSaveDialog(null);
    	if (file != null) {
    		try {
    			this.vm.saveTasks(this.vm.getTaskManager(), file.getAbsolutePath());
    		} catch (IOException ioException) {
    			Alert alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Error loading file");
    			alert.setContentText("Error saving task " + ioException.getMessage());
    			alert.showAndWait();
    		} catch (IllegalArgumentException illegalFile) {
    			Alert alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Not valid file type");
    			alert.setContentText("Error saving task " + illegalFile.getMessage());
    			alert.showAndWait();
    		}
    	}
    }
}
