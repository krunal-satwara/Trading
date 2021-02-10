package com.trade.withdrawalservice.repository;

import com.trade.withdrawalservice.model.Withdrawal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WithdrawalRepository extends MongoRepository<Withdrawal, Long> {
    List<Withdrawal> findWithdrawalByCustomerId(Long customerId);

    List<Withdrawal> findWithdrawalByCustomerIdAndAccountId(Long customerId, Long accountId);
}
