package org.habibwisnup.factory.commands;

import org.habibwisnup.factory.Command;
import org.habibwisnup.managers.CustomerManager;
import org.habibwisnup.utils.errorHandler.ErrorHandler;
import org.habibwisnup.utils.errorHandler.exceptions.GeneralException;

public class TransferCommand implements Command {

    private final CustomerManager customerManager;
    private String targetName;
    private int amount;

    public TransferCommand(CustomerManager customerManager) {
        this.customerManager = customerManager;
    }
    @Override
    public void execute() {
        try {
            var sender = customerManager.getCurrentCustomer();
            var receiver = customerManager.getCustomer(targetName);

            if (receiver == null) {
                System.out.println("Target customer does not exist.");
                return;
            }

            sender.withdraw(amount);
            receiver.deposit(amount);

            System.out.println("Transferred $" + amount + " to " + targetName);
            System.out.println("Your balance is $" + sender.getCustomer().getBalance());
        } catch (Exception e) {
            ErrorHandler.handleException(e);
        }
    }

    @Override
    public Command initialize(String[] parts) {
        this.targetName = parts[1];
        this.amount = Integer.parseInt(parts[2]);
        return this;
    }
}
