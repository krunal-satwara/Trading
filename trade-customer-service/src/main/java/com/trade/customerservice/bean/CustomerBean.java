package com.trade.customerservice.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerBean {

    @Id
    private Long customerId;
    @NotEmpty(message = "Customer Name is required")
    private String customerName;
    @NotNull(message = "Customer Mobile Number Must Not Be Empty")
    @Digits(integer = 10, fraction = 0, message = "Customer Mobile Number Must Contains 10 Digit")
    private Long customerMobile;
    @Max(value = 1, message = "Must Be Equal Or Less Than 1")
    @Min(value = 0, message = "Must Be Equal Or Greater Than 0")
    private Integer customerMobileStatus = 0;
    @Email(message = "Please provide a valid email address")
    @Pattern(regexp = ".+@.+\\..+", message = "Please provide a valid email address")
    @Indexed(unique = true)
    private String customerEmail;
    @Max(value = 1, message = "Must Be Equal Or Less Than 1")
    @Min(value = 0, message = "Must Be Equal Or Greater Than 0")
    private Integer customerEmailStatus = 0;
    @NotEmpty(message = "Password is required")
    private String customerPassword;
    @NotEmpty(message = "Address is required")
    private String customerAddress;
    @Indexed(unique = true)
    private String customerUserName;
    @Digits(integer = 1, fraction = 0, message = "status must be 1 or 0")
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
    @Max(value = 1, message = "Must Be Equal Or Less Than 1")
    @Min(value = 0, message = "Must Be Equal Or Greater Than 0")
    private Integer customer2FAStatus = 0;
}
