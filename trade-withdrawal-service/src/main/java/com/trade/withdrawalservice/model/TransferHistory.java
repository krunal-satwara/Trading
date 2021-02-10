package com.trade.withdrawalservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferHistory {

    @Id
    private Long transferId;
    private Long accountId;
    private Long accountNumber;
    private String customerId;
    private String customerName;
    private Long TransactionTypeId;
    private String transferType;
    private Long beforeBalance;
    private Long afterBalance;
    private Long amount;
    private String note;
    private Date createTime;

}

