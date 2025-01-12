package org.habibwisnup.factory.commands;

import org.habibwisnup.factory.Command;

public class ExitCommand implements Command {
    @Override
    public void execute() {
        System.out.println("Thank you for using the HABIB ATM. Goodbye!");
        System.exit(0);
    }

    @Override
    public Command initialize(String[] parts) {
        return this;
    }
}
