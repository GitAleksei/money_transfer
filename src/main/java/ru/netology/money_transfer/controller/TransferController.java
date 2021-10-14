package ru.netology.money_transfer.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.money_transfer.model.msg.MsgAnswer;
import ru.netology.money_transfer.model.msg.MsgTransfer;
import ru.netology.money_transfer.model.msg.MsgConfirmOperation;
import ru.netology.money_transfer.service.TransferService;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "${cross.origin.host.name}", maxAge = 3600)
@Validated
public class TransferController {
    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/transfer")
    public MsgAnswer postTransfer(@Valid @RequestBody MsgTransfer msgTransfer) {
        return transferService.postTransfer(msgTransfer);
    }

    @PostMapping("/confirmOperation")
    public MsgAnswer postConfirmOperation(@RequestBody MsgConfirmOperation msgConfirmOperation) {
        return transferService.postConfirmOperation(msgConfirmOperation);
    }
}
