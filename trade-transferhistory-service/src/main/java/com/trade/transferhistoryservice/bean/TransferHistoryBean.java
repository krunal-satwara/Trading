package com.trade.transferhistoryservice.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferHistoryBean {

    @Id
    private Long transferId;
    @NotNull(message = "Account Id Must Not Be Null")
    private Long accountId;
    @NotNull(message = "Account Number Must Not Be Null")
    private Long accountNumber;
    @NotNull(message = "Customer Id Must Not Be Null")
    private Long customerId;
    @NotNull(message = "Customer Number Must Not Be Null")
    private String customerName;
    @NotNull(message = "External Transfer ID Must Not Be Empty")
    private Long TransactionTypeId;
    @NotEmpty(message = "transfer Type is required")
    private String transferType;
    @NotNull(message = "Before Balance Must Not Be Empty")
    private Long beforeBalance;
    @NotNull(message = "After Balance Must Not Be Empty")
    private Long afterBalance;
    @NotNull(message = "Amount Must Not Be Empty")
    private Long amount;
    @NotEmpty(message = "Note is required")
    private String note;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date createTime;

}
