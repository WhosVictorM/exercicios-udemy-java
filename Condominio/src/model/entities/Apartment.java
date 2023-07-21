package model.entities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import model.exceptions.IllegalApartment;

public class Apartment extends Floors{
	private int number; 
	private int gasQuantity;
	private double cond;
	
	public Apartment() {
		super();
	}

	public Apartment(int number, int gasQuantity, double cond, int floors) {
		super(floors);
		this.number = number;
		this.gasQuantity = gasQuantity;
		this.cond = cond;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getGasQuantity() {
		return gasQuantity;
	}

	public void setGasQuantity(int gasQuantity) {
		this.gasQuantity = gasQuantity;
	}
	
	public double getCond() {
		return cond;
	}

	public void setCond(int cond) {
		this.cond = cond;
	}
	
	
	// 01 = 650
	// 02 = 500
	// 03 = 416
	// 04 = 411
	
	// 201 - 1.061,00
	// 1203 - 830,00
	// 1301 - 1.160,00
	// 1302 - 830,00
	// 1401 - 650,00
	
	
	

	public double incomes(int number, int floor, double gas) {
		int lastDigit = Math.abs(number % 10);
		
		if (lastDigit == 1) {
			
			if(floor == 2 && lastDigit == 1) {
				return 1061.00 + gas;
			}
			if(lastDigit == 1 && floor == 13) {
				return 1160.00 + gas;
			}
			return 650.00 + gas;
		}
		
		if (lastDigit == 2) {
			double income = 500.00 + gas;
			if(lastDigit == 2 && floor == 13) {
				return income = 830.00 + gas;
			}
			return income;
		}
		if (lastDigit == 3) {
			double income = 416.00 + gas;
			if(lastDigit == 3 && floor == 12) {
				return income = 830.00 + gas;
			}
			return income;
		}
		if (lastDigit == 4) {
			double income = 416.00 + gas;
			return income;
		}
		
		throw new IllegalApartment("Apartment invalid: the only possible finals is 01 to 04") ;
	}
	
	public double gasIncome(int gasQuantity) {
		return gasQuantity * 13.88;
	}
	
	public double gasIncomeFile(int number, int gasQuantity) {
		try {

			// Create a object to represent the file;
			File file = new File("Condominio.txt");

			// Scan the file line by line;
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();

				// Setted ":" as line splitter;
				String[] parts = line.split(":");

				// Check if the line has 2 parts, one before ":" and another one after ":";
				int aux = Integer.parseInt(parts[0]);
				if (parts.length == 4 && aux == number) {
					int strToInt = Integer.parseInt(parts[1]);
					double sub = (gasQuantity - gasFile(number) ) * 13.88;
					scanner.close();
					return sub;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		throw new RuntimeException("Error");
	}
	
	public int gasFile(int number) {
		try {

			// Create a object to represent the file;
			File file = new File("Condominio.txt");

			// Scan the file line by line;
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();

				// Setted ":" as line splitter;
				String[] parts = line.split(":");

				// Check if the line has 4 parts, one before ":" and another one after ":";
				int aux = Integer.parseInt(parts[0]);
				if (parts.length == 4 && aux == number) {
					int strToInt = Integer.parseInt(parts[1]);
					int sub = strToInt;
					scanner.close();
					return sub;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		throw new RuntimeException("Error");
	}
	
	
	public boolean saveData(int number, int gasQuantity, double gas, double cond) {
		try {

			// Opens the file and writes to an existing one, avoiding the creation of a new
			// file (true);
			FileWriter writer = new FileWriter("Condominio.txt", true);

			// Write in the file User and Pass in a single line divided by ":" and create a
			// new line by "\n";
			writer.write(number + ":" + gasQuantity + ":" + gasIncomeFile(getNumber(),getGasQuantity()) + ":" + incomes(getNumber(), getFloors(), gasIncomeFile(getNumber(), getGasQuantity())) + "\n");
			writer.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public String toString() {
		return "\nAPTO: " + getNumber() + "\n" + "Gas: " + gasIncome(getGasQuantity()) + "\n" + "Cond: " + incomes(getNumber(), getFloors(), gasIncome(getGasQuantity())) + "\n";
	}
	
}
