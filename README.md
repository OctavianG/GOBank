# GoBank - Java Banking Application
GoBank is a comprehensive and user-friendly JavaFX-based banking application that provides both clients and administrators with a seamless and secure banking experience. This project showcases a rich set of features, user authentication, and data security through SQLite data storage. It is built using the Maven build system, which simplifies project management and dependencies.

## Project Overview

### Objective
The primary objective of this project is to develop a robust and user-friendly banking application using JavaFX and Maven.

### User Authentication
- GoBank implements user authentication with roles for clients and administrators.
- Users must enter correct login credentials to access the application.

### Client Features
- Account Management: Allows clients to manage their checking and savings accounts.
- Fund Transfers: Clients can transfer funds between accounts.
- Transaction History: Provides a transaction history for clients.
- Enhanced User Experience: Delivers a streamlined user interface for clients.

### Administrator Features
- Client Management: Administrators can create and delete client accounts.
- Access Client Lists: Efficiently manage and access lists of client accounts.

### Data Security
GoBank ensures data security and integrity by utilizing SQLite data storage.
User data, including account details, is securely stored and managed.

### Skill Development
The project provides hands-on experience with several technologies, including:
- Java
- JavaFX
- Object-Oriented Programming (OOP) principles
- Cascading Style Sheets (CSS)
- Database Management with SQLite

## Application Structure

### Login Page
Users are presented with a login page where they can choose between client and admin roles. Correct credentials are required for access.

### Client Functionalities
- Dashboard: Clients can view their checking and savings accounts with detailed information and balances.
- Transactions: Clients can review their transaction history, including messages associated with each transaction.
- Accounts: Clients can view account information, such as account numbers, balances, and account creation dates. They can also transfer funds between accounts.
- Profile: Clients can view and update their personal information, including first name, last name, and password.

### Admin Functionalities
- Create Client: Administrators can create new client accounts and store them in the database.
- Clients View: Administrators can access a list of all client accounts and delete client accounts as needed.

## Features in Detail

### Transactions
The application includes features to track transaction history, balance updates, and fund transfers. Users can send money with messages to other clients and access their latest transactions.

### Styling
Cascading Style Sheets (CSS) have been implemented to enhance the application's visual appeal. Some buttons and labels feature FontAwesomeIcons for added aesthetic value.

### Logout
Both clients and administrators have access to a logout button, providing a convenient way to return to the login page.

## Usage

### Login
Users must enter valid login credentials to access the application.
You can login as an admin with username Admin and password 123456.
You can check gobank.db for other clients payee address and passwords

### Client Features
- Navigate through the dashboard to view account details.
- Access the transactions page to review transaction history.
- Manage accounts and perform fund transfers.

### Administrator Features
- Create and delete client accounts.
- Access lists of client accounts for efficient management.

### Data Security
User data is securely stored in an SQLite database.

### Maven Build
This project is managed and built using the Maven build system, making it easy to manage project dependencies and build configurations.

### Skills Development
Gain hands-on experience in Java, JavaFX, OOP, CSS, database management, and Maven through this project.

## Getting Started
Follow these steps to get started with GoBank:

1. Clone the repository.
2. Open the project in your Java IDE.
3. Use Maven to build and run the application.

## License
This project is licensed under the MIT License.
