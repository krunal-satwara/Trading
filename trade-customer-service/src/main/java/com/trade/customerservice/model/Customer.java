package com.trade.customerservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    private Long customerId;
    private String customerName;
    private Long customerMobile;
    private Integer customerMobileStatus = 0;
    private String customerEmail;
    private Integer customerEmailStatus = 0;
    private String customerPassword;
    private String customerAddress;
    private String customerUserName;
    private Integer customerStatus = 0;
    private String customerSecurityQuestion1;
    private String customerSecurityAnswer1;
    private String customerSecurityQuestion2;
    private String customerSecurityAnswer2;
    private String customerSecurityQuestion3;
    private String customerSecurityAnswer3;
    private Integer customer2FAStatus = 0;

}
