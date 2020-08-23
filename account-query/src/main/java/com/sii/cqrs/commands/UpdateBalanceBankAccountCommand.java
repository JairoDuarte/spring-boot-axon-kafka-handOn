package com.sii.cqrs.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@ToString
@AllArgsConstructor
public class UpdateBalanceBankAccountCommand {

    @TargetAggregateIdentifier // marca o identificador do agregado que o comando visa alterar.
    private String bankId;
    private BigDecimal balance;
}