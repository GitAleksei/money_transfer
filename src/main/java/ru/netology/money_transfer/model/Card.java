package ru.netology.money_transfer.model;

public class Card {
    private String number;
    private String validTill;
    private String cvv;
    private Amount amount;
    private String code;

    public Card() {
    }

    public Card(String number, String validTill, String cvv, Amount amount, String code) {
        this.number = number;
        this.validTill = validTill;
        this.cvv = cvv;
        this.amount = amount;
        this.code = code;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getValidTill() {
        return validTill;
    }

    public void setValidTill(String validTill) {
        this.validTill = validTill;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
