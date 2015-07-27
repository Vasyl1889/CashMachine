package com.myCashMachine.command;

import com.myCashMachine.exception.InterruptOperationException;

/**
 * Created by Vasyl on 27.07.15.
 */
interface Command {
    public void execute() throws InterruptOperationException;
}
