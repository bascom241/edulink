package com.Edulink.EdulinkServer.controller;

import com.Edulink.EdulinkServer.model.Bank;
import com.Edulink.EdulinkServer.service.BankService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/banks")
public class BankController {

    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }


    @GetMapping
    public ResponseEntity<?> fetchBanks() {
        List<Bank> banks = bankService.getAllBanks();
        return ResponseEntity.ok(banks);

    }
}
