package ru.netology.money_transfer.model.msg;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MsgAnswer msgAnswer = (MsgAnswer) o;
        return operationId.equals(msgAnswer.operationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operationId);
    }
}
