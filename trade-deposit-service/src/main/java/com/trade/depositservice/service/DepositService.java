package com.trade.depositservice.service;

import com.trade.depositservice.bean.DepositBean;
import com.trade.depositservice.model.ErrorCode;

public interface DepositService {

    ErrorCode deposit(DepositBean depositBean);

    ErrorCode updateDeposit(DepositBean depositBean);

    ErrorCode deposit(Long depositId);

    ErrorCode deposits();

    ErrorCode getAllByCustomerId(Long customerId);

}
