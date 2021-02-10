package com.trade.transferhistoryservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Withdrawal {

    @Id
    private Long withdrawalId;
    private Long accountId;
    private Long accountNumber;
    private Long customerId;
    private String customerName;
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME)
    private Date createTime;
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME)
    private Date executeTIme;
    private Long amount;
    private String bankName;
    private String bankCode;
    private Integer status;
    private String remarks;
    private String transactionType;

}
