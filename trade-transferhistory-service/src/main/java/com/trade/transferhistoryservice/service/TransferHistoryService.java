package com.trade.transferhistoryservice.service;

import com.trade.transferhistoryservice.bean.TransferHistoryBean;
import com.trade.transferhistoryservice.model.Deposit;
import com.trade.transferhistoryservice.model.ErrorCode;
import com.trade.transferhistoryservice.model.Search;
import com.trade.transferhistoryservice.model.Withdrawal;

public interface TransferHistoryService {

    ErrorCode transferHistory(TransferHistoryBean transferHistoryBean);

    ErrorCode transferHistory(Long transferId);

    ErrorCode transferHistories();

    ErrorCode saveTransferHistory(Deposit deposit);

    ErrorCode saveTransferHistory(Withdrawal withdrawal);

    ErrorCode updateTransferHistory(Deposit deposit);

    ErrorCode transferhistorieslist(Long customerId);

    ErrorCode transferHistorySearch(Search search);

}
