package org.habibwisnup.factory.commands;

import org.habibwisnup.factory.Command;
import org.habibwisnup.managers.CustomerManager;

public class DepositCommand implements Command {
    private CustomerManager customerManager;
    private int amount;

    public DepositCommand(CustomerManager customerManager) {
        this.customerManager = customerManager;
    }

    @Override
    public Command initialize(String[] parts) {
        if (parts.length < 2) {
            throw new IllegalArgumentException("DepositCommand requires an amount.");
        }
        this.amount = Integer.parseInt(parts[1]);
        return this;
    }

    @Override
    public void execute() {
        customerManager.getCurrentCustomer().deposit(amount);
        System.out.println("Your balance is $" + customerManager.getCurrentCustomer().getCustomer().getBalance());
    }
}
