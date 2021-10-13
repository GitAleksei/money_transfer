package ru.netology.money_transfer.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnauthorizedOperationId extends RuntimeException {
    public static final Logger LOGGER = LoggerFactory.getLogger(ForbiddenException.class);

    public UnauthorizedOperationId(String message) {
        super(message);
        LOGGER.error(message);
    }
}
