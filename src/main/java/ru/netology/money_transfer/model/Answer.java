package ru.netology.money_transfer.model;

public class Answer {
    private String operationId;

    public Answer() {
    }

    public Answer(String operationId) {
        this.operationId = operationId;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }
}
