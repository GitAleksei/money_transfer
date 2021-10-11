package ru.netology.money_transfer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.netology.money_transfer.model.Answer;
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

    public Answer postTransfer(MsgTransfer msgTransfer) {
        LOGGER.info(msgTransfer.toString());

        // To do check msgTransfer
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
