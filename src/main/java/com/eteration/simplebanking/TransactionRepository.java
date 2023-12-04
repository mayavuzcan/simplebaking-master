package com.eteration.simplebanking;

import com.eteration.simplebanking.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {


}
