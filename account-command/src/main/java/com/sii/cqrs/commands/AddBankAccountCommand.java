package com.sii.cqrs.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class AddBankAccountCommand {

    @TargetAggregateIdentifier
    private String id;
    private String name;
}