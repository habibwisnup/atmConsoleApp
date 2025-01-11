package org.habibwisnup.factory.commands;

import org.habibwisnup.factory.Command;
import org.habibwisnup.managers.CustomerManager;

public class WithdrawCommand implements Command {
    private final CustomerManager customerManager;
    private int amount;

    public WithdrawCommand(CustomerManager customerManager) {
        this.customerManager = customerManager;
    }

    @Override
    public void execute() {
        try {
            customerManager.getCurrentCustomer().withdraw(amount);
            System.out.println("Your balance is $" + customerManager.getCurrentCustomer().getCustomer().getBalance());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public Command initialize(String[] parts) {
        this.amount = Integer.parseInt(parts[1]);
        return this;
    }
}
