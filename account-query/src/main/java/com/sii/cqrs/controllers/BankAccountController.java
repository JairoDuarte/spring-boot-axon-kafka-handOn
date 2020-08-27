package com.sii.cqrs.controllers;

import com.sii.cqrs.model.BankAccount;
import com.sii.cqrs.repository.BankAccountRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/bank-accounts")
public class BankAccountController {

    private BankAccountRepository repository;

    @GetMapping
    public ResponseEntity<Iterable<BankAccount>> getAll() {
        return ResponseEntity.ok(repository.findAll());
    }
}