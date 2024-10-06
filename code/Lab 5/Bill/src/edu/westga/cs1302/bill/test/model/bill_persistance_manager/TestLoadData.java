package edu.westga.cs1302.bill.test.model.bill_persistance_manager;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.bill.model.Bill;
import edu.westga.cs1302.bill.model.BillPersistenceManager;

public class TestLoadData {
	
	@Test
	void testEmptyFile() throws IOException {
		try(FileWriter writer = new FileWriter(BillPersistenceManager.DATA_FILE)){
			writer.write("");
		}
		
		Bill bill = BillPersistenceManager.loadBillData();
		assertEquals(0, bill.getItems().length);
		assertEquals("No Server Set",bill.getServerName());
	}
	
	@Test
	void testServerNoItems() throws IOException {
		try(FileWriter writer = new FileWriter(BillPersistenceManager.DATA_FILE)){
			writer.write("Server: Bob" + System.lineSeparator());
		}
		
		Bill bill = BillPersistenceManager.loadBillData();
		
		assertEquals(0, bill.getItems().length);
		assertEquals("Bob", bill.getServerName());
	}
	
	@Test
	void testNoServerOneItem() throws IOException {
		try(FileWriter writer = new FileWriter(BillPersistenceManager.DATA_FILE)){
			writer.write("Server: No Server Set" + System.lineSeparator());
			writer.write("Tomato - 10.0");
		}
		
		Bill bill = BillPersistenceManager.loadBillData();
		
		assertEquals(1, bill.getItems().length);
		assertEquals("No Server Set", bill.getServerName());
		assertEquals("Tomato", bill.getItems()[0].getName());
		assertEquals(10.0, bill.getItems()[0].getAmount(), 0.0000001);
	}
	
	@Test
	void testServerandItems() throws IOException {
		try(FileWriter writer = new FileWriter(BillPersistenceManager.DATA_FILE)){
			writer.write("Server: Bob" + System.lineSeparator());
			writer.write("Taco - 1.0" + System.lineSeparator());
			writer.write("Broccoli - 6.0" + System.lineSeparator());
			writer.write("Chicken - 8.0" + System.lineSeparator());
		}
		
		Bill bill = BillPersistenceManager.loadBillData();
		
		assertEquals(3, bill.getItems().length);
		assertEquals("Bob", bill.getServerName());
		assertEquals("Taco", bill.getItems()[0].getName());
		assertEquals(1.0, bill.getItems()[0].getAmount(), 0.000001);
		assertEquals("Broccoli", bill.getItems()[1].getName());
		assertEquals(6.0, bill.getItems()[1].getAmount(), 0.0000001);
		assertEquals("Chicken", bill.getItems()[2].getName());
		assertEquals(8.0, bill.getItems()[2].getAmount(), 0.000000001);
	}
	
	@Test
	void testServerWithInvalidName() throws IOException{
		try(FileWriter writer = new FileWriter(BillPersistenceManager.DATA_FILE)){
			writer.write("Invalid name" + System.lineSeparator());
		}
		
		assertThrows(IOException.class, ()->{
			BillPersistenceManager.loadBillData();
		});
	}
	
	@Test
	void testInvalidFoodItemLayout() throws IOException{
		try(FileWriter writer = new FileWriter(BillPersistenceManager.DATA_FILE)){
			writer.write("Server: Bob" + System.lineSeparator());
			writer.write("Tomato, 1.0" + System.lineSeparator());
		}
		
		assertThrows(IOException.class, ()->{
			BillPersistenceManager.loadBillData();
		});
	}
	
	@Test
	void testIncompleteFoodItemInFile() throws IOException{
		try(FileWriter writer = new FileWriter(BillPersistenceManager.DATA_FILE)){
			writer.write("Server: Bob" + System.lineSeparator());
			writer.write("Tomato" + System.lineSeparator());
		}
		assertThrows(IOException.class, ()->{
			BillPersistenceManager.loadBillData();
		});
	}
}
