package org.habibwisnup.factory.commands;

import org.habibwisnup.factory.Command;
import org.habibwisnup.managers.CustomerManager;

public class LogoutCommand implements Command {
    private final CustomerManager customerManager;

    public LogoutCommand(CustomerManager customerManager) {
        this.customerManager = customerManager;
    }

    @Override
    public void execute() {
        customerManager.logout();
    }

    @Override
    public Command initialize(String[] parts) {
        return this;
    }
}
