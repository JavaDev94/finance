package com.example.finance.service.impl;

import com.example.finance.model.entity.Transaction;
import com.example.finance.model.entity.User;
import com.example.finance.model.enums.TransactionType;
import com.example.finance.repository.TransactionRepository;
import com.example.finance.repository.UserRepository;
import com.example.finance.service.TransactionService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }


    @Transactional
    public Transaction create(Long userId, Transaction transactionDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        if (transactionDto.getTransactionType() == TransactionType.WITHDRAW
                && user.getBalance().compareTo(transactionDto.getAmount()) < 0) {
            throw new IllegalStateException("Insufficient balance");
        }

        if (transactionDto.getTransactionType() == TransactionType.DEPOSIT) {
            user.setBalance(user.getBalance().add(transactionDto.getAmount()));
        } else {
            user.setBalance(user.getBalance().subtract(transactionDto.getAmount()));
        }

        userRepository.save(user);
        transactionDto.setUser(user);
        return transactionRepository.save(transactionDto);
    }

    public List<Transaction> getListByDateRange(Long userId, LocalDateTime start, LocalDateTime end) {
        if (userId != null) {
            return transactionRepository.findByUserId(userId);
        }
        return transactionRepository.findByCreatedAtBetween(start, end);
    }

}
