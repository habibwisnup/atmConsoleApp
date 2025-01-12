package org.habibwisnup.factory.commands;

import org.habibwisnup.factory.Command;
import org.habibwisnup.managers.CustomerManager;
import org.habibwisnup.utils.MessageConstant;
import org.habibwisnup.utils.errorHandler.exceptions.InvalidAmountException;

public class DepositCommand implements Command {
    private CustomerManager customerManager;
    private int amount;

    public DepositCommand(CustomerManager customerManager) {
        this.customerManager = customerManager;
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

    @Override
    public void execute() {
        var debts =customerManager.getCurrentCustomer().getCustomer().getDebtors();
        customerManager.getCurrentCustomer().deposit(amount);
        if(debts.isEmpty()) {
            System.out.println("Your balance is $" + customerManager.getCurrentCustomer().getCustomer().getBalance());
        }
        else {
            TransferCommand transferCommand = new TransferCommand(customerManager);
            var totalDebtPaid = 0;

            for (var kvp : debts.entrySet()) {
                String debtor = kvp.getKey();
                Integer debt = kvp.getValue();

                int amountToPay = Math.min(debt, amount - totalDebtPaid);

                var transferArgs = new String[]{
                        MessageConstant.TRANSFER_COMMAND, debtor, String.valueOf(amountToPay)
                };

                transferCommand.initialize(transferArgs).execute();
                totalDebtPaid += amountToPay;
            }
        }

        for (var kvp : debts.entrySet()) {
            if (kvp.getValue() == 0) {
                customerManager.getCurrentCustomer().getCustomer().getDebtors().remove(kvp.getKey());
            }
        }
    }
}
