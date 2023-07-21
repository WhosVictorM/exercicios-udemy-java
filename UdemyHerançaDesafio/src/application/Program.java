package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import entities.ImportedProduct;
import entities.Product;
import entities.UsedProduct;

public class Program {

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Scanner sc = new Scanner(System.in);
		
		List<Product> list = new ArrayList<>();
		System.out.print("Number of products: ");
		int n = sc.nextInt();
		
		for(int i=0; i<n; i++) {
			System.out.printf("Product #%d Data: \n", i+1);
			System.out.print("Common, Used or Imported (c/u/i)? ");
			char ch = sc.next().charAt(0);
			System.out.print("Name: ");
			sc.nextLine();
			String name = sc.nextLine();
			System.out.print("Price: ");
			double price = sc.nextDouble();
			if(ch == 'u') {
				System.out.print("Manufacture date: ");
				Date date = sdf.parse(sc.next());
				list.add(new UsedProduct(name, price, date));
			}
			else if (ch == 'i') {
				System.out.print("Customs fee: ");
				double fee = sc.nextDouble();
				list.add(new ImportedProduct(name, price, fee));
			}
			else {
				list.add(new Product(name, price));
			}
		}
		System.out.println("\nPRICE TAGS: ");
		for(Product p : list) {
			System.out.println(p.priceTag());
		}
		
		sc.close();
	}

}
