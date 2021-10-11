package ru.netology.money_transfer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.netology.money_transfer.exception.UnauthorizedCard;
import ru.netology.money_transfer.model.AnswerException;

@RestControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(UnauthorizedCard.class)
    public ResponseEntity<AnswerException> handlerUC(UnauthorizedCard e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new AnswerException(e.getMessage(), 1));
    }
}
