package org.habibwisnup.factory;

public class CommandInvoker {
    public void execute(Command command){
        command.execute();
    }
}
