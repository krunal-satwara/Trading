package com.trade.withdrawalservice.repository;

import com.trade.withdrawalservice.model.TransferHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends MongoRepository<TransferHistory, Long> {
    TransferHistory findByAccountIdAndCustomerId(Long accountId, Long customerId);

    TransferHistory findAllByTransactionTypeId(Long transactionTypeId);

    TransferHistory findByTransactionTypeId(Long TransactionTypeId);

}
