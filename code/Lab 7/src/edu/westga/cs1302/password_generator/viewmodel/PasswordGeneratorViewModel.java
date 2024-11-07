package edu.westga.cs1302.password_generator.viewmodel;

import java.util.Random;

import edu.westga.cs1302.password_generator.model.PasswordGenerator;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * The Class ViewModel.
 * 
 * @author Zachary Bailey
 * @version Fall 2024
 */
public class PasswordGeneratorViewModel {
	
	private StringProperty minimumLength;
	private BooleanProperty mustHaveAtLeastOneDigit;
	private BooleanProperty mustHaveAtLeastOneUpperCaseLetter;
	private BooleanProperty mustHaveAtLeastOneLowerCaseLetter;
	private StringProperty generatedPassword;
	private StringProperty errorMessage;
	/**
	 * Instantiates a new PasswordGenerator view model.
	 */
	
	public PasswordGeneratorViewModel() {
		this.minimumLength = new SimpleStringProperty("1");
		this.mustHaveAtLeastOneDigit = new SimpleBooleanProperty(false);
		this.mustHaveAtLeastOneUpperCaseLetter = new SimpleBooleanProperty(false);
		this.mustHaveAtLeastOneLowerCaseLetter = new SimpleBooleanProperty(false);
		this.generatedPassword = new SimpleStringProperty("");
		this.errorMessage = new SimpleStringProperty("No error present");
	}
	
	/**
	 * Returns the minimum length integer property
	 * @return IntegerProperty minimumLength
	 */
	public StringProperty minimumLength() {
		return this.minimumLength;
	}
	
	/**
	 * Returns the OneDigit boolean property
	 * @return BooleanProperty mustHaveAtLeastOneDigit
	 */
	public BooleanProperty mustHaveAtLeastOneDigit() {
		return this.mustHaveAtLeastOneDigit;
	}
	
	/**
	 * Returns the OneUpperCase booleanProperty
	 * @return BooleanProperty mustHaveAtLeastOneUpperCaseLetter
	 */
	public BooleanProperty mustHaveAtLeastOneUpperCaseLetter() {
		return this.mustHaveAtLeastOneUpperCaseLetter;
	}
	
	/**
	 * Returns the OneLowerCase booleanProperty
	 * @return BooleanProperty mustHaveAtLeastOneLowerCaseLetter
	 */
	public BooleanProperty mustHaveAtLeastOneLowerCaseLetter() {
		return this.mustHaveAtLeastOneLowerCaseLetter;
	}
	
	/**
	 * Returns the errorMessage StringProperty
	 * @return StringProperty for errorMessage
	 */
	public StringProperty errorMessage() {
		return this.errorMessage;
	}
	
	/**
	 * Returns the generatedPassword String Property
	 * @return StringProperty for generatedPassword
	 */
	public StringProperty generatedPassword() {
		return this.generatedPassword;
	}
	
	/**
	 * Returns the string of the generated password
	 */
	public void generatePassword() {
		int minimumLength = -1;
		
		try {
			minimumLength = Integer.parseInt(this.minimumLength.getValue());
		} catch (NumberFormatException noNegatives) {
			this.errorMessage.setValue("Length must be a positive integer");
			return;
		}
		Random randomNumberGenerator = new Random();
		try {
			PasswordGenerator generator = new PasswordGenerator(randomNumberGenerator.nextLong());
			generator.setMinimumLength(minimumLength);
			generator.setMustHaveAtLeastOneDigit(this.mustHaveAtLeastOneDigit.getValue());
			generator.setMustHaveAtLeastOneLowerCaseLetter(this.mustHaveAtLeastOneLowerCaseLetter.getValue());
			generator.setMustHaveAtLeastOneUpperCaseLetter(this.mustHaveAtLeastOneUpperCaseLetter.getValue());
				
			String password = generator.generatePassword();
			this.generatedPassword.setValue(password);
		} catch (IllegalArgumentException tooLow) {
			this.errorMessage.setValue("Length can not be below 1");
			return;
		}
	}
}
