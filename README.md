# 🏨 Hotel Management System

The Hotel Management System is a Java application designed to manage hotel reservations efficiently. It allows users to perform various operations such as reserving rooms, viewing reservations, updating reservations, deleting reservations, and retrieving room numbers based on reservation IDs and guest names.

## Table of Contents

- [🌟 Features](#features)
- [💻 Technologies Used](#technologies-used)
- [🛠️ Setup Instructions](#setup-instructions)
- [🚀 Usage](#usage)
- [🤝 Contributing](#contributing)
- [📝 License](#license)

## Features 🌟

1. **Reservation Operations:**
   - Reserve a room by providing guest details.
   - View existing reservations with details like guest name, room number, contact number, and reservation date.
   - Update reservation details such as guest name, room number, and contact number.
   - Delete reservations based on reservation ID.

2. **Interactive Console Interface:**
   - User-friendly interface with menu-driven options for easy navigation.

3. **Database Integration:**
   - Uses JDBC to connect to a MySQL database (`hotel_db`) running on `localhost`.

## Technologies Used 💻 

- Java
- JDBC (Java Database Connectivity)
- MySQL
- Eclipse IDE

## Setup Instructions 🛠️ 

To run the Hotel Management System on your local machine, follow these steps:

1. **Prerequisites:**
   - JDK (Java Development Kit) installed
   - MySQL installed with a database named `hotel_db`
   - Eclipse IDE (or any Java IDE) installed

2. **Clone the Repository:**
   ```bash
   git clone https://github.com/solocoder9/Hotel-Management-System.git

3. **Import the Project into Eclipse:**
   - Open Eclipse IDE.
   - Select `File` > `Import` > `Existing Projects into Workspace`.
   - Browse to the cloned repository directory and select the project.

4. **Configure MySQL Database:**
   - Ensure MySQL server is running.
   - Create a database named `hotel_db`:
     ```sql
     CREATE DATABASE hotel_db;
     ```

5. **Modify Database Connection Details:**
   - Open `HotelReservationSystem.java`.
   - Update the `url`, `username`, and `password` constants to match your MySQL server configuration:
     ```java
     private static final String url = "jdbc:mysql://localhost:3306/hotel_db";
     private static final String username = "root";
     private static final String password = "your-mysql-password";
     ```

6. **Run the Application:**
   - Right-click on `HotelReservationSystem.java` > `Run As` > `Java Application`.
   - Follow the on-screen prompts to interact with the Hotel Management System.

## Usage 🚀 

- Upon running the application, you will be presented with a menu.
- Choose an option (1-5) to perform the corresponding operations(CRUD).
- Follow the prompts to enter guest details, room numbers, etc.

## Contributing 🤝 

Contributions to the Hotel Management System project are welcome! To contribute:

1. Fork the repository.
2. Create your feature branch (`git checkout -b feature/YourFeature`).
3. Commit your changes (`git commit -am 'Add some feature'`).
4. Push to the branch (`git push origin feature/YourFeature`).
5. Create a new Pull Request.

## License 📝 

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

