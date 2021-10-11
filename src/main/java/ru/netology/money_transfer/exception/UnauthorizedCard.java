package ru.netology.money_transfer.exception;

public class UnauthorizedCard extends RuntimeException {
    public UnauthorizedCard(String message) {
        super(message);
    }
}
