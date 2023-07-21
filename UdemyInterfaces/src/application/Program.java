package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

public class Program {

	public static void main(String[] args) {

		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

		Scanner sc = new Scanner(System.in);

		System.out.println("Rental Details: \n");
		System.out.print("Car Model: ");
		String model = sc.nextLine();
		System.out.print("Start date (DD/MM/YYYY HH:MM): ");
		LocalDateTime start = LocalDateTime.parse(sc.nextLine(), fmt);
		System.out.print("Finish date (DD/MM/YYYY HH:MM): ");
		LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), fmt);

		CarRental cr = new CarRental(start, finish, new Vehicle(model));

		System.out.print("Price per Hour: ");
		double pricePerHour = sc.nextDouble();
		System.out.print("Price per Day: ");
		double pricePerDay = sc.nextDouble();

		RentalService rentalService = new RentalService(pricePerHour, pricePerDay, new BrazilTaxService());
		
		rentalService.processInvoice(cr);
		
		System.out.println("\nInvoice: \n");
		System.out.printf("Basic Payment: %.2f", cr.getInvoice().getBasicPayment());
		System.out.printf("Tax: %.2f", cr.getInvoice().getTax());
		System.out.printf("Total Payment: %.2f", cr.getInvoice().getTotalPayment());

		sc.close();
	}

}