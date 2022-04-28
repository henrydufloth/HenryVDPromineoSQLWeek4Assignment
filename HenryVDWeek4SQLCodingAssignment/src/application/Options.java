package application;


import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dao.DBConnection;


public class Options {
	private Scanner scanner = new Scanner(System.in);
	private List<String> options = Arrays.asList(
			"Display Cars",
			"Display Car",
			"Create Car",
			"Update Car",
			"Delete Car");
	
	public void start() {
		String selection = "";
		
		do {
			printOptions();
			selection = scanner.nextLine();
			
			if(selection.equals("1")) {
				displayCars();
				} else if(selection.equals("2")) {
				displayCar();
				} else if(selection.equals("3")) {
				createCar();
				} else if(selection.equals("4")) {
				updateCar();
				} else if(selection.equals("5")) {
				deleteCar();
				}
			end();
			
		}	while (selection.equals("1") || 
				   selection.equals("2") || 
				   selection.equals("3") || 
				   selection.equals("4") || 
				   selection.equals("5")
				   );
		
		restart();	
	}
	private void end() {
		System.out.println("Press Enter To Continue or type 'RESET' to reset everything");
		String resetCheck = scanner.nextLine();	
		if(resetCheck.equals("RESET")) {
			reset();
		}
	}
	private void restart() {
		System.out.println("Choose a valid selection");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		start();
	}
	
	private void reset() {
		System.out.println("RESETING......");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		DBConnection.resetCar();
	}
	private void displayCars() {
		DBConnection.getCars();
	}
	private void displayCar() {
		System.out.println("Enter the following information:");
		DBConnection.getCar();
	}
	private void createCar() {
		System.out.println("Enter the following information:");
		DBConnection.createCar();
	}
	private void updateCar() {
		System.out.println("Enter the following information:");
		DBConnection.updateCar();
	}
	private void deleteCar() {
		System.out.println("Enter the following information:");
		DBConnection.deleteCar();
	}


	private void printOptions() {
		System.out.println("Select Option:\n--------------------------");
		for (int i = 0; i < options.size(); i++) {
			System.out.println(i + 1 + ") " + options.get(i));
		}		
	}
}
