package ru.netology.money_transfer.model.msg;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MsgAnswerException that = (MsgAnswerException) o;
        return id == that.id && message.equals(that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, id);
    }

    @Override
    public String toString() {
        return "MsgAnswerException{" +
                "message='" + message + '\'' +
                ", id=" + id +
                '}';
    }
}
