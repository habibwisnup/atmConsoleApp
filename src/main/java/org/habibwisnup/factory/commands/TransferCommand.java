package org.habibwisnup.factory.commands;

import org.habibwisnup.factory.Command;
import org.habibwisnup.managers.CustomerManager;
import org.habibwisnup.utils.errorHandler.ErrorHandler;

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

            var senderBalance = sender.getCustomer().getBalance();
            var transferableAmount = Math.min(amount, senderBalance);
            var debtAmount = amount - transferableAmount;

            int senderDebtToReceiver = receiver.getCustomer().getDebtToPay(sender.getCustomer().getName());
            int amountToRepayDebt = Math.min(transferableAmount, senderDebtToReceiver);

            if (amountToRepayDebt > 0) {
                receiver.reduceDebt(amountToRepayDebt);
                receiver.getCustomer().reduceDebtFromCustomer(sender.getCustomer().getName(), amountToRepayDebt);

                transferableAmount -= amountToRepayDebt;
                System.out.println("Repaid $" + amountToRepayDebt + " of debt to " + receiver.getCustomer().getName());
            }

            if (transferableAmount > 0) {
                sender.withdraw(transferableAmount);
                receiver.deposit(transferableAmount);
                System.out.println("Transferred $" + transferableAmount + " to " + receiver.getCustomer().getName());
            }

            if (debtAmount > 0) {
                sender.addDebt(debtAmount);
                receiver.addDebt(-debtAmount);
                sender.getCustomer().addDebtFromCustomer(receiver.getCustomer().getName(), debtAmount);
                sender.getCustomer().displayDebts( receiver.getCustomer().getName());
            }
            if(sender.getCustomer().getDebtToPay(receiver.getCustomer().getName()) > 0 && !(debtAmount>0)) {
                sender.reduceDebt(amount);
                receiver.reduceDebt(amount);
                sender.getCustomer().reduceDebtFromCustomer(receiver.getCustomer().getName(), amount);
                sender.getCustomer().displayDebts( receiver.getCustomer().getName());
            }

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
