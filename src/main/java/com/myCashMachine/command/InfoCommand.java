package com.myCashMachine.command;

import com.myCashMachine.CashMachine;
import com.myCashMachine.ConsoleHelper;
import com.myCashMachine.CurrencyManipulator;
import com.myCashMachine.CurrencyManipulatorFactory;

import java.util.Collection;
import java.util.ResourceBundle;

/**
 * Created by Vasyl on 27.07.15.
 */
class InfoCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "info_en");
    @Override
    public void execute() {
        ConsoleHelper.writeMessage(res.getString("before"));
        Collection<CurrencyManipulator> listCurrencyManipulators = CurrencyManipulatorFactory.getAllCurrencyManipulators();

        if(listCurrencyManipulators.isEmpty())
            ConsoleHelper.writeMessage(res.getString("no.money"));

            for(CurrencyManipulator cm: listCurrencyManipulators ){
                if(cm.hasMoney()){
                ConsoleHelper.writeMessage(cm.getCurrencyCode() + " " + cm.getTotalAmount());
                } else{
                    ConsoleHelper.writeMessage(res.getString("no.money"));
                }
            }

    }
}
