package com.trade.customerservice.repository;

import com.trade.customerservice.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotEmpty;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, Long> {

    Customer findCustomerByCustomerUserName(@NotEmpty(message = "Username is required") String customerUserName);

    Customer findByCustomerId(Long customerId);

    Customer findByCustomerUserName(String customerUserName);

    Customer findCustomerByCustomerId(Long customerId);

}
