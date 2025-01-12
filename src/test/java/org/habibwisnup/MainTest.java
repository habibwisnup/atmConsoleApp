package org.habibwisnup;


import org.habibwisnup.factory.Command;
import org.habibwisnup.factory.CommandFactory;
import org.habibwisnup.factory.CommandInvoker;
import org.habibwisnup.managers.CustomerManager;
import org.habibwisnup.utils.errorHandler.exceptions.GeneralException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    private CustomerManager customerManager;
    private CommandFactory commandFactory;
    private CommandInvoker commandInvoker;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        customerManager = new CustomerManager();
        commandFactory = new CommandFactory(customerManager);
        commandInvoker = new CommandInvoker();
    }

    @Test
    public void testLoginCommand() {
        String[] loginCommand = {"login", "Alice"};
        Command login = commandFactory.getCommand(loginCommand);

        commandInvoker.execute(login);

        assertNotNull(customerManager.getCurrentCustomer(), "Customer should be logged in.");
        assertEquals("Alice", customerManager.getCurrentCustomer().getCustomer().getName());
    }

    @Test
    public void testDepositCommand() {
        String[] loginCommand = {"login", "Alice"};
        Command login = commandFactory.getCommand(loginCommand);
        commandInvoker.execute(login);

        String[] depositCommand = {"deposit", "100"};
        Command deposit = commandFactory.getCommand(depositCommand);

        commandInvoker.execute(deposit);

        assertNotNull(customerManager.getCurrentCustomer(), "Customer should be logged in.");
        assertEquals(100, customerManager.getCurrentCustomer().getCustomer().getBalance());
    }

    @Test
    public void testBalanceCommand() {
        String[] loginCommand = {"login", "Alice"};
        Command login = commandFactory.getCommand(loginCommand);
        commandInvoker.execute(login);

        String[] depositCommand = {"deposit", "100"};
        Command deposit = commandFactory.getCommand(depositCommand);
        commandInvoker.execute(deposit);

        String[] balanceCommand = {"balance"};
        Command balance = commandFactory.getCommand(balanceCommand);

        commandInvoker.execute(balance);

        assertNotNull(customerManager.getCurrentCustomer());
        assertEquals(100, customerManager.getCurrentCustomer().getCustomer().getBalance());
    }

    @Test
    public void testTransferCommand() {
        var command = new String[]{"login", "Bob"};
        var login = commandFactory.getCommand(command);
        commandInvoker.execute(login);

        command = new String[]{"deposit", "100"};
        var deposit = commandFactory.getCommand(command);
        commandInvoker.execute(deposit);

        command = new String[]{"logout"};
        var logout = commandFactory.getCommand(command);
        commandInvoker.execute(logout);

        command = new String[]{"login", "Alice"};
        var login2 = commandFactory.getCommand(command);
        commandInvoker.execute(login2);

        command = new String[]{"deposit", "100"};
        var deposit2 = commandFactory.getCommand(command);
        commandInvoker.execute(deposit2);


        command = new String[]{"transfer", "Bob", "50"};
        var transferCommand = commandFactory.getCommand(command);
        commandInvoker.execute(transferCommand);

        var sender = customerManager.getCustomer("Alice");
        var receiver = customerManager.getCustomer("Bob");

        assertNotNull(sender, "Sender should exist.");
        assertNotNull(receiver, "Receiver should exist.");

        assertEquals(50, sender.getCustomer().getBalance());
        assertEquals(150, receiver.getCustomer().getBalance());

        assertEquals(0, sender.getCustomer().getDebtToPay("Bob"));
        assertEquals(0, receiver.getCustomer().getDebtToPay("Alice"));
    }

    @Test
    public void testTransferCommandWithDebt() {
        var command = new String[]{"login", "Bob"};
        var login = commandFactory.getCommand(command);
        commandInvoker.execute(login);

        command = new String[]{"deposit", "100"};
        var deposit = commandFactory.getCommand(command);
        commandInvoker.execute(deposit);

        command = new String[]{"logout"};
        var logout = commandFactory.getCommand(command);
        commandInvoker.execute(logout);

        command = new String[]{"login", "Alice"};
        var login2 = commandFactory.getCommand(command);
        commandInvoker.execute(login2);

        command = new String[]{"deposit", "100"};
        var deposit2 = commandFactory.getCommand(command);
        commandInvoker.execute(deposit2);


        command = new String[]{"transfer", "Bob", "500"};
        var transferCommand = commandFactory.getCommand(command);
        commandInvoker.execute(transferCommand);

        var sender = customerManager.getCustomer("Alice");
        var receiver = customerManager.getCustomer("Bob");

        assertNotNull(sender, "Sender should exist.");
        assertNotNull(receiver, "Receiver should exist.");

        assertEquals(0, sender.getCustomer().getBalance());
        assertEquals(200, receiver.getCustomer().getBalance());

        assertEquals(400, sender.getCustomer().getDebtToPay("Bob"));
        assertEquals(0, receiver.getCustomer().getDebtToPay("Alice"));
    }

    @Test
    public void testHelpCommand() {
        String[] helpCommand = {"help"};
        Command help = commandFactory.getCommand(helpCommand);
        commandInvoker.execute(help);
    }

    @Test
    public void testExceptionHandling() {
        String[] invalidCommand = {"invalid_command"};

        Exception exception = assertThrows(GeneralException.class, () -> {
            Command command = commandFactory.getCommand(invalidCommand);
            commandInvoker.execute(command);
        });

        assertEquals(
                "Unknown Command: invalid_command Type 'help' for a list of available commands.",
                exception.getMessage()
        );
    }
}