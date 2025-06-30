ATM System with Factory Method Pattern, Strategy Pattern, Command Pattern

---

# Automated Teller Machine (ATM) System in Java Springboot

## Introduction
This project implements an Automated Teller Machine (ATM) system in Java, which demonstrates object-oriented programming concepts and the use of design patterns such as Factory, Strategy and Command pattern. The system supports operations like balance inquiry, deposits, withdrawals, and fund transfers. It in memory for data persistence instead of a database.

## Getting Started

### Prerequisites:

- **Java Development Kit (JDK)** 8 or higher
- **Text Editor/IDE** (e.g., IntelliJ IDEA, Eclipse, or VSCode)

### Setup Instructions:

1. **Clone the Repository:**
   - Download the project folder or clone the repository:
     ```
     git clone <repository-url>
     ```

2. **Compile the Code:**
   - You can either compile manually or use the provided script.

3. **Run the Application:**
   - Follow the on-screen prompts to perform operations like login, deposit, withdraw, balance, and transfer.

---

## Usage Instructions

### Option 1: Using `start.sh` Script (Recommended)

To use the ATM system, you can use the provided `start.sh` script to compile and run the application.

1. **Set up your environment:**
   - Make sure you have the **Java Development Kit (JDK)** installed (version 8 or higher).
   - Ensure your terminal is pointed at the root of the project directory.

2. **Run the provided `start.sh` script:**
   - The script will:
     - Compile the Java source code.
     - Run the application.
   
   Use the following command to execute the script:
   ```bash
   ./start.sh

### Option 2:Manual Compile and Invoke (MCI)

If you prefer to run the application manually, follow these steps to compile and run the program without using the script:

1. **Navigate to the Project Directory:**
   - Navigate to the Project Directory.
   - open Intellij Idea

2. **Compile the Java Code**
   - type mvn clean install in terminal
   - on Main.java right-click then run.


## Features and Functionality
### Key Features:

1. **Balance Inquiry**  
   - Displays the current balance after successful login.

2. **Deposit Funds**  
   - Allows users to deposit money into their account.

3. **Withdraw Funds**  
   - Enables users to withdraw money, with balance checks to ensure sufficient funds.

4. **Transfer Funds**  
   - Users can transfer funds to other users. The system checks for the recipient’s existence and balance sufficiency.
  
---
## Design Patterns Used

### 1. **Command Pattern**

**Purpose:**  
Encapsulate a request as an object, allowing parameterization of clients with queues, requests, and operations.

**How It’s Used:**  
Each operation (like login, deposit, withdraw, transfer, logout, and balance) is encapsulated as a **Command** object. The **CommandFactory** creates the appropriate **Command** based on user input, and the **CommandInvoker** is responsible for executing them. This decouples commands from the rest of the system, enabling flexibility and extensibility. New commands can be added without modifying other parts of the code.

**Key Elements:**
- **Command Interface** (or abstract class in some cases)
- **Concrete Command classes** (e.g., DepositCommand, LoginCommand)
- **CommandFactory** for command creation
- **CommandInvoker** for executing commands

This pattern is the primary enabler of flexibility and extensibility in the solution, encapsulating operations to be executed later.

---

### 2. **Factory Method Pattern**

**Purpose:**  
Define an interface for creating objects, but allow subclasses to alter the type of objects that will be created.

**How It’s Used:**  
The **CommandFactory** class acts as a factory that dynamically creates instances of commands based on user input. The factory isolates the command creation process from the rest of the codebase, making it easy to add new commands without changing the program flow.

**Key Elements:**
- **Factory class** (CommandFactory) decides which Command subclass to instantiate based on input.
- **Dynamic creation of commands** via a Map (`Map<String, Supplier<Command>>`) allows easy registration of new commands.
- The factory abstracts object creation and can easily be modified or extended.

This pattern allows for easy extension of commands without altering the flow of the program.

---

### 3. **Strategy Pattern**

**Purpose:**  
Define a family of algorithms, encapsulate each one, and make them interchangeable.

**How It’s Used:**  
Commands are interchangeable. Each command (DepositCommand, WithdrawCommand, etc.) implements the **Command** interface. Depending on the user input, the **CommandFactory** dynamically decides which Command to execute. Each command encapsulates a specific algorithm (e.g., deposit, withdraw), and can easily be swapped without altering the flow of the application.

**Key Elements:**
- **Command interface** defines the algorithm signature.
- **Concrete command implementations** (e.g., DepositCommand, WithdrawCommand) encapsulate different algorithms (e.g., how to process a deposit or withdrawal).
  
The Strategy Pattern is used in how commands implement a consistent interface (`execute()`), but each command encapsulates a different algorithm (e.g., deposit or withdrawal).

---

