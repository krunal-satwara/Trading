package com.trade.withdrawalservice.service;

import com.trade.withdrawalservice.bean.WithdrawalBean;
import com.trade.withdrawalservice.model.ErrorCode;

public interface WithdrawalService {

    ErrorCode withdrawal(WithdrawalBean withdrawalBean);

    ErrorCode withdrawal(Long withdrawalId);

    ErrorCode withdrawals();

    ErrorCode getWithdrawalByCustomerID(Long customerId);


}
