package ru.netology.money_transfer.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.money_transfer.model.Message;
import ru.netology.money_transfer.service.TransferService;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class TransferController {
    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/transfer")
    public String postTransfer(@RequestBody Message message) {
        transferService.postTransfer(message);
        return "12";
    }
}
