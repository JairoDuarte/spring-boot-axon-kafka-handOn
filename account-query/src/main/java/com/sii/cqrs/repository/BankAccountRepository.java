package com.sii.cqrs.repository;

import com.sii.cqrs.model.BankAccount;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BankAccountRepository extends MongoRepository<BankAccount, String> {
}