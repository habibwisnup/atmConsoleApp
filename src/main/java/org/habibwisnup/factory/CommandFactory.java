package org.habibwisnup.factory;

import org.habibwisnup.factory.commands.*;
import org.habibwisnup.managers.CustomerManager;
import org.habibwisnup.utils.MessageConstant;
import org.habibwisnup.utils.errorHandler.exceptions.GeneralException;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class CommandFactory {
    private final Map<String, Supplier<Command>> commandCollections = new HashMap<>();

    public CommandFactory(CustomerManager customerManager) {
        registerCommands(customerManager);
    }

    private void registerCommands(CustomerManager customerManager) {
        commandCollections.put(MessageConstant.LOGIN_COMMAND, () -> new LoginCommand(customerManager));
        commandCollections.put(MessageConstant.DEPOSIT_COMMAND, () -> new DepositCommand(customerManager));
        commandCollections.put(MessageConstant.WITHDRAW_COMMAND, () -> new WithdrawCommand(customerManager));
        commandCollections.put(MessageConstant.TRANSFER_COMMAND, () -> new TransferCommand(customerManager));
        commandCollections.put(MessageConstant.BALANCE_COMMAND, () -> new BalanceCommand(customerManager));
        commandCollections.put(MessageConstant.LOGOUT_COMMAND, () -> new LogoutCommand(customerManager));
    }

    public Command getCommand(String[] parts) {
        String commandKey = parts[0].toLowerCase();
        Supplier<Command> commandSupplier = commandCollections.get(commandKey);

        if (commandSupplier == null) {
            throw new GeneralException(MessageConstant.UNKNOWN_COMMAND_MESSAGE);
        }

        return commandSupplier.get().initialize(parts);
    }
}
