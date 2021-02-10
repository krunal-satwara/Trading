package com.trade.accountservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.trade.accountservice.bean.AccountBean;
import com.trade.accountservice.model.Deposit;
import com.trade.accountservice.model.ErrorCode;
import org.codehaus.jettison.json.JSONException;

public interface AccountService {

    ErrorCode account(AccountBean accountBean) throws JsonProcessingException, JSONException;

    ErrorCode accounts();

    ErrorCode account(Long accountId);

    ErrorCode getAccountUsingAccountNumber(Long accountNumber);

    ErrorCode deleteAccount(Long accountId);

    ErrorCode updateDepositAmountForAccount(Deposit deposit);

    ErrorCode activateAccount(Long accountId);

    ErrorCode findAccountDetailByCustomerId(Long customerId);

    ErrorCode findAccountDetailByAccountNumber(Long accountNumber);

    ErrorCode getWithdrawalByTwoID(Long customerId, Long accountId);

    ErrorCode findAllAccountDetailByStatus(Long customerId);

//    ErrorCode findAccountNameByCustomerId(Long accountNumber , Long customerId);

    ErrorCode findByCustomerId(Long customerId);
    
    ErrorCode generateAccountNumber();
}
