package com.vratsel.payment.tracker;

/**
 * Created by Vladimir on 12.03.2017.
 */
public class Payment {
    private int amount;
    private String currency;

    public Payment(String currency, int amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment)) return false;

        Payment payment = (Payment) o;

        if (getAmount() != payment.getAmount()) return false;
        return currency.equals(payment.currency);

    }

    @Override
    public int hashCode() {
        int result = getAmount();
        result = 31 * result + currency.hashCode();
        return result;
    }
}
