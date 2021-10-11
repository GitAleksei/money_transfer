package ru.netology.money_transfer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.netology.money_transfer.exception.UnauthorizedCard;
import ru.netology.money_transfer.model.Answer;
import ru.netology.money_transfer.model.Card;
import ru.netology.money_transfer.model.MsgConfirmOperation;
import ru.netology.money_transfer.model.MsgTransfer;
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

    public TransferService(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    public Answer postTransfer(MsgTransfer msgTransfer) throws UnauthorizedCard {
        LOGGER.info(msgTransfer.toString());

        Card card = transferRepository.getCard(msgTransfer.getCardFromNumber());

        if (card == null || !(card.getCvv().equals(msgTransfer.getCardFromCVV()))
                || !(card.getValidTill().equals(msgTransfer.getCardFromValidTill()))) {
            throw new UnauthorizedCard("Your card is not authorized");
        }

        if (!transferRepository.containsCard(msgTransfer.getCardToNumber())) {
            throw new UnauthorizedCard("Recipient's card is not registered");
        }

        transfers.put(operationId.incrementAndGet() + "", msgTransfer);

        return new Answer(operationId + "");
    }

    public Answer postConfirmOperation(MsgConfirmOperation msgConfirmOperation) {
        LOGGER.info(msgConfirmOperation.toString());

        var operationIdFrom = msgConfirmOperation.getOperationId();


        MsgTransfer msg;
        if (transfers.containsKey(operationIdFrom)) {
            msg = transfers.get(operationIdFrom);
        } else {
            // New exception()
            return new Answer(operationIdFrom);
        }
        // To do operation transfer
        var cardFromNumber = msg.getCardFromNumber();
        transferRepository.getCard(cardFromNumber);

        transfers.remove(operationIdFrom);

        return new Answer(operationIdFrom);
    }
}
