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
public class Deposit {

    @Id
    private Long depositId;
    private Long accountId;
    private Long accountNumber;
    private Long customerId;
    private String customerName;
    private Long amount;
    private String transactionType;
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME)
    private Date createTime;
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME)
    private Date executeTIme;
    private Integer status;
    private String remarks;

}
