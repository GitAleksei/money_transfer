package ru.netology.money_transfer.model;

public class Amount {
    private int value;
    private int string;

    public Amount() {
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getString() {
        return string;
    }

    public void setString(int string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return "Amount{" +
                "value=" + value +
                ", string=" + string +
                '}';
    }
}
