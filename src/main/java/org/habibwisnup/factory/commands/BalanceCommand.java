package org.habibwisnup.factory.commands;

import org.habibwisnup.factory.Command;
import org.habibwisnup.managers.CustomerManager;

public class BalanceCommand implements Command {
    private final CustomerManager customerManager;

    public BalanceCommand(CustomerManager customerManager) {
        this.customerManager = customerManager;
    }

    @Override
    public void execute() {
        var customer = customerManager.getCurrentCustomer();
        System.out.println("Your balance is $" + customer.getCustomer().getBalance());
        if (customer.getCustomer().getDebt() > 0) {
            System.out.println("Owed $" + customer.getCustomer().getDebt());
        }
    }

    @Override
    public Command initialize(String[] parts) {
        return this;
    }
}
