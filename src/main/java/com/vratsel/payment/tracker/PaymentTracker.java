package com.vratsel.payment.tracker;

import java.util.HashMap;
import java.util.Map;

import static com.vratsel.payment.tracker.util.Helper.*;
/**
 * Created by Vladimir on 12.03.2017.
 */
public class PaymentTracker {
    private HashMap<String, Integer> balance = new HashMap<>();

    public void addPayment(Payment payment) {
        if (payment != null)
         addPayment(payment.getCurrency(), payment.getAmount());
    }

    public synchronized void addPayment(String currency, Integer amount) {
        Integer oldAmount = getBalance(currency);
        balance.put(currency, oldAmount == null ? amount : amount + oldAmount);
    }

    Integer getBalance(String currency) {
        return balance.get(currency);
    }

    Map<String, Integer> getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        synchronized (this) {
            final StringBuilder output = new StringBuilder(BALANCE + ":\n");
                    balance.entrySet().stream().
                            forEach(entry -> output.append(entry.getValue() != 0 ? entry.getKey() + " " + entry.getValue() + "\n" : ""));
            return  output.toString();
        }
    }
}
