package ru.netology.money_transfer.model.msg;

public class MsgAnswerException {
    private String message;
    private int id;

    public MsgAnswerException() {
    }

    public MsgAnswerException(String message, int id) {
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
