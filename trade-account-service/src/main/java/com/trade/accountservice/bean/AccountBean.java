package com.trade.accountservice.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountBean {

    @Id
    private Long accountId;
    @NotNull(message = "Customer ID Must Not Be Empty")
    private Long customerId;
    @NotNull(message = "Account Number Must Between 3 To 20 Digits")
    private Long accountNumber;
    @NotEmpty(message = "Account Name Must Not Be Empty")
    private String accountName;
    @NotNull(message = "Account Type Must Not Be Empty")
    private Integer accountType;
    @NotNull(message = "Account Balance Must Not Be Null")
    private Long accountBalance;
    @Max(value = 1, message = "Must Be Equal Or Less Than 1")
    @Min(value = 0, message = "Must Be Equal Or Greater Than 0")
    private Integer accountStatus = 1;
    @NotEmpty(message = "Account Remarks Must Not Be Empty")
    private String accountRemarks;

}
