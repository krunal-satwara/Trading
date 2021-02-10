package com.trade.customerservice.service;

import com.trade.customerservice.bean.CustomerBean;
import com.trade.customerservice.model.ErrorCode;

public interface CustomerService {
    ErrorCode createCustomer(CustomerBean customerBean);

    ErrorCode updateCustomer(CustomerBean customerBean);

    ErrorCode getAllCustomer();

    ErrorCode deleteCustomer(Long customerId);

    ErrorCode getCustomerByUserName(String userName);

    ErrorCode updateEmailStatus(Long customerId);

    ErrorCode updateMobileStatus(Long customerId);

    ErrorCode updateCustomer2FAStatus(Long customerId);

    ErrorCode updateCustomerStatus(Long customerId);

    ErrorCode customerStatus(Long customerId);

    ErrorCode findByCustomerUserName(String customerUserName);

    ErrorCode getCustomerByCustomerId(Long customerId);

}
