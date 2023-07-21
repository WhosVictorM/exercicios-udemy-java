package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Employee;
import entities.OutsourcedEmployee;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		List<Employee> list = new ArrayList<>();
		
		System.out.println("Number of Employees");
		int n = sc.nextInt();
		
		for(int i=0; i<n; i++) {
			System.out.printf("Employee #%d:\n", i+1);
			System.out.println("Outsourced? (y/n): ");
			char ch = sc.next().charAt(0);
			System.out.print("Name: ");
			sc.nextLine();
			String name = sc.nextLine();
			System.out.println("Hours: ");
			int hour = sc.nextInt();
			System.out.println("Value per hour: ");
			double valuePerHour = sc.nextDouble();
			if(ch == 'y') {
				System.out.println("Additional charge: ");
				double additionalCharge = sc.nextDouble();
				Employee emp = new OutsourcedEmployee(name, hour, valuePerHour, additionalCharge);
				list.add(emp);
			}
			else {
				Employee emp = new Employee(name, hour, valuePerHour);
				list.add(emp);
			}
		}
		System.out.println();
		System.out.println("PAYMENTS: ");
		for(Employee emp : list) {
			System.out.printf("%s - $%.2f\n", emp.getName(), emp.payment());
		}
		
		
		
		sc.close();
	}

}
