package com.example.finance.repository;

import com.example.finance.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUserId(Long userId);
    List<Transaction> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
}
