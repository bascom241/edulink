package com.Edulink.EdulinkServer.service;

import com.Edulink.EdulinkServer.dto.PaystackResponse;
import com.Edulink.EdulinkServer.model.Bank;
import com.Edulink.EdulinkServer.repository.BankRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Service
public class BankService {

    @Value("${paystack.secret.key}")
    private String payStackSecret;

    private final BankRepository bankRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public ResponseEntity<List<Bank>> getBanks() {

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(payStackSecret);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        String payStackUrl = "https://api.paystack.co/bank";

        ResponseEntity<PaystackResponse<List<Bank>>> response =
                restTemplate.exchange(
                        payStackUrl,
                        HttpMethod.GET,
                        entity,
                        new ParameterizedTypeReference<PaystackResponse<List<Bank>>>() {}
                );

        List<Bank> banks = Objects.requireNonNull(response.getBody()).getData();


        bankRepository.saveAll(banks);


        banks.forEach(bank ->
                System.out.println(bank.getName() + " - " + bank.getCode())
        );

        return ResponseEntity.ok(banks);
    }


    public List<Bank> getAllBanks () {
        return bankRepository.findAll();
    }
}
