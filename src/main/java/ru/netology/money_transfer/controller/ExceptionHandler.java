package ru.netology.money_transfer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {
    public ResponseEntity<String> handler() {
        return null;
    }
}
