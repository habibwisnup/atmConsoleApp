package org.habibwisnup.factory.commands;

import org.habibwisnup.factory.Command;
import org.habibwisnup.managers.CustomerManager;
import org.habibwisnup.utils.MessageConstant;

public class LoginCommand implements Command {

    private final CustomerManager customerManager;
    private String customerName;

    public LoginCommand(CustomerManager customerManager) {
        this.customerManager = customerManager;
    }

    @Override
    public void execute() {
        customerManager.login(customerName);
    }

    @Override
    public Command initialize(String[] parts) {
        if (parts.length < 2)
            throw new IllegalArgumentException(MessageConstant.INSUFFICIENT_ARGUMENT + MessageConstant.HELP_COMMAND_MESSAGE);

        this.customerName = parts[1];
        return this;
    }
}
