package edu.westga.cs1302.password_generator.test.viewmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.password_generator.viewmodel.PasswordGeneratorViewModel;

public class TestViewModel {
	
	@Test
	void testMinimumLength1NoOtherRestriction() {
		PasswordGeneratorViewModel viewModel = new PasswordGeneratorViewModel();
		viewModel.minimumLength().setValue("1");
		viewModel.mustHaveAtLeastOneUpperCaseLetter().setValue(false);
		viewModel.mustHaveAtLeastOneLowerCaseLetter().setValue(false);
		viewModel.mustHaveAtLeastOneDigit().setValue(false);
		
		viewModel.generatePassword();
		String result = viewModel.generatedPassword().getValue();
		assertTrue(1 <= result.length(), "checking length of generated password");
	}
	
	@Test
	void testMinimumLength3NoOtherRestriction() {
		PasswordGeneratorViewModel viewModel = new PasswordGeneratorViewModel();
		viewModel.minimumLength().setValue("3");
		viewModel.mustHaveAtLeastOneUpperCaseLetter().setValue(false);
		viewModel.mustHaveAtLeastOneLowerCaseLetter().setValue(false);
		viewModel.mustHaveAtLeastOneDigit().setValue(false);
		
		viewModel.generatePassword();
		String result = viewModel.generatedPassword().getValue();
		assertTrue(3 <= result.length(), "checking length of generated password");
	}
	
	@Test
	void testMinimumLength3AtLeastOneUpperCaseLetterNoOtherRestriction() {
		PasswordGeneratorViewModel viewModel = new PasswordGeneratorViewModel();
		viewModel.minimumLength().setValue("3");
		viewModel.mustHaveAtLeastOneUpperCaseLetter().setValue(true);
		viewModel.mustHaveAtLeastOneLowerCaseLetter().setValue(false);
		viewModel.mustHaveAtLeastOneDigit().setValue(false);
		
		viewModel.generatePassword();
		String result = viewModel.generatedPassword().getValue();
		assertTrue(3 <= result.length(), "checking length of generated password");
	}
	
	@Test
	void testMinimumLength3AtLeastOneDigitNoOtherRestriction() {
		PasswordGeneratorViewModel viewModel = new PasswordGeneratorViewModel();
		viewModel.minimumLength().setValue("3");
		viewModel.mustHaveAtLeastOneUpperCaseLetter().setValue(false);
		viewModel.mustHaveAtLeastOneLowerCaseLetter().setValue(false);
		viewModel.mustHaveAtLeastOneDigit().setValue(true);
		
		viewModel.generatePassword();
		String result = viewModel.generatedPassword().getValue();
		
		assertTrue(3 <= result.length(), "checking length of generated password");
	}
	
	@Test
	void testMinimumLength3AtLeastOneLowerCaseLetterNoOtherRestriction() {
		PasswordGeneratorViewModel viewModel = new PasswordGeneratorViewModel();
		viewModel.minimumLength().setValue("3");
		viewModel.mustHaveAtLeastOneUpperCaseLetter().setValue(false);
		viewModel.mustHaveAtLeastOneLowerCaseLetter().setValue(true);
		viewModel.mustHaveAtLeastOneDigit().setValue(false);
		
		viewModel.generatePassword();
		String result = viewModel.generatedPassword().getValue();
		
		assertTrue(3 <= result.length(), "checking length of generated password");
	}
	
	@Test
	void testMinimumLength3AtLeastOneLowerCaseLetterAtLeastOneUpperCaseLetterNoOtherRestriction() {
		PasswordGeneratorViewModel viewModel = new PasswordGeneratorViewModel();
		viewModel.minimumLength().setValue("3");
		viewModel.mustHaveAtLeastOneUpperCaseLetter().setValue(true);
		viewModel.mustHaveAtLeastOneLowerCaseLetter().setValue(true);
		viewModel.mustHaveAtLeastOneDigit().setValue(false);
		
		viewModel.generatePassword();
		String result = viewModel.generatedPassword().getValue();
		
		assertTrue(3 <= result.length(), "checking length of generated password");
	}
	
	@Test
	void testMinimumLength3AtLeastOneLowerCaseLetterAtLeastOneDigitNoOtherRestriction() {
		PasswordGeneratorViewModel viewModel = new PasswordGeneratorViewModel();
		viewModel.minimumLength().setValue("3");
		viewModel.mustHaveAtLeastOneUpperCaseLetter().setValue(false);
		viewModel.mustHaveAtLeastOneLowerCaseLetter().setValue(true);
		viewModel.mustHaveAtLeastOneDigit().setValue(true);
		
		viewModel.generatePassword();
		String result = viewModel.generatedPassword().getValue();
		
		assertTrue(3 <= result.length(), "checking length of generated password");
	}
	
	@Test
	void testMinimumLength3AtLeastOneUpperCaseLetterAtLeastOneDigitNoOtherRestriction() {
		PasswordGeneratorViewModel viewModel = new PasswordGeneratorViewModel();
		viewModel.minimumLength().setValue("3");
		viewModel.mustHaveAtLeastOneUpperCaseLetter().setValue(true);
		viewModel.mustHaveAtLeastOneLowerCaseLetter().setValue(false);
		viewModel.mustHaveAtLeastOneDigit().setValue(true);
		
		viewModel.generatePassword();
		String result = viewModel.generatedPassword().getValue();
		
		assertTrue(3 <= result.length(), "checking length of generated password");
	}
	@Test
	void testMinimumLength3AtLeastOneUpperCaseLetterAtLeastOneLowerCaseLetterAtLeastOneDigit() {
		PasswordGeneratorViewModel viewModel = new PasswordGeneratorViewModel();
		viewModel.minimumLength().setValue("3");
		viewModel.mustHaveAtLeastOneUpperCaseLetter().setValue(true);
		viewModel.mustHaveAtLeastOneLowerCaseLetter().setValue(true);
		viewModel.mustHaveAtLeastOneDigit().setValue(true);
		
		viewModel.generatePassword();
		String result = viewModel.generatedPassword().getValue();
		
		assertTrue(3 <= result.length(), "checking length of generated password");
	}
	
	@Test
	void testInvalidNameLength() {
		PasswordGeneratorViewModel viewModel = new PasswordGeneratorViewModel();
		viewModel.minimumLength().setValue("A");
		viewModel.generatePassword();
		assertEquals("Length must be a positive integer", viewModel.errorMessage().getValue());
	}
	
	@Test
	void testInvalidLength() {
		PasswordGeneratorViewModel viewModel = new PasswordGeneratorViewModel();
		viewModel.minimumLength().setValue("-1");
		viewModel.generatePassword();
		assertEquals("Length can not be below 1", viewModel.errorMessage().getValue());
	}
}	

