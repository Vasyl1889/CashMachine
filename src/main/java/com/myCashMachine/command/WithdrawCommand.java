package com.myCashMachine.command;

import com.myCashMachine.CashMachine;
import com.myCashMachine.ConsoleHelper;
import com.myCashMachine.CurrencyManipulator;
import com.myCashMachine.CurrencyManipulatorFactory;
import com.myCashMachine.exception.InterruptOperationException;
import com.myCashMachine.exception.NotEnoughMoneyException;

import java.util.ConcurrentModificationException;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by Vasyl on 27.07.15.
 */
class WithdrawCommand   implements Command{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "withdraw_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        try {
            CurrencyManipulator currencyManipulator= CurrencyManipulatorFactory.getManipulatorByCurrencyCode(ConsoleHelper.askCurrencyCode());

            int amountToWithdraw;
            while (true) {
                try {
                    ConsoleHelper.writeMessage(res.getString("specify.amount"));
                    amountToWithdraw=Integer.parseInt(ConsoleHelper.readString());
                    if(amountToWithdraw<=0)
                        throw new NumberFormatException();

                    if (currencyManipulator.isAmountAvailable(amountToWithdraw)) {

                        Map<Integer, Integer> withDrawMoney =  currencyManipulator.withdrawAmount(amountToWithdraw);
                        for(Map.Entry<Integer,Integer> pair: withDrawMoney.entrySet()){
                            ConsoleHelper.writeMessage("\t" + pair.getKey() + " - " + pair.getValue());
                            ConsoleHelper.writeMessage(String.format(res.getString("success.format"), pair.getKey(),pair.getValue()));
                        }
                        break;
                    }
                    else
                    {
                        throw new NotEnoughMoneyException();
                    }


                } catch (NumberFormatException e) {
                    ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                }
                catch (NotEnoughMoneyException e) {
                    ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                }
            }
        } catch (ConcurrentModificationException e) {
            ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
        }
    }
}
