package com.myCashMachine;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vasyl on 27.07.15.
 */
public class CurrencyManipulatorFactory {
    private static Map<String, CurrencyManipulator> currencyManipulators = new HashMap<String, CurrencyManipulator>();

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode) {
        if (currencyManipulators.containsKey(currencyCode)) {
            return currencyManipulators.get(currencyCode);
        } else {
            CurrencyManipulator currentManipulator = new CurrencyManipulator(currencyCode);
            currencyManipulators.put(currencyCode, currentManipulator);

            return currentManipulator;
        }
    }

    private CurrencyManipulatorFactory() {
    }

    public static Collection getAllCurrencyManipulators() {
        return currencyManipulators.values();
    }
}
