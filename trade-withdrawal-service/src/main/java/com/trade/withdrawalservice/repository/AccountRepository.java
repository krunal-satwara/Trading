package com.trade.withdrawalservice.repository;

import com.trade.withdrawalservice.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends MongoRepository<Account, Long> {

    Account findByAccountNumber(Long accountNumber);

    Account findByAccountId(Long accountId);

    Account findByAccountIdAndCustomerId(Long accountId, Long customerId);
}
