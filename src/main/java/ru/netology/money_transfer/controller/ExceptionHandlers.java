package ru.netology.money_transfer.controller;

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
    public ResponseEntity<MsgAnswerException> handlerUOI(ForbiddenException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new MsgAnswerException(e.getMessage(), exceptionId.incrementAndGet()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MsgAnswerException> handlerCVE(MethodArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new MsgAnswerException("The form is filled out incorrectly ",
                        exceptionId.incrementAndGet()));
    }
}
