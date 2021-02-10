package com.trade.accountservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Deposit {

    private Long depositId;
    private Long accountId;
    private Long customerId;
    private Long amount;
    private String transactionType;
    private String createTime;
    private String executeTIme;
    private Integer status;
    private String remarks;

}
