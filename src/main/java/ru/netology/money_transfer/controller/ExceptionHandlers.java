package ru.netology.money_transfer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.netology.money_transfer.exception.ForbiddenException;
import ru.netology.money_transfer.exception.UnauthorizedCard;
import ru.netology.money_transfer.exception.UnauthorizedOperationId;
import ru.netology.money_transfer.model.msg.MsgAnswerException;

import java.util.concurrent.atomic.AtomicInteger;

@RestControllerAdvice
public class ExceptionHandlers {
    public static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlers.class);

    private final AtomicInteger exceptionId = new AtomicInteger();

    @ExceptionHandler(UnauthorizedCard.class)
    public ResponseEntity<MsgAnswerException> handlerUC(UnauthorizedCard e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new MsgAnswerException(e.getMessage(), exceptionId.incrementAndGet()));
    }

    @ExceptionHandler(UnauthorizedOperationId.class)
    public ResponseEntity<MsgAnswerException> handlerUOI(UnauthorizedOperationId e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new MsgAnswerException(e.getMessage(), exceptionId.incrementAndGet()));
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<MsgAnswerException> handlerFE(ForbiddenException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new MsgAnswerException(e.getMessage(), exceptionId.incrementAndGet()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MsgAnswerException> handlerCVE(MethodArgumentNotValidException e) {
        var msg = new MsgAnswerException("The form is filled out incorrectly",
                exceptionId.incrementAndGet());
        LOGGER.error(msg.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<MsgAnswerException> handlerCVE(RuntimeException e) {
        var msg = new MsgAnswerException("Internal server error",
                exceptionId.incrementAndGet());
        LOGGER.error(msg.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg);
    }
}
