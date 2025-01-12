package org.habibwisnup.factory;

import java.util.function.Supplier;

public class CommandMetadata {
    protected final Supplier<Command> commandSupplier;
    protected final boolean requiresLogin;

    public CommandMetadata(Supplier<Command> commandSupplier, boolean requiresLogin) {
        this.commandSupplier = commandSupplier;
        this.requiresLogin = requiresLogin;
    }
}
