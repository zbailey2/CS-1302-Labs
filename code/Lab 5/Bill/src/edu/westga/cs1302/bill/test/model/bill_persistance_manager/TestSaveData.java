package edu.westga.cs1302.bill.test.model.bill_persistance_manager;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.bill.model.Bill;
import edu.westga.cs1302.bill.model.BillItem;
import edu.westga.cs1302.bill.model.BillPersistenceManager;

public class TestSaveData {
	
	@Test
	void testNullBill() {
		assertThrows(IllegalArgumentException.class, ()->{
			BillPersistenceManager.saveBillData(null);
		});
	}
	
	@Test
	void testNoItemsInBill() throws IOException {
		Bill bill = new Bill();
		BillPersistenceManager.saveBillData(bill);
		
		File inputFile = new File(BillPersistenceManager.DATA_FILE);
		try(Scanner reader = new Scanner(inputFile)){
			assertEquals("Server: No Server Set", reader.nextLine());
			assertEquals("Subtotal: 0.0", reader.nextLine());
			assertEquals("Tax: 0.0", reader.nextLine());
			assertEquals("Tip: 0.0", reader.nextLine());
			assertEquals("Total: 0.0", reader.nextLine());
			assertFalse(reader.hasNextLine(), "Making sure no other lines exist");
		}
	}
	
	@Test
	void testWithOneItem() throws IOException {
		Bill bill = new Bill();
		BillItem item = new BillItem("Chicken", 10.00);
		bill.addItem(item);
		
		BillPersistenceManager.saveBillData(bill);
		
		File inputFile = new File(BillPersistenceManager.DATA_FILE);
		try(Scanner reader = new Scanner(inputFile)){
			assertEquals("Server: No Server Set", reader.nextLine());
			assertEquals("Chicken - 10.0", reader.nextLine());
			assertEquals("Subtotal: 10.0", reader.nextLine());
			assertEquals("Tax: 1.0", reader.nextLine());
			assertEquals("Tip: 2.0", reader.nextLine());
			assertEquals("Total: 13.0", reader.nextLine());
			assertFalse(reader.hasNextLine(), "Making sure no other lines exist");
		}
	}
	
	@Test
	void testWithMultipleItems() throws IOException{
		Bill bill = new Bill();
		BillItem item1 = new BillItem("Chicken", 10.00);
		bill.addItem(item1);
		BillItem item2 = new BillItem("Rice", 5.00);
		bill.addItem(item2);
		BillItem item3 = new BillItem("Salt", 3.00);
		bill.addItem(item3);
		
		BillPersistenceManager.saveBillData(bill);
		
		File inputFile = new File(BillPersistenceManager.DATA_FILE);
		try(Scanner reader = new Scanner(inputFile)){
			assertEquals("Server: No Server Set", reader.nextLine());
			assertEquals("Chicken - 10.0", reader.nextLine());
			assertEquals("Rice - 5.0", reader.nextLine());
			assertEquals("Salt - 3.0", reader.nextLine());
			assertEquals("Subtotal: 18.0", reader.nextLine());
			assertEquals("Tax: 1.8", reader.nextLine());
			assertEquals("Tip: 3.6", reader.nextLine());
			assertEquals("Total: 23.400000000000002", reader.nextLine());
			assertFalse(reader.hasNextLine(), "Making sure no other lines exist");
		}
	}
	
	@Test
	void testOverWritingBill() throws IOException {
		Bill bill = new Bill();
		BillItem item = new BillItem("Chicken", 10.00);
		bill.addItem(item);
		
		BillPersistenceManager.saveBillData(bill);
		
		Bill bill2 = new Bill();
		BillItem item2 = new BillItem("Rice", 5.00);
		bill2.addItem(item2);
		
		BillPersistenceManager.saveBillData(bill2);
		
		File inputFile = new File(BillPersistenceManager.DATA_FILE);
		try(Scanner reader = new Scanner(inputFile)){
			assertEquals("Server: No Server Set", reader.nextLine());
			assertEquals("Rice - 5.0", reader.nextLine());
			assertEquals("Subtotal: 5.0", reader.nextLine());
			assertEquals("Tax: 0.5", reader.nextLine());
			assertEquals("Tip: 1.0", reader.nextLine());
			assertEquals("Total: 6.5", reader.nextLine());
			assertFalse(reader.hasNextLine(), "Making sure no other lines exist");
		}
	}
}
