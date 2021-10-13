package ru.netology.money_transfer.model;

import javax.validation.constraints.Min;

public class Amount {
    @Min(0)
    private int value;
    private String currency;

    public Amount() {
    }

    public Amount(int value, String currency) {
        this.value = value;
        this.currency = currency;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void increment(Amount amount) {
        value = value + amount.value;
    }

    public Amount negative() {
        return new Amount(-value, currency);
    }

    @Override
    public String toString() {
        return String.format("%.2f%s", value / 100.0, currency);
    }
}
