package com.trade.depositservice.repository;

import com.trade.depositservice.model.Deposit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepositRespository extends MongoRepository<Deposit, Long> {

    List<Deposit> getAllByCustomerId(Long customerId);

}
