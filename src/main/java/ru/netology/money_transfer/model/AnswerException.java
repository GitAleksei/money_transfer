package ru.netology.money_transfer.model;

public class AnswerException {
    private String message;
    private int id;

    public AnswerException() {
    }

    public AnswerException(String message, int id) {
        this.message = message;
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
