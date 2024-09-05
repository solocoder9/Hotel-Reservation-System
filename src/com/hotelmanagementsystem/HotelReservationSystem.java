package com.hotelmanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class HotelReservationSystem {

	private static final String url = "jdbc:mysql://localhost:3306/hotel_db";
	private static final String username = "root";
	private static final String password = "solo";

	// Method to reserve a room
	public static void reserveRoom(Connection con, Scanner sc) {

		try {
			System.out.print("Enter guest name: ");
			String guestName = sc.next();
			sc.nextLine();

			System.out.print("Enter room number: ");
			int roomNumber = sc.nextInt();

			System.out.print("Enter contact number: ");
			String contactNumber = sc.next();

			String sqlQuery = "INSERT INTO reservations (guest_name, room_number, contact_number) " + "VALUES ('"
					+ guestName + "', " + roomNumber + ", '" + contactNumber + " ')";

			try (Statement stmt = con.createStatement()) {

				int affectedRows = stmt.executeUpdate(sqlQuery);

				if (affectedRows > 0) {
					System.out.println("Reservation Successful!");
				} else {
					System.out.println("Reservation Failed.");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// Method to view reservation
	public static void viewReservations(Connection con) throws SQLException {

		String sqlQuery = "SELECT reservation_id, guest_name, room_number, contact_number, reservation_date FROM reservations";

		try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sqlQuery)) {

			System.out.println("Current Reservations:");
			System.out.println(
					"+----------------+-----------------+---------------+----------------------+-------------------------+");
			System.out.println(
					"| Reservation ID | Guest           | Room Number   | Contact Number       | Reservation Date        |");
			System.out.println(
					"+----------------+-----------------+---------------+----------------------+-------------------------+");

			while (rs.next()) {
				int reservationID = rs.getInt("reservation_id");
				String guestName = rs.getString("guest_name");
				int roomNumber = rs.getInt("room_number");
				String contactNumber = rs.getString("contact_number");
				String reservationDate = rs.getTimestamp("reservation_date").toString();

				// Format and display the reservation data in a table-like format
				System.out.printf("| %-14d | %-15s | %-13d | %-20s | %-19s   |\n", reservationID, guestName, roomNumber,
						contactNumber, reservationDate);
			}

			System.out.println(
					"+----------------+-----------------+---------------+----------------------+-------------------------+");
		}
	}

	// Method to get room number
	public static void getRoomNumber(Connection con, Scanner sc) {

		try {
			System.out.print("Enter reservation ID: ");
			int reservationID = sc.nextInt();

			System.out.print("Enter guest name: ");
			String guestName = sc.next();

			String sqlQuery = "SELECT room_number FROM reservations " + "WHERE reservation_id = " + reservationID
					+ " AND guest_name = '" + guestName + "'";

			try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sqlQuery)) {

				if (rs.next()) {
					int roomNumber = rs.getInt("room_number");
					System.out.println("Room number for Reservation ID " + reservationID + " and Guest " + guestName
							+ " is: " + roomNumber);
				} else {
					System.out.println("Reservation not found for the given ID and guest name.");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Method to update reservation
	public static void updateReservation(Connection con, Scanner sc) {

		try {
			System.out.print("Enter reservation ID to update: ");
			int reservationID = sc.nextInt();
			sc.nextLine(); // consume the new line character

			if (!reservationExists(con, reservationID)) {
				System.out.println("Reservation not found for the given ID.");
			}

			System.out.print("Enter new guest name: ");
			String newGuestName = sc.nextLine();

			System.out.print("Enter new room number: ");
			int newRoomNumber = sc.nextInt();

			System.out.print("Enter new contact number: ");
			String newContactNumber = sc.next();

			String sqlQuery = "UPDATE reservations SET guest_name = '" + newGuestName + "', " + "room_number = "
					+ newRoomNumber + ", " + "contact_number = '" + newContactNumber + "' " + "WHERE reservation_id = "
					+ reservationID;

			try (Statement stmt = con.createStatement()) {

				int affectedRows = stmt.executeUpdate(sqlQuery);

				if (affectedRows > 0) {
					System.out.println("Reservation updated successfully!");
				} else {
					System.out.println("Reservation update failed.");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Method to delete reservation
	public static void deleteReservation(Connection con, Scanner sc) {

		try {
			System.out.print("Enter reservation ID to delete: ");
			int reservationID = sc.nextInt();

			if (!reservationExists(con, reservationID)) {
				System.out.println("Reservation not found for the give ID.");
				return;
			}

			String sqlQuey = "DELETE FROM reservations WHERE reservation_id = " + reservationID;

			try (Statement stmt = con.createStatement()) {

				int rowsAffected = stmt.executeUpdate(sqlQuey);

				if (rowsAffected > 0) {
					System.out.println("Reservation deleted successfully!");
				} else {
					System.out.println("Reservation deletion failed.");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Method to check whether the reservation is exists or not
	public static boolean reservationExists(Connection con, int reservationID) {

		try {
			String sqlQuery = "SELECT reservation_id FROM reservations WHERE  reservation_id = " + reservationID;

			try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sqlQuery)) {

				return rs.next(); // If there's a result, the reservation exists
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false; // Handle database errors as needed
		}
	}

	// Method to exit from reservation system
	public static void exit() throws InterruptedException {

		System.out.print("Exiting System");
		int i = 5;

		while (i != 0) {
			System.out.print(".");
			Thread.sleep(450);
			i--;
		}

		System.out.println();
		System.out.println("Thank You for Using Hotel Management System!!!");
	}

	// Main method
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}

		try {
			Connection con = DriverManager.getConnection(url, username, password);

			while (true) {
				System.out.println();
				System.out.println("HOTEL MANAGEMENT SYSTEM");
				Scanner sc = new Scanner(System.in);

				System.out.println("1. Reserve a Room");
				System.out.println("2. View Reservation");
				System.out.println("3. Get Room Number");
				System.out.println("4. Update Reservatins");
				System.out.println("5. Delete Reservations");
				System.out.println("0. Exit");

				System.out.print("Choose an option: ");
				int choice = sc.nextInt();

				switch (choice) {
				case 1:
					reserveRoom(con, sc);
					break;
				case 2:
					viewReservations(con);
					break;
				case 3:
					getRoomNumber(con, sc);
					break;
				case 4:
					updateReservation(con, sc);
					break;
				case 5:
					deleteReservation(con, sc);
					break;
				case 0:
					exit();
					sc.close();
					return;
				default:
					System.out.println("Invalid choice. Try again.");

				}
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
