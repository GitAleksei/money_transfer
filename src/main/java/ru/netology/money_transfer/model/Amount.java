package ru.netology.money_transfer.model;

import javax.validation.constraints.Min;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amount amount = (Amount) o;
        return value == amount.value && currency.equals(amount.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, currency);
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
