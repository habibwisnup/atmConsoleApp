package org.habibwisnup.factory.commands;

import org.habibwisnup.factory.Command;
import org.habibwisnup.managers.CustomerManager;
import org.habibwisnup.utils.MessageConstant;
import org.habibwisnup.utils.errorHandler.exceptions.InvalidAmountException;

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
        if (parts.length < 2)
            throw new IllegalArgumentException(MessageConstant.INSUFFICIENT_ARGUMENT + MessageConstant.HELP_COMMAND_MESSAGE);

        try {
            this.amount = Integer.parseInt(parts[1]);
            if (amount <= 0)
                throw new InvalidAmountException(MessageConstant.INVALID_AMOUNT_MESSAGE);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(MessageConstant.NUMERIC_VALIDATION_MESSAGE);
        }

        return this;
    }
}
