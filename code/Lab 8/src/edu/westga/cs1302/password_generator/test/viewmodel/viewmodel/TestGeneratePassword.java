package edu.westga.cs1302.password_generator.test.viewmodel.viewmodel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.password_generator.viewmodel.ViewModel;

class TestGeneratePassword {

	@Test
	void testMinimumLengthNotANumber() {
		ViewModel vm = new ViewModel();
		vm.getMinimumLength().setValue("apple");
		
		vm.generatePassword();
		
		assertEquals("Invalid Minimum Length: must be a positive integer, but was apple", vm.getErrorText().getValue(), "checking the error text property");
		assertEquals(0, vm.getPasswords().getSize());
	}
	
	@Test
	void testMinimumLengthNotAValidNumber() {
		ViewModel vm = new ViewModel();
		vm.getMinimumLength().setValue("-2");
		
		vm.generatePassword();
		
		assertEquals("Invalid Minimum Length: minimum length must be at least 1", vm.getErrorText().getValue(), "checking the error text property");
		assertEquals(0, vm.getPasswords().getSize());
	}
	
	@Test
	void testValidInputProvided() {
		ViewModel vm = new ViewModel();
		vm.getMinimumLength().setValue("2");
		
		vm.generatePassword();
		
		assertTrue(vm.getPasswords().get(0).length() >= 2, "checking the password property has an appropriate number of characters");
		assertEquals("", vm.getErrorText().getValue(), "checking the error text property");
		assertEquals(1, vm.getPasswords().getSize());
	}
	
	@Test
	void testWithValidPasswords() {
		ViewModel vm = new ViewModel();
		vm.getMinimumLength().setValue("2");
		vm.generatePassword();
		vm.getMinimumLength().setValue("4");
		vm.generatePassword();
		
		assertTrue(vm.getPasswords().get(0).length() >= 2, "checking the password property has an appropriate number of characters");
		assertTrue(vm.getPasswords().get(1).length() >= 4, "checking the password property has an appropriate number of characters");
		assertEquals("", vm.getErrorText().getValue());
		assertEquals(2, vm.getPasswords().getSize());
	}
	
	@Test
	void testWithOneValidInputOneInvalidInput() {
		ViewModel vm = new ViewModel();
		vm.getMinimumLength().setValue("2");
		vm.generatePassword();
		vm.getMinimumLength().setValue("-4");
		vm.generatePassword();
		
		assertTrue(vm.getPasswords().get(0).length() >= 2, "checking the password property has an appropriate number of characters");
		assertEquals("Invalid Minimum Length: minimum length must be at least 1", vm.getErrorText().getValue());
		assertEquals(1, vm.getPasswords().getSize());
	}
}
