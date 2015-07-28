package com.myCashMachine.command;

import com.myCashMachine.CashMachine;
import com.myCashMachine.ConsoleHelper;
import com.myCashMachine.exception.InterruptOperationException;

import java.util.ResourceBundle;

/**
 * Created by Vasyl on 27.07.15.
 */
class ExitCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "exit_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("exit.question.y.n"));
        String answer = ConsoleHelper.readString();
        if (res.getString("yes").equalsIgnoreCase(answer.trim())) {
            ConsoleHelper.writeMessage(res.getString("thank.message"));
            CommandExecutor.exitFlag = true;
        } else {
        }
    }
}
