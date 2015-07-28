package com.myCashMachine;

import com.myCashMachine.exception.NotEnoughMoneyException;

import java.util.*;


/**
 * Created by Vasyl on 27.07.15.
 */
public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations = new TreeMap<Integer, Integer>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return (o1 > o2) ? -1 : ((o1 == o2) ? 0 : 1);
        }
    });

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public void addAmount(int denomination, int count) {
        if (denominations.containsKey(denomination)) {
            denominations.put(denomination, denominations.get(denomination) + count);
        } else {
            denominations.put(denomination, count);
        }
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public int getTotalAmount() {
        int summa = 0;
        for (Map.Entry<Integer, Integer> pair : denominations.entrySet()) {
            summa = summa + (pair.getKey() * pair.getValue());
        }
        return summa;
    }

    public boolean hasMoney() {
        return denominations.size() != 0;
    }

    public boolean isAmountAvailable(int expectedAmount) {
        return expectedAmount <= getTotalAmount();
    }


    /**
     * method withdrawAmount
     */
    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        Map<Integer, Integer> copyOfDenominations = new TreeMap<Integer, Integer>(denominations);
        int withdrawMoney = expectedAmount;
        Map<Integer, Integer> result = new LinkedHashMap();

        Iterator<Map.Entry<Integer, Integer>> iterator = denominations.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> x = iterator.next();

            int currentBanknote = x.getKey();
            int remainingBanknotes = x.getValue();
            int countOfCurrentBanknotes = 0;

            while ((remainingBanknotes > 0) && ((withdrawMoney - currentBanknote) >= 0)) {
                withdrawMoney -= currentBanknote;
                remainingBanknotes--;

                if (remainingBanknotes == 0) {
                    iterator.remove();
                } else x.setValue(remainingBanknotes);
                countOfCurrentBanknotes++;
            }

            if (countOfCurrentBanknotes > 0) {
                result.put(currentBanknote, countOfCurrentBanknotes);
            }
        }

        if ((withdrawMoney > 0) && (withdrawMoney <= getTotalAmount())) {
            denominations.clear();
            denominations.putAll(copyOfDenominations);
            throw new NotEnoughMoneyException();
        }
        copyOfDenominations.clear();
        return result;
    }
}
