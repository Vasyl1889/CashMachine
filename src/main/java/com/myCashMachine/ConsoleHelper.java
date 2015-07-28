package com.myCashMachine;

import com.myCashMachine.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

/**
 * Created by Vasyl on 27.07.15.
 */
public class ConsoleHelper {
    private static String code;
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "common_en");
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        String readline = null;
        try {
            readline = bufferedReader.readLine();
            if ("EXIT".equalsIgnoreCase(readline)) {
                ConsoleHelper.writeMessage(res.getString("the.end"));
                throw new InterruptOperationException();
            }
        } catch (IOException ignored) {
        }

        return readline;
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("choose.currency.code"));
        while ((code = readString().trim()).length() != 3) {
            ConsoleHelper.writeMessage(res.getString("invalid.data"));
        }

        return code.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        String[] argArray = new String[2];

        boolean flag = false;
        ConsoleHelper.writeMessage(String.format(res.getString("choose.denomination.and.count.format"), code));

        while (!flag) {
            String inputLine = readString();
            argArray = inputLine.split(" ");

            if (argArray.length != 2 || Integer.parseInt(argArray[0]) <= 0 || Integer.parseInt(argArray[1]) <= 0) {
                ConsoleHelper.writeMessage(res.getString("invalid.data"));
            } else {
                flag = true;
            }
        }

        return argArray;
    }

    public static Operation askOperation() throws InterruptOperationException {
        String numberOperation;
        Operation operation = null;
        ConsoleHelper.writeMessage(res.getString("choose.operation"));
        writeMessage(String.format("%s - %d, %s - %d, %s - %d, %s - %d",
                res.getString("operation.INFO"), Operation.INFO.ordinal(),
                res.getString("operation.DEPOSIT"), Operation.DEPOSIT.ordinal(),
                res.getString("operation.WITHDRAW"), Operation.WITHDRAW.ordinal(),
                res.getString("operation.EXIT"), Operation.EXIT.ordinal()));
        while (true) {
            try {
                numberOperation = readString();
                operation = Operation.getAllowableOperationByOrdinal(Integer.valueOf(numberOperation));
                break;
            } catch (IllegalArgumentException exeption) {
                ConsoleHelper.writeMessage(res.getString("invalid.data"));
            }
        }

        return operation;
    }

    public static void printExitMessage() {
        ConsoleHelper.writeMessage(res.getString("the.end"));
    }
}
