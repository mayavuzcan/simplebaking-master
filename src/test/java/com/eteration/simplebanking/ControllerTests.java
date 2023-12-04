package com.eteration.simplebanking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.eteration.simplebanking.Request.DepositRequest;
import com.eteration.simplebanking.Request.WithdrawalRequest;
import com.eteration.simplebanking.Response.DepositResponse;
import com.eteration.simplebanking.controller.AccountController;
import com.eteration.simplebanking.entity.BankAccountEntity;
import com.eteration.simplebanking.entity.DepositTransactionEntity;
import com.eteration.simplebanking.model.InsufficientBalanceException;
import com.eteration.simplebanking.services.BankAccountService;
import com.eteration.simplebanking.services.TransactionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;

@ExtendWith(MockitoExtension.class)
class ControllerTests {

    @Spy
    @InjectMocks
    private AccountController controller;

    @Mock
    private BankAccountService service;

    @Mock
    private TransactionService transactionService;

    @Mock
    private DepositTransactionEntity depositTransaction;

    @Mock
    private BankAccountEntity account;

    @BeforeEach
    void setUp() {
        account = new BankAccountEntity("17892", "Kerem Karaca", 0.0, new Date());

    }

    @Test
    public void givenId_Credit_thenReturnJson() throws Exception {
        // Arrange
        BankAccountEntity mockAccount = new BankAccountEntity("17892", "Kerem Karaca", 100.0, new Date());
        DepositRequest depositRequest = new DepositRequest(50.0);

        when(service.findAccount("17892")).thenReturn(mockAccount);
        when(transactionService.saveTransaction(any(DepositTransactionEntity.class))).thenReturn(depositTransaction);

        // Act
        ResponseEntity<Object> responseEntity = controller.credit("17892", depositRequest);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("OK", ((DepositResponse) responseEntity.getBody()).getStatus());
        verify(service, times(1)).findAccount("17892");
        verify(transactionService, times(1)).saveTransaction(any(DepositTransactionEntity.class));
    }

    @Test
    public void givenId_CreditAndThenDebit_thenReturnJson() throws Exception {
        // Arrange
        BankAccountEntity mockAccount = new BankAccountEntity("17892", "Kerem Karaca", 0.0, new Date());
        when(service.findAccount("17892")).thenReturn(mockAccount);
        when(transactionService.saveTransaction(any(DepositTransactionEntity.class))).thenReturn(depositTransaction);

        // Act
        DepositRequest depositRequest = new DepositRequest(1000.0);
        WithdrawalRequest withdrawalRequest = new WithdrawalRequest(50.0);

        ResponseEntity<Object> result = controller.credit("17892", depositRequest);
        ResponseEntity<Object> result2 = controller.debit("17892", withdrawalRequest);

        // Assert
        verify(service, times(2)).findAccount("17892");
        assertEquals(200, result.getStatusCodeValue());
        assertEquals(200, result2.getStatusCodeValue());
        assertEquals(950.0, mockAccount.getBalance());
    }

    @Test
    public void givenId_CreditAndThenDebitMoreGetException_thenReturnJson() throws Exception {
        // Arrange
        BankAccountEntity mockAccount = new BankAccountEntity("17892", "Kerem Karaca", 0.0, new Date());

        when(service.findAccount("17892")).thenReturn(mockAccount);
        when(transactionService.saveTransaction(any(DepositTransactionEntity.class))).thenReturn(depositTransaction);

        // Act
        ResponseEntity<Object> result = controller.credit("17892", new DepositRequest(1000.0));

        // Assert
        assertEquals(200, result.getStatusCodeValue());
        assertEquals(1000.0, mockAccount.getBalance(), 0.001);
        verify(service, times(1)).findAccount("17892");

        // Act & Assert
        Assertions.assertThrows(InsufficientBalanceException.class, () -> {
            controller.debit("17892", new WithdrawalRequest(5000.0));
        });
    }

    @Test
    public void givenId_GetAccount_thenReturnJson() throws Exception {
        // Arrange
        when(service.findAccount("17892")).thenReturn(account);

        // Act
        ResponseEntity<Object> result = controller.getAccount("17892");

        // Assert
        verify(service, times(1)).findAccount("17892");
        assertEquals(200, result.getStatusCodeValue());
    }
}