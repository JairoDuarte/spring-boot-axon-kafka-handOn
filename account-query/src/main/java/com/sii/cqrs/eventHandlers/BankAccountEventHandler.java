package com.sii.cqrs.eventHandlers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.sii.cqrs.events.BankAccountAddedEvent;
import com.sii.cqrs.events.BankAccountBalanceUpdatedEvent;
import com.sii.cqrs.events.BankAccountRemovedEvent;
import com.sii.cqrs.exceptions.BankAccountNotFoundException;
import com.sii.cqrs.model.BankAccount;
import com.sii.cqrs.repository.BankAccountRepository;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
@ProcessingGroup("kafka-group")
public class BankAccountEventHandler {
    private final BankAccountRepository repository;

    @EventHandler
    public void on(BankAccountAddedEvent event) {
        BankAccount bankAccount = repository.save(new BankAccount(event.getId(), event.getName(), event.getBalance()));
        log.info("A bank account was added! {}", bankAccount);
    }

    @EventHandler
    public void on(BankAccountBalanceUpdatedEvent event) {
        var bank = repository.findById(event.getBankId()).orElseThrow(BankAccountNotFoundException::new);
        bank.setBalance(event.getBalance());
        repository.save(bank);
        log.info("A bank account balance was updated! {}", bank);
    }

    @EventHandler
    public void on(BankAccountRemovedEvent event) {
        repository.deleteById(event.getId());
        log.info("A bank account was removed! {}", event.getId());
    }
}