package edu.westga.cs1302.bill.model;

/**
 * Manages a set of BillItems.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class BillCalculator {
	public static final double TAX = 0.1;
	public static final double TIP = 0.2;
	
	/**
	 * Return the subTotal for the bill
	 * 
	 * @return the subTotal for the bill
	 * @param items a array of BillIems
	 */
	public static double calculateSubtotal(BillItem[] items) {
		double subTotal = 0.0;
		for (int index = 0; index < items.length; index++) {
			if (items[index] != null) {
				subTotal += items[index].getAmount();
			}
		}
		subTotal = Math.round(subTotal * 100.0) / 100.0;
		return subTotal;
	}
	
	/**
	 * Calculate and return the tax for the bill
	 * 
	 * @return the taxAmount for the bill
	 * @param items a array of BillIems
	 */
	public static double calculateTax(BillItem[] items) {
		double subTotal = calculateSubtotal(items);
		double taxAmount = (subTotal * TAX);
		taxAmount =  Math.round(taxAmount * 100.0) / 100.0;
		return taxAmount;
	}
	
	/**
	 * Calculate and return the tip for the bill
	 * 
	 * @return the tipAmount for the bill
	 * @param items a array of BillIems
	 */
	public static double calculateTip(BillItem[] items) {
		double subTotal = calculateSubtotal(items);
		double tipAmount = (subTotal * TIP);
		tipAmount = Math.round(tipAmount * 100.0) / 100.0;
		return tipAmount;
	}
	
	/**
	 * Calculate and return the total of the bill
	 * 
	 * @return the total for the bill
	 * @param items a array of BillIems
	 */
	public static double calculateTotal(BillItem[] items) {
		double subTotal = calculateSubtotal(items);
		double taxAmount = calculateTax(items);
		double tipAmount = calculateTip(items);
		double total = subTotal + taxAmount + tipAmount;
		total = Math.round(total * 100.0) / 100.0;
		return total;
	}

}	
