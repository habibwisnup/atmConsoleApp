package org.habibwisnup.factory.commands;

import org.habibwisnup.factory.Command;

public class HelpCommand implements Command {
    @Override
    public void execute() {
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

    @Override
    public Command initialize(String[] parts) {
        return this;
    }
}
