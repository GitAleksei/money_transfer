package ru.netology.money_transfer.model.msg;

import ru.netology.money_transfer.model.Amount;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class MsgTransfer {
    @Pattern(regexp = "[0-9]{16}")
    private String cardFromNumber;
    @Pattern(regexp = "[0-9]{2}[/][0-9]{2}")
    private String cardFromValidTill;
    @Pattern(regexp = "[0-9]{3}")
    private String cardFromCVV;
    @Pattern(regexp = "[0-9]{16}")
    private String cardToNumber;
    private Amount amount;

    public MsgTransfer() {
    }

    public String getCardFromNumber() {
        return cardFromNumber;
    }

    public void setCardFromNumber(String cardFromNumber) {
        this.cardFromNumber = cardFromNumber;
    }

    public String getCardFromValidTill() {
        return cardFromValidTill;
    }

    public void setCardFromValidTill(String cardFromValidTill) {
        this.cardFromValidTill = cardFromValidTill;
    }

    public String getCardFromCVV() {
        return cardFromCVV;
    }

    public void setCardFromCVV(String cardFromCVV) {
        this.cardFromCVV = cardFromCVV;
    }

    public String getCardToNumber() {
        return cardToNumber;
    }

    public void setCardToNumber(String cardToNumber) {
        this.cardToNumber = cardToNumber;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Message{" +
                "cardFromNumber='" + cardFromNumber + '\'' +
                ", cardFromValidTill='" + cardFromValidTill + '\'' +
                ", cardFromCVV='" + cardFromCVV + '\'' +
                ", cardToNumber='" + cardToNumber + '\'' +
                ", amount=" + amount +
                '}';
    }
}
