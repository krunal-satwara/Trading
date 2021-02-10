package com.trade.transferhistoryservice.repository;

import com.trade.transferhistoryservice.model.TransferHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransferRepository extends MongoRepository<TransferHistory, Long> {
    TransferHistory findByAccountIdAndCustomerId(Long accountId, Long customerId);

    TransferHistory findAllByTransactionTypeId(Long transactionTypeId);

    TransferHistory findByTransactionTypeId(Long TransactionTypeId);

    List<TransferHistory> findTransferHistoriesByCustomerId(Long customerId);

    List<TransferHistory> findTransferHistoriesByCustomerIdAndCreateTimeIsBetween(Long customerId, Date startDate, Date endDate);
}
