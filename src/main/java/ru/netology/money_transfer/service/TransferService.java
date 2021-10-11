package ru.netology.money_transfer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.netology.money_transfer.model.Answer;
import ru.netology.money_transfer.model.MsgConfirmOperation;
import ru.netology.money_transfer.model.MsgTransfer;
import ru.netology.money_transfer.repository.TransferRepository;

@Service
public class TransferService {
    public static final Logger LOGGER = LoggerFactory.getLogger(TransferService.class);
    private final TransferRepository transferRepository;

    public TransferService(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    public Answer postTransfer(MsgTransfer msgTransfer) {
        LOGGER.info(msgTransfer.toString());
        Answer answer = new Answer();
        answer.setOperationId("123");
        return answer;
    }

    public Answer postConfirmOperation(MsgConfirmOperation msgConfirmOperation) {
        LOGGER.info(msgConfirmOperation.toString());
        Answer answer = new Answer();
        answer.setOperationId("1234");
        return answer;
    }
}
