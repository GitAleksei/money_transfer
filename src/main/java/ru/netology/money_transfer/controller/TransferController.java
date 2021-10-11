package ru.netology.money_transfer.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.money_transfer.model.Answer;
import ru.netology.money_transfer.model.MsgTransfer;
import ru.netology.money_transfer.model.MsgConfirmOperation;
import ru.netology.money_transfer.service.TransferService;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class TransferController {
    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/transfer")
    public Answer postTransfer(@RequestBody MsgTransfer msgTransfer) {
        return transferService.postTransfer(msgTransfer);
    }

    @PostMapping("/confirmOperation")
    public Answer postConfirmOperation(@RequestBody MsgConfirmOperation msgConfirmOperation) {
        return transferService.postConfirmOperation(msgConfirmOperation);
    }
}
