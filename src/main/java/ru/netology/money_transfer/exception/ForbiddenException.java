package ru.netology.money_transfer.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ForbiddenException extends RuntimeException {
    public static final Logger LOGGER = LoggerFactory.getLogger(ForbiddenException.class);

    public ForbiddenException(String message) {
        super(message);
        LOGGER.error(message);
    }
}
