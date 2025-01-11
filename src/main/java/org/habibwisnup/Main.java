package org.habibwisnup;

import org.habibwisnup.factory.Command;
import org.habibwisnup.factory.CommandFactory;
import org.habibwisnup.factory.CommandInvoker;
import org.habibwisnup.managers.CustomerManager;
import org.habibwisnup.utils.errorHandler.ErrorHandler;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        CustomerManager customerManager = new CustomerManager();
        CommandFactory commandFactory = new CommandFactory(customerManager);
        CommandInvoker commandInvoker = new CommandInvoker();

        System.out.println("Welcome to the HABIB ATM! Type your command:");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("> ");
                String input = scanner.nextLine().trim();
                String[] parts = input.split(" ");

                if (parts[0].equalsIgnoreCase("help")) {
                    displayHelpMenu();
                    continue;
                }

                Command command = commandFactory.getCommand(parts);
                commandInvoker.execute(command);

            } catch (Exception e) {
                ErrorHandler.handleException(e);
            }
        }
    }

    private static void displayHelpMenu() {
        System.out.println("Available Commands:");
        System.out.println("  help                - Show this help menu.");
        System.out.println("  login <username>    - Log in as a specific user.");
        System.out.println("  logout              - Log out of the current session.");
        System.out.println("  deposit <amount>    - Deposit a specified amount of money.");
        System.out.println("  withdraw <amount>   - Withdraw a specified amount of money.");
        System.out.println("  transfer <recipient> <amount> - Transfer money to another user.");
        System.out.println("  balance             - Display your current balance.");
        System.out.println("  exit                - Exit the ATM Console App.");
    }
}