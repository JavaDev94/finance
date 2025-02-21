package com.example.finance.service;

import com.example.finance.model.entity.Transaction;
import com.example.finance.model.entity.User;
import com.example.finance.model.enums.TransactionType;
import com.example.finance.repository.TransactionRepository;
import com.example.finance.repository.UserRepository;
import com.example.finance.service.impl.TransactionServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TransactionServiceImpl transactionServiceImpl;

    private User user;
    private Transaction transaction;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setName("John Doe");
        user.setBalance(BigDecimal.valueOf(1000));

        transaction = new Transaction();
        transaction.setId(1L);
        transaction.setUser(user);
        transaction.setAmount(BigDecimal.valueOf(200));
        transaction.setTransactionType(TransactionType.DEPOSIT);
        transaction.setCreatedAt(LocalDateTime.now());
    }

    @Test
    void shouldCreateTransaction() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        Transaction result = transactionServiceImpl.create(1L,transaction);

        assertNotNull(result);
        assertEquals(transaction.getAmount(), result.getAmount());
        verify(transactionRepository, times(1)).save(any(Transaction.class));
    }

    @Test
    void shouldGetTransactionsByUserId() {
        when(transactionRepository.findByUserId(1L)).thenReturn(List.of(transaction));

        List<Transaction> result = transactionServiceImpl.getListByDateRange(1L, mock(), mock());

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

}
