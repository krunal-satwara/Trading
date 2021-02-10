package com.trade.withdrawalservice.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WithdrawalBean {
    @Id
    private Long withdrawalId;
    @NotNull(message = "Account Id Must Not Be Null")
    private Long accountId;
    @NotNull(message = "Account Number Must Not Be Null")
    private Long accountNumber;
    @NotNull(message = "Customer Id Must Not Be Null")
    private Long customerId;
    @NotNull(message = "Customer Name Must Not Be Null")
    private String customerName;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date createTime;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date executeTIme;
    @NotNull(message = "Amount Must Not Be Empty")
    private Long amount;
    @NotEmpty(message = "Bank Name is required")
    private String bankName;
    @NotEmpty(message = "Bank Code is required")
    private String bankCode;
    @Digits(integer = 1, fraction = 0, message = "status must be 1 or 0")
    private Integer status;
    @NotEmpty(message = "Remarks is required")
    private String remarks;
    @NotEmpty(message = "Transaction Type is required")
    private String transactionType;

}
