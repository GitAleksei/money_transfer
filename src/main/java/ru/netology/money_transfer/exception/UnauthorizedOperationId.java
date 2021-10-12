package ru.netology.money_transfer.exception;

public class UnauthorizedOperationId extends RuntimeException {
    public UnauthorizedOperationId(String message) {
        super(message);
    }
}
