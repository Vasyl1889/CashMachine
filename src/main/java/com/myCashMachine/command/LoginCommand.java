package com.myCashMachine.command;

import com.myCashMachine.CashMachine;
import com.myCashMachine.ConsoleHelper;
import com.myCashMachine.exception.InterruptOperationException;

import java.util.ResourceBundle;

/**
 * Created by Vasyl on 27.07.15.
 */
public class LoginCommand implements Command {
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login_en");

    @Override
    public void execute() throws InterruptOperationException
    {
        ConsoleHelper.writeMessage(res.getString("before"));
        ConsoleHelper.writeMessage(res.getString("specify.data"));
        while(true){
            String curentCartNumber = ConsoleHelper.readString();

            if(validCreditCards.containsKey(curentCartNumber)){
                while(true){
                    //ConsoleHelper.writeMessage("Please enter pinCode.");
                    String curentPinCode = ConsoleHelper.readString();

                    if(curentPinCode.equals(validCreditCards.getString(curentCartNumber))){
                        ConsoleHelper.writeMessage(String.format(res.getString("success.format"), curentCartNumber));
                            break;
                    } else {
                        ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), curentCartNumber));
                        ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                    }
                }
            } else {
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                continue;
            }
            break;
        }
    }
}
