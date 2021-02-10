package com.trade.withdrawalservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Account {

    @Id
    private Long accountId;
    private Long customerId;
    private Long accountNumber;
    private String accountName;
    private Integer accountType;
    private Long accountBalance;
    private Integer accountStatus;
    private String accountRemarks;

    public Account() {
    }

    public Account(Long accountId, Long customerId, Long accountNumber, String accountName, Integer accountType, Long accountBalance, Integer accountStatus, String accountRemarks) {
        this.accountId = accountId;
        this.customerId = customerId;
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.accountType = accountType;
        this.accountBalance = accountBalance;
        this.accountStatus = accountStatus;
        this.accountRemarks = accountRemarks;
    }
}
