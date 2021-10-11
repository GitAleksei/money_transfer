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

        transfers.put(operationId.incrementAndGet() + "", msgTransfer);

        Answer answer = new Answer();
        answer.setOperationId(operationId + "");
        return answer;
    }

    public Answer postConfirmOperation(MsgConfirmOperation msgConfirmOperation) {
        LOGGER.info(msgConfirmOperation.toString());
        var operId = msgConfirmOperation.getOperationId();
        if (transfers.containsKey(operId)) {
            var msg = transfers.remove(operId);
        }
        // To do operation transfer
        Answer answer = new Answer();
        answer.setOperationId(operId);
        return answer;
    }
}
