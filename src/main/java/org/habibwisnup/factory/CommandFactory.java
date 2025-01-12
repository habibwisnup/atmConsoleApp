package org.habibwisnup.factory;

import org.habibwisnup.factory.commands.*;
import org.habibwisnup.managers.CustomerManager;
import org.habibwisnup.utils.MessageConstant;
import org.habibwisnup.utils.errorHandler.exceptions.GeneralException;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private final Map<String, CommandMetadata> commandCollections = new HashMap<>();
    private final CustomerManager customerManager;

    public CommandFactory(CustomerManager customerManager) {
        this.customerManager = customerManager;
        registerCommands(customerManager);
    }

    private void registerCommands(CustomerManager customerManager) {
        commandCollections.put(MessageConstant.LOGIN_COMMAND, new CommandMetadata(() -> new LoginCommand(customerManager), false));
        commandCollections.put(MessageConstant.DEPOSIT_COMMAND, new CommandMetadata(() -> new DepositCommand(customerManager), true));
        commandCollections.put(MessageConstant.WITHDRAW_COMMAND, new CommandMetadata(() -> new WithdrawCommand(customerManager), true));
        commandCollections.put(MessageConstant.TRANSFER_COMMAND, new CommandMetadata(() -> new TransferCommand(customerManager), true));
        commandCollections.put(MessageConstant.BALANCE_COMMAND, new CommandMetadata(() -> new BalanceCommand(customerManager), true));
        commandCollections.put(MessageConstant.LOGOUT_COMMAND, new CommandMetadata(() -> new LogoutCommand(customerManager), true));
        commandCollections.put(MessageConstant.EXIT_COMMAND, new CommandMetadata(ExitCommand::new, false));
    }

    public Command getCommand(String[] parts) {
        if (parts.length == 0 || parts[0].isEmpty()) {
            throw new GeneralException(MessageConstant.NO_COMMAND_PROVIDED_MESSAGE + MessageConstant.HELP_COMMAND_MESSAGE);
        }

        var commandKey = parts[0].toLowerCase();
        var commandMetadata = commandCollections.get(commandKey);

        if (commandMetadata == null) {
            throw new GeneralException(MessageConstant.UNKNOWN_COMMAND_MESSAGE + commandKey+" "+MessageConstant.HELP_COMMAND_MESSAGE);
        }

        if (commandMetadata.requiresLogin && !customerManager.isLoggedIn()) {
            throw new GeneralException(MessageConstant.LOGIN_REQUIRED_MESSAGE);
        }

        try {
            return commandMetadata.commandSupplier.get().initialize(parts);
        } catch (Exception e) {
            throw new GeneralException(MessageConstant.INVALID_ARGUMENT_MESSAGE + commandKey + " : " + e.getMessage());
        }
    }
}
