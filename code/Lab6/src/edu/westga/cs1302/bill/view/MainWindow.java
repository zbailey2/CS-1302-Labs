package edu.westga.cs1302.bill.view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Comparator;

import edu.westga.cs1302.bill.model.Bill;
import edu.westga.cs1302.bill.model.BillItem;
import edu.westga.cs1302.bill.model.BillPersistenceManager;
import edu.westga.cs1302.bill.model.CSVBillPersistenceManager;
import edu.westga.cs1302.bill.model.TSVBillPersistenceManager;
import edu.westga.cs1302.bill.model.BillAscendingCostComparator;
import edu.westga.cs1302.bill.model.BillDescendingCostComparator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * The codebehind for the MainWindow of the application
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class MainWindow {
	private Bill bill;

	@FXML
	private TextField name;
	@FXML
	private TextField amount;
	@FXML
	private TextArea receiptArea;
	@FXML
	private ComboBox<String> serverName;
	@FXML
	private ComboBox<BillPersistenceManager> format;
	@FXML
    private ComboBox<Comparator<BillItem>> order;

	@FXML
	void addItem(ActionEvent event) {
		try {
			BillItem item = new BillItem(this.name.getText(), Double.parseDouble(this.amount.getText()));
			this.bill.addItem(item);
			this.name.clear();
			this.amount.clear();
			this.changeOrder(event);
			this.updateReceipt();
		} catch (NumberFormatException numError) {
			this.displayErrorPopup("Invalid amount provided, please correct and try again.");
		} catch (IllegalArgumentException argError) {
			this.displayErrorPopup("Unable to add new bill item");
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
		try {
			this.format.getValue().saveBillData(this.bill);
		} catch (IOException writeError) {
			this.displayErrorPopup("Unable to save data to file!");
		}
	}

	@FXML
	void changeFormat(ActionEvent event) throws IOException {
		this.saveBillData(event);
	}

	private void displayErrorPopup(String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setContentText(message);
		alert.showAndWait();
	}
	
	@FXML
	void changeOrder(ActionEvent event) {
		this.bill.sort(this.order.getValue());
		this.updateReceipt();
	}
	
	@FXML
	void initialize() {
		assert this.amount != null : "fx:id=\"amount\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.format != null : "fx:id=\"format\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.name != null : "fx:id=\"name\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.receiptArea != null
				: "fx:id=\"receiptArea\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.serverName != null
				: "fx:id=\"serverName\" was not injected: check your FXML file 'MainWindow.fxml'.";
		 assert this.order != null : "fx:id=\"order\" was not injected: check your FXML file 'MainWindow.fxml'.";

		// Formats the ComboBox For Server Names
		this.serverName.getItems().add("Bob");
		this.serverName.getItems().add("Alice");
		this.serverName.getItems().add("Trudy");

		// Formats the ComboBox For Saving Methods
		this.format.getItems().add(new CSVBillPersistenceManager());
		this.format.getItems().add(new TSVBillPersistenceManager());
		this.format.setValue(this.format.getItems().get(0));
		
		//Formats the ComboBox for Changing Order
		this.order.getItems().add(new BillAscendingCostComparator());
		this.order.getItems().add(new BillDescendingCostComparator());
		
		this.order.setValue(this.order.getItems().get(0));
		
		try {
			this.bill = this.format.getValue().loadBillData();
			this.updateReceipt();
		} catch (FileNotFoundException fileError) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setContentText("No save data file found, loading with no bill data");
			alert.showAndWait();
		} catch (IOException parseError) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText("File not in valid format, creating new bill");
			alert.setContentText(parseError.getMessage());
			alert.showAndWait();
		}
	}
}
