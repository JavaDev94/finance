package com.example.finance.service;

import com.example.finance.model.entity.Transaction;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionService {

    Transaction create(Long userId, Transaction transactionDto);

    List<Transaction> getListByDateRange(Long userId, LocalDateTime start, LocalDateTime end);
}