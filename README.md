# Movie-Booking-System
MovieTicketBooking is a Java-based application that allows users to book movie tickets online. It enables users to view available movies, select seats, and make payments securely. The application provides a simple and user-friendly interface for booking tickets hassle-free

# Overview:
This is a Java OOP project that implements a movie booking system. The system allows customers to view a list of available movies, select a movie, and book a seat for the selected movie. The project is built entirely using Object-Oriented Programming concepts and is completely hardcoded, meaning it does not use any database.

The Movie class represents a movie that is being screened in a cinema. It has a title and duration property.
The Screen class represents a screen in the cinema. It has an id, capacity, and a movie property.
The Seat class represents a seat in the cinema. It has an id, isOccupied, and a seatType property.
The SeatType class represents the type of seat in the cinema. It has a name and price property.
The Customer class represents a customer who is booking tickets. It extends the User class and has a name and email property. It also has a static email pattern to validate the email entered by the customer.
The Payment class represents a payment made by a customer. It has a amount property.
The Ticket class represents a ticket that is booked by a customer. It has a screen, customer, seats, and payment property. It also has a getTotalCost method that calculates the total cost of the ticket.
The Promotion interface is used to represent various promotions that can be applied to a ticket. Currently, there is only one implementation of the Promotion interface called EarlyBirdDiscount which provides a 10% discount on the ticket.
The code also contains a checkLogin method in the User class to check if the username and password entered by a user is correct.

# Features:

The project is built using Java OOP concepts, making it easy to understand and maintain.
The system allows customers to view a list of available movies, select a movie, and book a seat for the selected movie.
The project has been designed with a modular structure, making it easy to add or remove features.
The system makes use of inheritance, encapsulation, and polymorphism concepts, making the code more efficient and reusable.

# How to use:
To use the movie booking system, follow these steps:

1.Clone or download the project from the GitHub repository.
2.Open the project in any Java IDE (Integrated Development Environment) like Eclipse, NetBeans, or IntelliJ IDEA.
3.Run the project 

# Conclusion:

This Java OOP project provides a simple yet efficient solution for a movie booking system. It uses Object-Oriented Programming concepts to provide a modular, maintainable, and reusable codebase. The project is completely hardcoded, making it easy to understand and use without the need for any database.
