package edu.westga.cs1302.cms.model;

/** Stores and manages information for a single student.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class Student {
	private String name;
	private double grade;
	
	/** Create a new student with the specified name
	 * 
	 * @precondition name != null && name.length() >= 3
	 * @postcondition getName() == name
	 * 
	 * @param name the name of the new student
	 * @param grade the grade of the new student
	 * @throws IllegalArgumentException when precondition is violated
	 */
	public Student(String name, double grade) throws IllegalArgumentException {
		if (name == null) {
			throw new IllegalArgumentException("Name must be provided.");
		}
		if (name.length() < 3) {
			throw new IllegalArgumentException("Name must have at least 3 characters.");
		}
		if (grade < 0 || grade > 100) {
			throw new IllegalArgumentException("Grade must be from 0-100");
		}
		this.name = name;
		this.grade = Math.round(grade * 100.00) / 100.00;
	}
	
	/** Return the name of the student
	 * 
	 * @return the name of the student
	 */
	public String getName() {
		return this.name;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	/**
	 * Returns the grade of the student
	 * 
	 * @return the grade of the student
	 */
	public double getGrade() {
		return this.grade;
	}
	
}
