package application;

import dao.DBConnection;

public class Application {

	public static void main(String[] args) {
		Options options = new Options();
		create();
		options.start();
	}
	private static void create() {
		DBConnection.resetCar();
	}

}
