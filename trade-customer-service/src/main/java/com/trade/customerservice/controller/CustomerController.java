package com.trade.customerservice.controller;

import com.trade.customerservice.bean.CustomerBean;
import com.trade.customerservice.model.ErrorCode;
import com.trade.customerservice.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/trade/customer")
public class CustomerController {


    @Autowired
    private CustomerService customerService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    private HttpServletRequest request;

    @ModelAttribute
    public void bindHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }

    @PostMapping("/create")
    public Object createCustomer(@Valid @RequestBody CustomerBean customerBean) {
        LOGGER.info("Request For Create Customer =" + customerBean);
        return customerService.createCustomer(customerBean);
    }

    @GetMapping("/getAllCustomer")
    public ErrorCode getAllCustomer() {
        LOGGER.info("Request For List Of Customers");
        return customerService.getAllCustomer();
    }

    @GetMapping("/getCustomer/{userName}")
    public ErrorCode getCustomerByUserName(@Valid @PathVariable String userName) {
        LOGGER.info("Request For Get Customer By User Name =" + userName);
        return customerService.getCustomerByUserName(userName);
    }

    @DeleteMapping("/delete/{customerId}")
    public ErrorCode delete(@PathVariable Long customerId) {
        LOGGER.info("Request For Delete Customer =" + customerId);
        return customerService.deleteCustomer(customerId);
    }

    @PutMapping("/update")
    public ErrorCode updateCustomer(@RequestBody CustomerBean customerBean) {
        LOGGER.info("Request For Update Customer =" + customerBean);
        return customerService.updateCustomer(customerBean);
    }

    @PostMapping("/verifyEmail/{customerId}")
    public ErrorCode updateEmailStatus(@PathVariable Long customerId) {
        LOGGER.info("Request For Verify Email Status For CustomerId = " + customerId);
        return customerService.updateEmailStatus(customerId);
    }

    @PostMapping("/verifyMobile/{customerId}")
    public ErrorCode updateMobileStatus(@PathVariable Long customerId) {
        LOGGER.info("Request For Verify Mobile Status For CustomerId = " + customerId);
        return customerService.updateMobileStatus(customerId);
    }

    @PostMapping("/verify2FA/{customerId}")
    public ErrorCode updateCustomer2FAStatus(@PathVariable Long customerId) {
        LOGGER.info("Request For Verify 2FA Status For CustomerId = " + customerId);
        return customerService.updateCustomer2FAStatus(customerId);
    }

    @PostMapping("/activateCustomer/{customerId}")
    public ErrorCode updateCustomerStatus(@PathVariable Long customerId) {
        LOGGER.info("Request For Activate Email Status For CustomerId = " + customerId);
        return customerService.updateCustomerStatus(customerId);
    }

    @GetMapping("/customerStatus/{customerId}")
    public ErrorCode customerStatus(@PathVariable Long customerId) {
        LOGGER.info("Request For Customer Status");
        return customerService.customerStatus(customerId);
    }

    @PostMapping("/customerUserName/{customerUserName}")
    public ErrorCode findByCustomerUserName(@PathVariable String customerUserName) {
        LOGGER.info("Request For Security Check");
        return customerService.findByCustomerUserName(customerUserName);
    }

    @GetMapping("/getCustomerByCustomerId/{CustomerId}")
    public ErrorCode getCustomerByCustomerId(@PathVariable Long CustomerId) {
        LOGGER.info("Request For get Customer By Customer ID");
        return customerService.getCustomerByCustomerId(CustomerId);

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return errors;
    }
}
