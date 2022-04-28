package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;



public class DBConnection {

	static Scanner scanner = new Scanner(System.in);

	private static String URL = "jdbc:mysql://localhost:3306/cars";
	private static String USERNAME = "root";
	private static String PASSWORD = "12345678";	
	
	public static void getCars() {
		try {
			String CAR_BY_ID_QUERY = "SELECT * FROM cars";
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			PreparedStatement ps = conn.prepareStatement(CAR_BY_ID_QUERY);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				System.out.println("ID: " + rs.getInt(1) + " Name: " + rs.getString(2) + " Price: $" + rs.getInt(3));
			}
			
			} catch (SQLException e) {
				System.out.println("Loss");
				e.printStackTrace();
				}
	}
	
	public static void getCar() {
		try {
			String CAR_BY_ID_QUERY = "SELECT * FROM cars WHERE id = ?";
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			System.out.print("ID of Car: ");
			String carId = scanner.nextLine();
			PreparedStatement ps = conn.prepareStatement(CAR_BY_ID_QUERY);
			ps.setString(1, carId);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				System.out.println("ID: " + rs.getInt(1) + " Name: " + rs.getString(2) + " Price: $" + rs.getInt(3));
			}
			
			} catch (SQLException e) {
				System.out.println("Loss");
				e.printStackTrace();
				}
	}
	
	public static void createCar() {
		try {
			String CREATE_CAR = "INSERT INTO cars (name, price) VALUES (?,?)";
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			System.out.print("Name of Car: ");
			String carName = scanner.nextLine();
			System.out.print("Price of Car: ");
			String carPrice = scanner.nextLine();
			PreparedStatement ps = conn.prepareStatement(CREATE_CAR);
			ps.setString(1, carName);
			ps.setInt(2, Integer.parseInt(carPrice));
			ps.executeUpdate();
			
			} catch (SQLException e) {
				System.out.println("Loss");
				e.printStackTrace();
				}
	}
	
	public static void updateCar() {
		try {
			String UPDATE_CAR_BY_ID = "UPDATE cars SET name = ?, price = ? WHERE id = ?";
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			System.out.print("ID of Car: ");
			String carId = scanner.nextLine();
			System.out.print("Name of Car: ");
			String carName = scanner.nextLine();
			System.out.print("Price of Car: ");
			String carPrice = scanner.nextLine();
			PreparedStatement ps = conn.prepareStatement(UPDATE_CAR_BY_ID);
			ps.setString(3, carId);
			ps.setString(1, carName);
			ps.setInt(2, Integer.parseInt(carPrice));
			ps.executeUpdate();
			} catch (SQLException e) {
				System.out.println("Loss");
				e.printStackTrace();
				}
	}
	
	public static void deleteCar() {
		try {
			String DELETE_CAR_BY_ID = "DELETE FROM cars WHERE id = ?";
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			System.out.print("ID of Car: ");
			String carId = scanner.nextLine();
			PreparedStatement ps = conn.prepareStatement(DELETE_CAR_BY_ID);
			ps.setString(1, carId);
			ps.executeUpdate();	
			} catch (SQLException e) {
				System.out.println("Loss");
				e.printStackTrace();
				}
	}
	
	public static void resetCar() {
		try {
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement dropStatement = conn.createStatement();
			dropStatement.execute("DROP DATABASE cars");
			Statement createDbStatement = conn.createStatement();
			createDbStatement.execute("CREATE DATABASE cars");
			Statement useDbStatement = conn.createStatement();
			useDbStatement.execute("USE cars");
			Statement createTbStatement = conn.createStatement();
			createTbStatement.execute("CREATE TABLE cars (\n"
					+ "	id int(10) NOT NULL auto_increment,\n"
					+ "	name varchar(50) NOT null,\n"
					+ "	price int(50) NOT null,\n"
					+ "	PRIMARY KEY (id)\n"
					+ "	)");
			try {
				String CREATE_CAR = "INSERT INTO cars (name, price) VALUES (?,?)";
				System.out.println("Create your First Car: ");
				System.out.print("Name of Car: ");
				String carName = scanner.nextLine();
				System.out.print("Price of Car: ");
				String carPrice = scanner.nextLine();
				PreparedStatement ps = conn.prepareStatement(CREATE_CAR);
				ps.setString(1, carName);
				ps.setInt(2, Integer.parseInt(carPrice));
				ps.executeUpdate();
				
				} catch (SQLException e) {
					System.out.println("Loss");
					e.printStackTrace();
					}
			} catch (SQLException e) {
				System.out.println("Loss");
				e.printStackTrace();
				}
	}
}
