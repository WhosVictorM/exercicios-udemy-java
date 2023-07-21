package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import model.entities.Employee;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter salary: ");
		double salary = sc.nextDouble();

		try (BufferedReader br = new BufferedReader(new FileReader("C:\\temp\\employee.csv"))) {
			List<Employee> list = new ArrayList<>();
			String line = br.readLine();

			while (line != null) {
				String[] part = line.split(",");
				list.add(new Employee(part[0], part[1], Double.parseDouble(part[2])));
				line = br.readLine();
			}

			List<String> emails = list.stream().filter(p -> p.getSalary() > salary).map(p -> p.getEmail()).sorted().collect(Collectors.toList());

			emails.forEach(System.out::println);

			double sum = list.stream().filter(x -> x.getName().charAt(0) == 'M').map(x -> x.getSalary()).reduce(0.0,(x, y) -> x + y);

			System.out.println("Sum of salary 'M' people: " + String.format("%.2f", sum));

		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		sc.close();

	}

}
