package ru.netology.money_transfer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.netology.money_transfer.exception.UnauthorizedCard;
import ru.netology.money_transfer.exception.UnauthorizedOperationId;
import ru.netology.money_transfer.model.MsgAnswerException;

import java.util.concurrent.atomic.AtomicInteger;

@RestControllerAdvice
public class ExceptionHandlers {
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
}
