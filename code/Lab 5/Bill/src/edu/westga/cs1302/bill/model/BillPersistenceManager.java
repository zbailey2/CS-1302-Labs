package edu.westga.cs1302.bill.model;

import java.io.File;
import java.io.FileNotFoundException;
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
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static Bill loadBillData() throws FileNotFoundException, IOException {
		Bill bill = new Bill();
		File inputFile = new File(DATA_FILE);
		try (Scanner scanner = new Scanner(inputFile)) {
			while (scanner.hasNextLine()) {
				String baseline = scanner.nextLine();
				String strippedLine = baseline.strip();
				if (strippedLine.contains(":")) {
					String [] parts = strippedLine.split(": ");
					String label = parts[0];
					String value = parts[1];
					if (label.equals("Server")) {
						bill.setServerName(value);
					} 
				} else if (strippedLine.contains(" - ")) {
					String[] itemParts = strippedLine.split(" - ");
					String itemName = itemParts[0];
					Double itemCost = Double.parseDouble(itemParts[1]);
					BillItem currentItem = new BillItem(itemName, itemCost);
					bill.addItem(currentItem);
				} else {
					throw new IOException("Invalid file format for item price, item name, or server name");
				}
				
			}
		}
		return bill;
	}

}
