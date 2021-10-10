package ru.netology.money_transfer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.netology.money_transfer.model.Message;
import ru.netology.money_transfer.repository.TransferRepository;

@Service
public class TransferService {
    public static final Logger LOGGER = LoggerFactory.getLogger(TransferService.class);
    private final TransferRepository transferRepository;

    public TransferService(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    public String postTransfer(Message message) {
        LOGGER.info(message.toString());
        return "123";
    }
}
