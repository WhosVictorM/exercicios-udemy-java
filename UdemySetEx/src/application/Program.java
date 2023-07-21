package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import model.entities.Users;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Set<Users> users = new HashSet<>();

		System.out.println("Enter the full file path: ");
		String path = sc.nextLine();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			
			String line = br.readLine();
			
			while (line != null) {
				String[] parts = line.split(" ");
				String name = parts[0];
				Date date = Date.from(Instant.parse(parts[1]));
				users.add(new Users(name, date));
				
				line = br.readLine();
			}
			System.out.println("Total Users: " + users.size());
			
			
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

		sc.close();
	}

}
