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

        System.out.println("Welcome to the HABIB ATM! Type 'help' for available commands.");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("> ");
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    continue;
                }

                String[] parts = input.split(" ");

                Command command = commandFactory.getCommand(parts);
                commandInvoker.execute(command);

            } catch (Exception e) {
                ErrorHandler.handleException(e);
            }
        }
    }
}