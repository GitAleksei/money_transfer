package ru.netology.money_transfer.model.msg;

public class MsgAnswer {
    private String operationId;

    public MsgAnswer() {
    }

    public MsgAnswer(String operationId) {
        this.operationId = operationId;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }
}
