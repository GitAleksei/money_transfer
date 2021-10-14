package ru.netology.money_transfer.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.netology.money_transfer.model.Amount;
import ru.netology.money_transfer.model.msg.MsgAnswer;
import ru.netology.money_transfer.model.msg.MsgConfirmOperation;
import ru.netology.money_transfer.model.msg.MsgTransfer;

@SpringBootTest
class TransferServiceTest {
    private static final String OPERATION_ID = "1";
    private static final String CARD_FROM_NUMBER = "1111111111111111";
    private static final String CARD_FROM_CVV = "111";
    private static final String CARD_FROM_VALID_TILL = "11/22";
    private static final String CARD_TO_NUMBER = "2222222222222222";
    private static final String CODE = "0000";
    private static final int AMOUNT_VALUE = 10000;
    private static final String AMOUNT_CURRENCY = "RUR";

    @Autowired
    TransferService transferService;

    @Test
    void postTransferTest() {
        MsgTransfer msgTransfer = Mockito.mock(MsgTransfer.class);
        Mockito.when(msgTransfer.getCardFromNumber()).thenReturn(CARD_FROM_NUMBER);
        Mockito.when(msgTransfer.getCardFromCVV()).thenReturn(CARD_FROM_CVV);
        Mockito.when(msgTransfer.getCardFromValidTill()).thenReturn(CARD_FROM_VALID_TILL);
        Mockito.when(msgTransfer.getCardToNumber()).thenReturn(CARD_TO_NUMBER);
        Mockito.when(msgTransfer.getAmount())
                .thenReturn(new Amount(AMOUNT_VALUE, AMOUNT_CURRENCY));

        MsgAnswer expected = new MsgAnswer(OPERATION_ID);

        MsgAnswer actual = transferService.postTransfer(msgTransfer);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void postConfirmOperationTest() {
        MsgConfirmOperation msgConfirmOperation = Mockito.mock(MsgConfirmOperation.class);
        Mockito.when(msgConfirmOperation.getOperationId()).thenReturn(OPERATION_ID);
        Mockito.when(msgConfirmOperation.getCode()).thenReturn(CODE);

        MsgAnswer expected = new MsgAnswer(OPERATION_ID);

        MsgAnswer actual = transferService.postConfirmOperation(msgConfirmOperation);

        Assertions.assertEquals(expected, actual);
    }
}