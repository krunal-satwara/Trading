package com.trade.depositservice.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepositBean {

    @Id
    private Long depositId;
    @NotNull(message = "Account Id Must Not Be Null")
    private Long accountId;
    @NotNull(message = "Account Number Must Not Be Null")
    private Long accountNumber;
    @NotNull(message = "Customer Id Must Not Be Null")
    private Long customerId;
    @NotNull(message = "Customer Name Must Not Be Null")
    private String customerName;
    @NotNull(message = "Amount Must Not Be Null")
    private Long amount;
    @NotEmpty(message = "transactionType is required")
    private String transactionType = "D";
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date createTime;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date executeTIme;
    @Max(value = 1, message = "Must Be Equal Or Less Than 1")
    @Min(value = 0, message = "Must Be Equal Or Greater Than 0")
    private Integer status=1;
    @NotEmpty(message = "Remarks is required")
    private String remarks;
}
