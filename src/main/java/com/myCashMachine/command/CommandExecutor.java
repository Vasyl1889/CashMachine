package com.myCashMachine.command;

import com.myCashMachine.Operation;
import com.myCashMachine.exception.InterruptOperationException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vasyl on 27.07.15.
 */
public class CommandExecutor {
    private static Map<Operation, Command> commandMap = new HashMap<>();
    public static Boolean exitFlag = false;
    static {
        commandMap.put(Operation.LOGIN, new LoginCommand());
        commandMap.put(Operation.INFO, new InfoCommand());
        commandMap.put(Operation.DEPOSIT, new DepositCommand());
        commandMap.put(Operation.WITHDRAW, new WithdrawCommand());
        commandMap.put(Operation.EXIT, new ExitCommand());
    }
    private CommandExecutor() {

    }
    public static final void execute(Operation operation) throws InterruptOperationException
    {
        commandMap.get(operation).execute();
    }
}
