package com.myCashMachine.command;

import com.myCashMachine.CashMachine;
import com.myCashMachine.ConsoleHelper;
import com.myCashMachine.CurrencyManipulator;
import com.myCashMachine.CurrencyManipulatorFactory;
import com.myCashMachine.exception.InterruptOperationException;

import java.util.ResourceBundle;

/**
 * Created by Vasyl on 27.07.15.
 */
class DepositCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "deposit_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String code = ConsoleHelper.askCurrencyCode();

        while (true) {
            try {
                String[] cash = ConsoleHelper.getValidTwoDigits(code);

                Integer nominal = Integer.parseInt(cash[0]);
                Integer amount = Integer.parseInt(cash[1]);

                CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
                currencyManipulator.addAmount(nominal, amount);
                ConsoleHelper.writeMessage(String.format(res.getString("success.format"), (nominal * amount), code));

                break;
            } catch (NumberFormatException e) {
                ConsoleHelper.writeMessage(res.getString("invalid.data"));
            }
        }
    }
}
