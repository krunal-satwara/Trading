package com.trade.zuulservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Document(collection = "customer")
@Data
public class Customer {
    @Id
    private Long customerId;
    @NotEmpty(message = "customername is required")
    private String customerName;
    private Long customerMobile;
    @Field
    private Integer customerMobileStatus = 0;
    @Email(message = "Please provide a valid email address")
    @Pattern(regexp = ".+@.+\\..+", message = "Please provide a valid email address")
    @Indexed(unique = true)
    private String customerEmail;
    @Field
    private Integer customerEmailStatus = 0;
    @NotEmpty(message = "password is required")
    private String customerPassword;
    private String customerAddress;
    @Indexed(unique = true)
    private String customerUserName;
    @Field
    private Integer customerStatus = 0;
    @NotEmpty(message = "Please Select Question 1")
    private String customerSecurityQuestion1;
    @NotEmpty(message = "Please Answer Question 1")
    private String customerSecurityAnswer1;
    @NotEmpty(message = "Please Select Question 2")
    private String customerSecurityQuestion2;
    @NotEmpty(message = "Please Answer Question 2")
    private String customerSecurityAnswer2;
    @NotEmpty(message = "Please Select Question 3")
    private String customerSecurityQuestion3;
    @NotEmpty(message = "Please Answer Question 3")
    private String customerSecurityAnswer3;
    @Max(value = 1, message = "must be equal or less than 1")
    private Integer customer2FAStatus = 0;

    public Customer() {
    }

    public Customer(Long customerId, @NotEmpty(message = "customername is required") String customerName, Long customerMobile, Integer customerMobileStatus, @Email(message = "Please provide a valid email address") @Pattern(regexp = ".+@.+\\..+", message = "Please provide a valid email address") String customerEmail, Integer customerEmailStatus, @NotEmpty(message = "password is required") String customerPassword, String customerAddress, String customerUserName, Integer customerStatus, @NotEmpty(message = "Please Select Question 1") String customerSecurityQuestion1, @NotEmpty(message = "Please Answer Question 1") String customerSecurityAnswer1, @NotEmpty(message = "Please Select Question 2") String customerSecurityQuestion2, @NotEmpty(message = "Please Answer Question 2") String customerSecurityAnswer2, @NotEmpty(message = "Please Select Question 3") String customerSecurityQuestion3, @NotEmpty(message = "Please Answer Question 3") String customerSecurityAnswer3, @Max(value = 1, message = "must be equal or less than 1") Integer customer2FAStatus) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerMobile = customerMobile;
        this.customerMobileStatus = customerMobileStatus;
        this.customerEmail = customerEmail;
        this.customerEmailStatus = customerEmailStatus;
        this.customerPassword = customerPassword;
        this.customerAddress = customerAddress;
        this.customerUserName = customerUserName;
        this.customerStatus = customerStatus;
        this.customerSecurityQuestion1 = customerSecurityQuestion1;
        this.customerSecurityAnswer1 = customerSecurityAnswer1;
        this.customerSecurityQuestion2 = customerSecurityQuestion2;
        this.customerSecurityAnswer2 = customerSecurityAnswer2;
        this.customerSecurityQuestion3 = customerSecurityQuestion3;
        this.customerSecurityAnswer3 = customerSecurityAnswer3;
        this.customer2FAStatus = customer2FAStatus;
    }
}
