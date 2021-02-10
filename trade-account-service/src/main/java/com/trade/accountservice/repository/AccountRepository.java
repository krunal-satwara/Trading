package com.trade.accountservice.repository;

import com.trade.accountservice.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends MongoRepository<Account, Long> {

    Account findByAccountNumber(Long accountNumber);

    Account findByAccountId(Long accountId);

    Account findByAccountIdAndCustomerId(Long accountId, Long customerId);

    List<Account> findByCustomerId(Long customerId);

    Account findAccountByAccountNumber(Long accountNumber);

    List<Account> findAccountByCustomerIdAndAccountId(Long customerId, Long accountId);

    List<Account> findAllByAccountStatusAndCustomerId(Integer accountStatus, Long customerId);

//    Account findByAccountNumberAndCustomerId(Long accountNumber, Long customerId);

    List<Account> findAllByCustomerId(Long customerId);

}
