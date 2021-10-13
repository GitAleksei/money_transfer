package ru.netology.money_transfer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.netology.money_transfer.exception.ForbiddenException;
import ru.netology.money_transfer.exception.UnauthorizedCard;
import ru.netology.money_transfer.model.*;
import ru.netology.money_transfer.model.fee.Fee;
import ru.netology.money_transfer.model.fee.FeeImpl;
import ru.netology.money_transfer.model.msg.MsgAnswer;
import ru.netology.money_transfer.model.msg.MsgConfirmOperation;
import ru.netology.money_transfer.model.msg.MsgTransfer;
import ru.netology.money_transfer.repository.TransferRepository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TransferService {
    private final Map<String, MsgTransfer> transfers = new ConcurrentHashMap<>();
    private final AtomicInteger operationId = new AtomicInteger();
    public static final Logger LOGGER = LoggerFactory.getLogger(TransferService.class);
    private final TransferRepository transferRepository;
    private final Fee<Amount> fee;

    public TransferService(TransferRepository transferRepository, Fee<Amount> fee) {
        this.transferRepository = transferRepository;
        this.fee = fee;
    }


    public MsgAnswer postTransfer(MsgTransfer msgTransfer) throws UnauthorizedCard {
        Card card = transferRepository.getCard(msgTransfer.getCardFromNumber());

        if (card == null || !(card.getCvv().equals(msgTransfer.getCardFromCVV()))
                || !(card.getValidTill().equals(msgTransfer.getCardFromValidTill()))) {
            throw new UnauthorizedCard("Your card is not authorized");
        }
        if (!transferRepository.containsCard(msgTransfer.getCardToNumber())) {
            throw new UnauthorizedCard("Recipient's card is not registered");
        }

        transfers.put(operationId.incrementAndGet() + "", msgTransfer);

        return new MsgAnswer(operationId + "");
    }


    public MsgAnswer postConfirmOperation(MsgConfirmOperation msgConfirmOperation) {
        var operationIdFrom = msgConfirmOperation.getOperationId();

        MsgTransfer msgTransfer;
        if ((msgTransfer = transfers.remove(operationIdFrom)) == null) {
            throw new UnsupportedOperationException("operationId is not registered");
        }

        var cardFromNumber = msgTransfer.getCardFromNumber();
        var cardFrom = transferRepository.getCard(cardFromNumber);
        var cardToNumber = msgTransfer.getCardToNumber();
        var cardTo = transferRepository.getCard(cardToNumber);

        if (!msgConfirmOperation.getCode().equals(cardFrom.getCode())) {
            throw new ForbiddenException("Verification code does not match");
        }

        transferMoney(cardFrom, cardTo, msgTransfer.getAmount());

        LOGGER.info(transferRepository.toString());
        return new MsgAnswer(operationIdFrom);
    }

    private void transferMoney(Card cardFrom, Card cardTo, Amount amount) {
        cardTo.getAmount().increment(amount);
        var feeAmount = fee.calculate(amount);

        LOGGER.info("Card from = " + cardFrom.getNumber() + ", card to = " + cardTo.getNumber() +
                ", amount = " + amount + ", fee = " + feeAmount + ", operation = completed");

        amount.increment(feeAmount);
        cardFrom.getAmount().increment(amount.negative());
    }
}
