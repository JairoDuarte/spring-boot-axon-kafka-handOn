package com.sii.cqrs.aggregates;

import java.math.BigDecimal;
import java.util.UUID;

import com.sii.cqrs.commands.AddBankAccountCommand;
import com.sii.cqrs.commands.RemoveBankAccountCommand;
import com.sii.cqrs.commands.UpdateBalanceBankAccountCommand;
import com.sii.cqrs.events.BankAccountAddedEvent;
import com.sii.cqrs.events.BankAccountBalanceUpdatedEvent;
import com.sii.cqrs.events.BankAccountRemovedEvent;

import org.springframework.util.Assert;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.axonframework.modelling.command.AggregateLifecycle;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Aggregate
@NoArgsConstructor
public class UpdateBankAccountAggregate {

    @AggregateIdentifier
    private String id;
    private String name;
    private BigDecimal balance;

    @CommandHandler
    public UpdateBankAccountAggregate(UpdateBalanceBankAccountCommand cmd) {
        log.info("Handling {} commandss: {} id: {}", cmd.getClass().getSimpleName(), cmd, this.id);
        Assert.hasLength(cmd.getBankId(), "Bank Id should not be empty or null.");
        Assert.notNull(cmd.getBalance(), "Balance should not be empty or null.");
        AggregateLifecycle.apply(new BankAccountBalanceUpdatedEvent(cmd.getBankId(), cmd.getBalance()));
        log.info("Done handling {} command: {}", cmd.getClass().getSimpleName(), cmd);
    }

    // @EventSourcingHandler
    // public void on(BankAccountAddedEvent event) {
    // log.info("Handling {} event: {}", event.getClass().getSimpleName(), event);
    // this.id = event.getId();
    // this.name = event.getName();
    // this.balance = event.getBalance();
    // log.info("Done handling {} event: {}", event.getClass().getSimpleName(),
    // event);
    // }

    @EventSourcingHandler
    public void on(BankAccountBalanceUpdatedEvent event) {
        log.info("Handling {} event: {}", event.getClass().getSimpleName(), event);
        this.balance = event.getBalance();
        this.id = event.getBankId();

        log.info("Done handling {} event: {}", event.getClass().getSimpleName(), event);
    }

    // @EventSourcingHandler
    // public void on(BankAccountRemovedEvent event) {
    // log.info("Done handling {} event: {}", event.getClass().getSimpleName(),
    // event);
    // }
}