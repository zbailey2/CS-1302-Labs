package edu.westga.cs1302.cms.test.model.student;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.cms.model.Student;

class TestConstructor {

	@Test
	void testNullName() {
		assertThrows(IllegalArgumentException.class, ()->{new Student(null, 80);});
	}

	@Test
	void testNameTooShort() {
		assertThrows(IllegalArgumentException.class, ()->{new Student("12", 89);});
	}
	
	@Test
	void testMinimumLengthName() {
		Student result = new Student("123", 88);
		
		assertEquals("123", result.getName(), "checking the name of the student");
	}
	
	@Test
	void testGradeTooLow() {
		assertThrows(IllegalArgumentException.class, ()-> {new Student("John", -1);});
	}
	
	@Test
	void testGradeTooHigh() {
		assertThrows(IllegalArgumentException.class,()-> {new Student("John", 101);});
	}
}
