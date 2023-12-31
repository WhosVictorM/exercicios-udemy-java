package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import entities.Department;
import entities.HourContract;
import entities.Worker;
import entities.enums.WorkerLevel;

public class Program {

	public static void main(String[] args) throws ParseException {
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.print("Enter the Department name: ");
		String departmentName = sc.nextLine();
		System.out.println("Worker Data");
		System.out.print("Name: ");
		String workerName = sc.nextLine();
		System.out.print("Level: ");
		String workerLevel = sc.nextLine();
		System.out.println("Base Salary: ");
		double baseSalary = sc.nextDouble();
		
		Worker worker = new Worker(workerName, WorkerLevel.valueOf(workerLevel), baseSalary, new Department(departmentName));
		
		System.out.print("How many contracts: ");
		int n = sc.nextInt();
		
		for(int i=0; i<n; i++) {
			System.out.printf("Contract #%d Data: ", i+1);
			System.out.print("Date (DD/MM/YYYY): ");
			Date contractDate = sdf.parse(sc.next());
			System.out.print("Value per Hour: ");
			double valuePerHour = sc.nextDouble();
			System.out.print("Duration (Hours): ");
			int hours = sc.nextInt();
			HourContract contract = new HourContract(contractDate, valuePerHour, hours);
			worker.addContract(contract);
			
		}
		System.out.print("Enter month and year to calculate income (MM/YYYY): ");
		String monthNyear = sc.next();
		int month = Integer.parseInt(monthNyear.substring(0, 2));
		int year = Integer.parseInt(monthNyear.substring(3));
		System.out.printf("Name: %s\n", worker.getName());
		System.out.printf("Department: %s\n", worker.getDepartment().getName());
		System.out.printf("Income for %s: $%.2f\n", monthNyear, worker.income(year, month));
		
		sc.close();
	}

}
