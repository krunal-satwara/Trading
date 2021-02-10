package com.trade.accountservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    private Long accountId;
    private Long customerId;
    private Long accountNumber;
    private String accountName;
    private Integer accountType;
    private Long accountBalance;
    private Integer accountStatus = 1;
    private String accountRemarks;

}
