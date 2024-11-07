package edu.westga.cs1302.password_generator.view;

import edu.westga.cs1302.password_generator.viewmodel.PasswordGeneratorViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/** Codebehind for the MainWindow of the Application.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class MainWindow {

    @FXML private CheckBox mustIncludeDigits;
    @FXML private CheckBox mustIncludeLowerCaseLetters;
    @FXML private CheckBox mustIncludeUpperCaseLetters;
    @FXML private TextField minimumLength;
    @FXML private TextArea output;
    @FXML private Label errorMessage;
    @FXML private Button generator;
    private PasswordGeneratorViewModel viewModel;
    
    /**
     * Instantiates a new PaswordGeneratorViewModel
     * 
     * @precondition none
     * @postcondition none
     */
    public MainWindow() {
    	this.viewModel = new PasswordGeneratorViewModel();
    }
    
    @FXML
    void generatePassword() {
    	this.viewModel.generatePassword();
    }
    
    /**
     * binds all the components necessary
     */
    private void bindComponentsToViewModel() {
    	this.mustIncludeDigits.selectedProperty().bindBidirectional(this.viewModel.mustHaveAtLeastOneDigit());
    	this.mustIncludeLowerCaseLetters.selectedProperty().bindBidirectional(this.viewModel.mustHaveAtLeastOneLowerCaseLetter());
    	this.mustIncludeUpperCaseLetters.selectedProperty().bindBidirectional(this.viewModel.mustHaveAtLeastOneUpperCaseLetter());
    	this.minimumLength.textProperty().bindBidirectional(this.viewModel.minimumLength());
    	this.output.textProperty().bind(this.viewModel.generatedPassword());
    	this.errorMessage.textProperty().bind(this.viewModel.errorMessage());
    }

    @FXML
    void initialize() {
        this.bindComponentsToViewModel();
        this.minimumLength.setText("1");
    }
}
