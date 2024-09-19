package edu.westga.cs1302.bill.test.model.bill_calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.bill.model.Bill;
import edu.westga.cs1302.bill.model.BillCalculator;
import edu.westga.cs1302.bill.model.BillItem;

class TestGetTotal {

	@Test
	void testTotalWithEmptyBill() {
		Bill bill = new Bill();
	    
	    double result = BillCalculator.calculateTotal(bill.getItems());
	    double expected = 0.0;
	    
	    assertEquals(expected, result);
	    
	}
	
	@Test
	void testTotalWithOneItem() {
		Bill bill = new Bill();
		BillItem billItemOne = new BillItem("Bread", 5.00);
				
		bill.addItem(billItemOne);
				
		double result = BillCalculator.calculateTotal(bill.getItems());
		double expected = 6.50;
		assertEquals(expected, result);
		assertEquals("Bread", billItemOne.getName(), "Checks the name of the item");
		assertEquals(5.00, billItemOne.getAmount(), "Checks the amount of the item");
	}
	
	@Test
	//Tests array with two items
	void testTotalWithTwoItems() {
		Bill bill = new Bill();
		BillItem billItemOne = new BillItem("Cheese", 5.50);
		BillItem billItemTwo = new BillItem("Cereal", 1.50);
		
		bill.addItem(billItemOne);
		bill.addItem(billItemTwo);
		
		double result = BillCalculator.calculateTotal(bill.getItems());
		double expected = 9.1;
		assertEquals(expected, result);
		assertEquals("Cheese", billItemOne.getName(), "Checks the name of the item");
		assertEquals(5.50, billItemOne.getAmount(), "Checks the amount of the item");
		assertEquals("Cereal", billItemTwo.getName(), "Checks the name of the item");
		assertEquals(1.50, billItemTwo.getAmount(), "Checks the amount of the item");
	}
	
	@Test
	//Tests full array
	void testTotalWithFullItems() {
		Bill bill = new Bill();
		BillItem billItemOne = new BillItem("Cheese", 5.50);
		BillItem billItemTwo = new BillItem("Meat", 10.50);
		BillItem billItemThree = new BillItem("Bread", 7.38);
		
		bill.addItem(billItemOne);
		bill.addItem(billItemTwo);
		bill.addItem(billItemThree);
		
		double result = BillCalculator.calculateTotal(bill.getItems());
		double expected = 30.4;
		assertEquals(expected, result);
		assertEquals("Cheese", billItemOne.getName(), "Checks the name of the item");
		assertEquals(5.50, billItemOne.getAmount(), "Checks the amount of the item");
		assertEquals("Meat", billItemTwo.getName(), "Checks the name of the item");
		assertEquals(10.50, billItemTwo.getAmount(), "Checks the amount of the item");
		assertEquals("Bread", billItemThree.getName(), "Checks the name of the item");
		assertEquals(7.38, billItemThree.getAmount(), "Checks the amount of the item");
	}
	
	@Test
	//Tests to make sure method ignores null items in array
	void testTotalWithNullItems() {
		Bill bill = new Bill();
		BillItem billItemOne = new BillItem("Bread", 5.00);
		
		bill.addItem(billItemOne);
		
		double result = BillCalculator.calculateTotal(bill.getItems());
		double expected = 6.5;
		assertEquals(expected, result);
		assertEquals("Bread", billItemOne.getName(), "Checks the name of the item");
		assertEquals(5.00, billItemOne.getAmount(), "Checks the amount of the item");
	}
	
	@Test
	//Tests with a large number for prices
	void testTotalWithLargePricedItems() {
		Bill bill = new Bill();
		BillItem billItemOne = new BillItem("Cheese", 100.31);
		BillItem billItemTwo = new BillItem("Meat", 250.52);
		BillItem billItemThree = new BillItem("Bread", 320.54);
		
		bill.addItem(billItemOne);
		bill.addItem(billItemTwo);
		bill.addItem(billItemThree);
		
		double result = BillCalculator.calculateTotal(bill.getItems());
		double expected = 872.78;
		assertEquals(expected, result);
		assertEquals("Cheese", billItemOne.getName(), "Checks the name of the item");
		assertEquals(100.31, billItemOne.getAmount(), "Checks the amount of the item");
		assertEquals("Meat", billItemTwo.getName(), "Checks the name of the item");
		assertEquals(250.52, billItemTwo.getAmount(), "Checks the amount of the item");
		assertEquals("Bread", billItemThree.getName(), "Checks the name of the item");
		assertEquals(320.54, billItemThree.getAmount(), "Checks the amount of the item");
	}
	
	@Test
	//Test with identical items
	void testTotalWithSameItems() {
		Bill bill = new Bill();
		BillItem billItemOne = new BillItem("Bread", 320.54);
		BillItem billItemTwo = new BillItem("Bread", 320.54);
		BillItem billItemThree = new BillItem("Bread", 320.54);
		
		bill.addItem(billItemOne);
		bill.addItem(billItemTwo);
		bill.addItem(billItemThree);
		
		double result = BillCalculator.calculateTotal(bill.getItems());
		double expected = 1250.1;
		assertEquals(expected, result);
		assertEquals("Bread", billItemOne.getName(), "Checks the name of the item");
		assertEquals(320.54, billItemOne.getAmount(), "Checks the amount of the item");
		assertEquals("Bread", billItemTwo.getName(), "Checks the name of the item");
		assertEquals(320.54, billItemTwo.getAmount(), "Checks the amount of the item");
		assertEquals("Bread", billItemThree.getName(), "Checks the name of the item");
		assertEquals(320.54, billItemThree.getAmount(), "Checks the amount of the item");
	}
	
	@Test
	//Tests with unordered items to ensure it doesn't change value
	void testTotalWithUnorderedArray() {
		Bill bill = new Bill();
		BillItem billItemTwo = new BillItem("Bread", 320.54);
		BillItem billItemOne = new BillItem("Bread", 320.54);
		BillItem billItemThree = new BillItem("Bread", 320.54);
		
		bill.addItem(billItemTwo);
		bill.addItem(billItemOne);
		bill.addItem(billItemThree);
		
		double result = BillCalculator.calculateTotal(bill.getItems());
		double expected = 1250.10;
		assertEquals(expected, result);
		assertEquals("Bread", billItemOne.getName(), "Checks the name of the item");
		assertEquals(320.54, billItemOne.getAmount(), "Checks the amount of the item");
		assertEquals("Bread", billItemTwo.getName(), "Checks the name of the item");
		assertEquals(320.54, billItemTwo.getAmount(), "Checks the amount of the item");
		assertEquals("Bread", billItemThree.getName(), "Checks the name of the item");
		assertEquals(320.54, billItemThree.getAmount(), "Checks the amount of the item");
	}

}
