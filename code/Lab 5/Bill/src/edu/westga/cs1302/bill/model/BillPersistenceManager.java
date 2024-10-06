package edu.westga.cs1302.bill.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/** Supports saving and loading bill data,
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class BillPersistenceManager {
	
	public static final String DATA_FILE = "data.txt";
	
	/** Save the bill!
	 * 
	 * Writes all bill data to DATA_FILE
	 * 
	 * @precondition bill != null
	 * @postcondition none
	 * 
	 * @param bill the bill to save
	 * @throws IOException 
	 */
	public static void saveBillData(Bill bill) throws IOException {
		if (bill == null) {
			throw new IllegalArgumentException("Bill can not be null");
		}
		
		try (FileWriter writer = new FileWriter(DATA_FILE)) {
			writer.write("Server: " + bill.getServerName() + System.lineSeparator());
			for (BillItem item : bill.getItems()) {
				writer.write(item.getName() + " - " + item.getAmount() + System.lineSeparator());
			}
			writer.write("Subtotal: " + BillCalculator.getSubTotal(bill.getItems()) + System.lineSeparator());
			writer.write("Tax: " + BillCalculator.getTax(bill.getItems()) + System.lineSeparator());
			writer.write("Tip: " + BillCalculator.getTip(bill.getItems()) + System.lineSeparator());
			writer.write("Total: " + BillCalculator.getTotal(bill.getItems()) + System.lineSeparator());
		}
	}

	/** Load the bill!
	 * 
	 * Reads from DATA_FILE
	 * File is assumed to use the same format as saveBillData
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the bill loaded
	 */
//	public static Bill loadBillData() throws IOException {
//		Bill bill = new Bill();
//		File inputFile = new File(DATA_FILE);
//		try(Scanner scanner = new Scanner(inputFile)){
//			for(int lineNumber = 1; scanner.hasNextLine(); lineNumber++) {
//				String baseline = scanner.nextLine();
//				String strippedLine = baseline.strip();
//				String [] parts = strippedLine.split(": ");
//				String label = parts[0];
//				String value = parts[1];
//				if(label.equals("Subtotal")) {
//					double subTotal = Double.parseDouble(parts[1]);
//				} else if(label.equals("Tax")) {
//					double tax = Double.parseDouble(parts[1]);
//				} else if(label.equals("Tip")) {
//					double tip = Double.parseDouble(parts[1]);
//				} else if(label.equals("Total")) {
//					double total = Double.parseDouble(parts[1]);
//				} else if(label.equals("Server")) {
//					String server = parts[1];
//				} else {
//					String foodName = parts;
//				}
//			}
//		}
//	}

}
