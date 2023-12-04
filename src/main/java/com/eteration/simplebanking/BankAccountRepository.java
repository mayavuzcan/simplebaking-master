package com.eteration.simplebanking;

import com.eteration.simplebanking.entity.BankAccountEntity;
import com.eteration.simplebanking.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface BankAccountRepository extends JpaRepository<BankAccountEntity, Long> {
    BankAccountEntity findByAccountNumber(String accountNumber);
}
