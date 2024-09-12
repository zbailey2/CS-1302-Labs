package edu.westga.cs1302.cms.view;

import java.util.ArrayList;

import edu.westga.cs1302.cms.model.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * Code behind for the MainWindow of the application
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class MainWindow {
	@FXML
	private TextField name;
	@FXML
	private ListView<Student> students;
	@FXML
	private TextField grade;
	@FXML
	private Label studentGrade;
	@FXML
	private Label classAverage;

	@FXML
	void addStudent(ActionEvent event) {
		String studentName = this.name.getText();

		try {
			double studentGrade = Double.parseDouble(this.grade.getText());
			Student student = new Student(studentName, studentGrade);
			this.students.getItems().add(student);
		} catch (NumberFormatException errorGrade) {
			Alert errorPopup = new Alert(Alert.AlertType.ERROR);
			errorPopup.setContentText(
					"Unable to create student: " + errorGrade.getMessage() + " Please reenter grade and try again.");
			errorPopup.showAndWait();
		} catch (IllegalArgumentException errorName) {
			Alert errorPopup = new Alert(Alert.AlertType.ERROR);
			errorPopup.setContentText("Unable to create student: " + errorName.getMessage()
					+ " Please reenter name or grade and then try again.");
			errorPopup.showAndWait();
		}
	}

	@FXML
	void removeStudent(ActionEvent event) {
		Student student = this.students.getSelectionModel().getSelectedItem();
		if (student != null) {
			this.students.getItems().remove(student);
		} else {
			Alert errorPopup = new Alert(Alert.AlertType.ERROR);
			errorPopup.setContentText("No student selected. Unable to remove.");
			errorPopup.showAndWait();
		}
	}

	@FXML
	void viewGrade() {
		Student student = this.students.getSelectionModel().getSelectedItem();
		if (student != null) {
			double currentGrade = student.getGrade();
			this.studentGrade.setText("Grade: " + Double.toString(currentGrade));
		} else {
			Alert errorPopup = new Alert(Alert.AlertType.ERROR);
			errorPopup.setContentText("No student selected. Unable to check grade.");
			errorPopup.showAndWait();
		}
	}

	@FXML
	void clearGrade() {
		this.studentGrade.setText("");
	}

	@FXML
	void getAverageOfClass() {
		//Must initialize ArrayList in order to test getAverage in Student class
		//Can not test a ListView in JUnit without an appropriate JavaFX environment
		//I do not know how to do that yet
		ArrayList<Student> allStudents = new ArrayList<Student>();
		allStudents.addAll(this.students.getItems());
		if (this.students.getItems().size() == 0) {
			this.classAverage.setText("");
		} else {
			double average = Student.getAverage(allStudents);
			this.classAverage.setText("Class Average " + System.lineSeparator() + Double.toString(average));
		}

	}

	@FXML
	void initialize() {
		assert this.classAverage != null
				: "fx:id=\"classAverage\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.grade != null : "fx:id=\"grade\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.name != null : "fx:id=\"name\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.studentGrade != null
				: "fx:id=\"studentGrade\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.students != null : "fx:id=\"students\" was not injected: check your FXML file 'MainWindow.fxml'.";
	}

}
