package org.habibwisnup.factory;

import org.habibwisnup.factory.commands.ExitCommand;
import org.habibwisnup.factory.commands.LoginCommand;
import org.habibwisnup.managers.CustomerManager;
import org.habibwisnup.utils.MessageConstant;
import org.habibwisnup.utils.errorHandler.exceptions.GeneralException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CommandFactoryTest {
    private CustomerManager customerManager;
    private CommandFactory commandFactory;

    @BeforeEach
    void setUp() {
        customerManager = mock(CustomerManager.class);
        commandFactory = new CommandFactory(customerManager);
    }

    @Test
    void testGetCommandWithEmptyInput() {
        String[] input = {};
        GeneralException exception = assertThrows(GeneralException.class, () -> {
            commandFactory.getCommand(input);
        });
        assertEquals(MessageConstant.NO_COMMAND_PROVIDED_MESSAGE + MessageConstant.HELP_COMMAND_MESSAGE, exception.getMessage());
    }

    @Test
    void testGetCommandWithUnknownCommand() {
        String[] input = {"unknown"};
        GeneralException exception = assertThrows(GeneralException.class, () -> {
            commandFactory.getCommand(input);
        });
        assertTrue(exception.getMessage().contains(MessageConstant.UNKNOWN_COMMAND_MESSAGE));
    }

    @Test
    void testGetCommandWithLoginRequiredWithoutLogin() {
        when(customerManager.isLoggedIn()).thenReturn(false);

        String[] input = {MessageConstant.DEPOSIT_COMMAND};
        GeneralException exception = assertThrows(GeneralException.class, () -> {
            commandFactory.getCommand(input);
        });

        assertEquals(MessageConstant.LOGIN_REQUIRED_MESSAGE, exception.getMessage());
    }

    @Test
    void testGetCommandWithLoginRequiredWithLogin() {
        when(customerManager.isLoggedIn()).thenReturn(true);

        String[] input = {MessageConstant.BALANCE_COMMAND};
        var command = commandFactory.getCommand(input);

        assertNotNull(command);
    }

    @Test
    void testGetCommandWithValidCommandAndArguments() {
        String[] input = {MessageConstant.LOGIN_COMMAND, "Alice"};
        var command = commandFactory.getCommand(input);

        assertNotNull(command);
        assertTrue(command instanceof LoginCommand);
    }

    @Test
    void testGetCommandWithInvalidArguments() {
        when(customerManager.isLoggedIn()).thenReturn(true);
        String[] input = {MessageConstant.TRANSFER_COMMAND, "Alice"};
        GeneralException exception = assertThrows(GeneralException.class, () -> {
            commandFactory.getCommand(input);
        });

        assertTrue(exception.getMessage().contains(MessageConstant.INVALID_ARGUMENT_MESSAGE));
    }

    @Test
    void testGetExitCommand() {
        String[] input = {MessageConstant.EXIT_COMMAND};
        var command = commandFactory.getCommand(input);

        assertNotNull(command);
        assertTrue(command instanceof ExitCommand);
    }
}