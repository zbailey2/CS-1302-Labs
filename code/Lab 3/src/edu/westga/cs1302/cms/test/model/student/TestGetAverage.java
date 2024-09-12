package edu.westga.cs1302.cms.test.model.student;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.cms.model.Student;

public class TestGetAverage {
	
	

	@Test
	public void testAverageWithNoStudents() {
		ArrayList<Student> students = new ArrayList<Student>();
		double average = Student.getAverage(students);
		assertEquals(0.0, average, "Average should be 0.0 no students in list");
	}

	@Test
	public void testAverageOfOneStudent() {
		ArrayList<Student> students = new ArrayList<Student>();
		Student student = new Student("John", 90.0);
		
		students.add(student);
		
		double average = Student.getAverage(students);
		assertEquals(90.0, average, "Average should be 0.0 no students in list");
		assertEquals("John", student.getName(), "Name should not change");
		assertEquals(90.0, student.getGrade(), "Grade should not change");
		assertEquals(1, students.size(), "ListView should only have one item");
	}

	@Test
	public void testAverageWithMultipleStudents() {
		ArrayList<Student> students = new ArrayList<Student>();
		Student student = new Student("John", 90.0);
		Student student2 = new Student("Ryan", 70.0);
		Student student3 = new Student("Michael", 85.0);
		
		students.add(student);
		students.add(student2);
		students.add(student3);
		
		double average = Student.getAverage(students);
		assertEquals(81.67, average, "Average should be 0.0 no students in list");
		assertEquals("John", student.getName(), "Name should not change");
		assertEquals("Ryan", student2.getName(), "Name should not change");
		assertEquals("Michael", student3.getName(), "Name should not change");
		assertEquals(90.0, student.getGrade(), "Grade should not change");
		assertEquals(70.0, student2.getGrade(), "Grade should not change");
		assertEquals(85.0, student3.getGrade(), "Grade should not change");
		assertEquals(3, students.size(), "ListView should only have one item");
	}


	@Test
	public void testAverageWithUpperEdgeGrades() {
		ArrayList<Student> students = new ArrayList<Student>();
		Student student = new Student("John", 99.99999);
		Student student2 = new Student("Ryan", 99.99);
		Student student3 = new Student("Michael", 100.0000000000000001);
		
		students.add(student);
		students.add(student2);
		students.add(student3);
		
		double average = Student.getAverage(students);
		assertEquals(100, average, "Average should be 0.0 no students in list");
		assertEquals("John", student.getName(), "Name should not change");
		assertEquals("Ryan", student2.getName(), "Name should not change");
		assertEquals("Michael", student3.getName(), "Name should not change");
		assertEquals(100.0, student.getGrade(), "Grade should not change");
		assertEquals(99.99, student2.getGrade(), "Grade should not change");
		assertEquals(100.0, student3.getGrade(), "Grade should not change");
		assertEquals(3, students.size(), "ListView should only have one item");
	}

	@Test
	public void testAverageWithLowerEdgeGrades() {
		ArrayList<Student> students = new ArrayList<Student>();
		Student student = new Student("John", 0.001);
		Student student2 = new Student("Ryan", 0.1);
		Student student3 = new Student("Michael", 0.01);
		
		students.add(student);
		students.add(student2);
		students.add(student3);
		
		double average = Student.getAverage(students);
		assertEquals(0.04, average, "Average should be 0.0 no students in list");
		assertEquals("John", student.getName(), "Name should not change");
		assertEquals("Ryan", student2.getName(), "Name should not change");
		assertEquals("Michael", student3.getName(), "Name should not change");
		assertEquals(0.0, student.getGrade(), "Grade should not change");
		assertEquals(0.1, student2.getGrade(), "Grade should not change");
		assertEquals(0.01, student3.getGrade(), "Grade should not change");
		assertEquals(3, students.size(), "ListView should only have one item");
	}
}
