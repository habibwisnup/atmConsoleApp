package org.habibwisnup.factory;

public interface Command {
    void execute();
    Command initialize(String[] parts);
}
