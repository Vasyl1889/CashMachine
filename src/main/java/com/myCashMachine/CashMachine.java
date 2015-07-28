package com.myCashMachine;

import com.myCashMachine.command.CommandExecutor;
import com.myCashMachine.exception.InterruptOperationException;

import java.io.IOException;
import java.util.Locale;

/**
 * Created by Vasyl on 27.07.15.
 */
public class CashMachine {
    public static final String RESOURCE_PATH = "";

    public static void main(String[] args) throws IOException {
        Locale.setDefault(Locale.ENGLISH);
        Operation operation;

        try {
            CommandExecutor.execute(Operation.LOGIN);
            do {
                operation = ConsoleHelper.askOperation();
                CommandExecutor.execute(operation);
            } while ((!(operation.equals(Operation.EXIT)) || (!CommandExecutor.exitFlag)));

        } catch (InterruptOperationException exception) {
            ConsoleHelper.printExitMessage();
        }
    }
}
