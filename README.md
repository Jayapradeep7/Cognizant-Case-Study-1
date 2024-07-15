# Cognizant-Case-Study-1

Hotel Reservation System

Features:

View Room Availability:

* Allows users to check the availability of rooms for a specific date range.
* Displays available rooms with details such as room number, type, and price.

Make Reservation:

* Enables users to make a reservation for a room.
* Shows reservation details including room number, guest name, check-in and check-out dates, and total price.

Cancel Reservation:

* Allows users to cancel a reservation.
* Updates the room availability status accordingly.

Check-in:

* Users can check-in to a reserved room by providing the reservation ID.
* Updates the room's availability status to indicate it is occupied.

Check-out:

* Users can check-out from a room by providing the reservation ID.
* Updates the room's availability status to indicate it is available again.

Code Overview:

The application consists of several classes, each with specific responsibilities, organized using Object-Oriented Programming (OOP) principles:

Room Class:

* Represents a hotel room with attributes: number, type, price, and availability status.
* Contains getter and setter methods to access and modify room attributes.
* Overrides the toString method to provide a string representation of the room.

Reservation Class:

* Represents a reservation with attributes: ID, room ID, guest name, check-in date, check-out date, and total price.
* Contains getter methods to access reservation details.
* Uses a static counter to generate unique reservation IDs.
* Overrides the toString method to provide a string representation of the reservation.

HotelManager Class:

* Manages the list of rooms and reservations.
* Provides methods to add rooms, check room availability, make reservations, cancel reservations, check-in, and check-out.
* Contains helper methods to get a room by its ID and to get a reservation by its ID.

Main Class (HotelReservationSystem):

* Contains the main method to run the application.
* Initializes the rooms and displays a menu for user interaction.
* Implements a loop to continuously prompt the user for actions: view room availability, make reservations, cancel reservations, check-in, check-out, or exit.
* Uses a Scanner for user input and SimpleDateFormat for date parsing and formatting.
* Handles exceptions to provide meaningful error messages to the user.

How to run the application:

Prerequisites:
* I have installed the Java Development Kit (JDK) on my machine.
* I have an Integrated Development Environment (IDE)-IntelliJ IDEA.

Instructions for Each Feature:

View Room Availability:

* Enter the check-in and check-out dates when prompted to see a list of available rooms within the specified date range.

Make Reservation:

* Provide the room number, guest name, and check-in/check-out dates to create a new reservation.
* The application will display the reservation details upon successful booking.

Cancel Reservation:

* Enter the reservation ID to cancel an existing reservation.
* The application will confirm the cancellation and update the room availability.

Check-in:

* Provide the reservation ID to check-in to a reserved room.
* The application will confirm the check-in and update the room's status to occupied.

Check-out:

* Provide the reservation ID to check-out from a room.
*The application will confirm the check-out and update the room's status to available again.

Explanation of the exception handling implemented:

* The application includes exception handling to manage potential errors gracefully.
* Common exceptions handled include:
     * Invalid date formats.
     * Unavailable rooms for the specified date range.
     * Non-existent reservation IDs.
* Custom exceptions can be implemented for more specific error handling if needed.

Application Design

Encapsulation:

* Attributes of classes are private, with public getter and setter methods to access and modify them.

Abstraction:

* Complex operations like room availability checks and reservation management are encapsulated within the HotelManager class.

Inheritance and Polymorphism:

*Although not directly used in this implementation, the design allows for easy extension, such as different room types or additional operations.

* Modularity:

The code is organized into separate classes, each responsible for specific functionality, promoting maintainability and scalability.

This Hotel Reservation System demonstrates how to build a basic console-based application using Core Java, OOP principles, and Java Collections. It provides a practical example of managing a hotel's operations programmatically.
