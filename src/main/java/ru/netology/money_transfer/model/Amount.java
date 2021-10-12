package ru.netology.money_transfer.model;

public class Amount {
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
        return "Amount{" +
                "value=" + value +
                ", currency=" + currency +
                '}';
    }
}
