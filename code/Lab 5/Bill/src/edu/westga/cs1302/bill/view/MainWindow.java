package edu.westga.cs1302.bill.view;

import edu.westga.cs1302.bill.model.Bill;
import edu.westga.cs1302.bill.model.BillItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/** The codebehind for the MainWindow of the application
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class MainWindow {
	private Bill bill;
	
    @FXML private TextField name;
    @FXML private TextField amount;
    @FXML private TextArea receiptArea;
    @FXML private ComboBox<String> serverName;

    @FXML
    void addItem(ActionEvent event) {
    	try {
	    	BillItem item = new BillItem(this.name.getText(), Double.parseDouble(this.amount.getText()));
	    	this.bill.addItem(item);
	    	this.name.clear();
	    	this.amount.clear();
	    	this.updateReceipt();
    	} catch (NumberFormatException numError) {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setHeaderText("Unable to add new bill item");
    		alert.setContentText("Invalid amount provided, please correct and try again.");
    		alert.showAndWait();
    	} catch (IllegalArgumentException argError) {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setHeaderText("Unable to add new bill item");
    		alert.setContentText(argError.getMessage());
    		alert.showAndWait();
    	}
    }

	private void updateReceipt() {
		this.receiptArea.setText(BillTextifier.getText(this.bill));
	}
    
    @FXML
    void selectServer(ActionEvent event) {
    	String name = this.serverName.getValue();
    	if (name != null) {
    		this.bill.setServerName(name);
	    	this.updateReceipt();
    	}
    }

    @FXML
    void saveBillData(ActionEvent event) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setContentText("Operation not yet implemented!");
		alert.showAndWait();
    }

    @FXML
    void initialize() {
        this.serverName.getItems().add("Bob");
        this.serverName.getItems().add("Alice");
        this.serverName.getItems().add("Trudy");
        this.bill = new Bill();
    	this.updateReceipt();
    }
}

