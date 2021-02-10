package com.trade.customerservice.service;

import com.trade.customerservice.bean.CustomerBean;
import com.trade.customerservice.model.Customer;
import com.trade.customerservice.model.ErrorCode;
import com.trade.customerservice.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment environment;

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

    //String authToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJrcnVuYWwiLCJleHAiOjE1OTU1NjA4MTIsImlhdCI6MTU5MjkzMjgxMn0.F7B_lcKFF9MatRc7m4k-_gbCPdnvpy0z2bSD1zP6f94";

    @Override
    public ErrorCode createCustomer(CustomerBean customerBean) {

        Customer customer = new Customer(customerBean.getCustomerId(), customerBean.getCustomerName(), customerBean.getCustomerMobile()
                , customerBean.getCustomerMobileStatus(), customerBean.getCustomerEmail(), customerBean.getCustomerEmailStatus()
                , customerBean.getCustomerPassword(), customerBean.getCustomerAddress(), customerBean.getCustomerUserName()
                , customerBean.getCustomerStatus(), customerBean.getCustomerSecurityQuestion1(), customerBean.getCustomerSecurityAnswer1()
                , customerBean.getCustomerSecurityQuestion2(), customerBean.getCustomerSecurityAnswer2()
                , customerBean.getCustomerSecurityQuestion3(), customerBean.getCustomerSecurityAnswer3(), customerBean.getCustomer2FAStatus());

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(customer, customerBean);

        List<Customer> customers = customerRepository.findAll();
        if (true) {
            for (Customer customer1 : customers) {
                if (customerBean.getCustomerUserName().equals(customer1.getCustomerUserName())) {
                    return new ErrorCode(1009, "CustomerUserName Already Exist");
                }
            }
        }
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            //headers.set("Authorization", "Token "+authToken);
            HttpEntity<String> entity = new HttpEntity<String>(headers);
            LOGGER.info("Request For Sequence For Customer");
            ResponseEntity<Long> response = restTemplate.exchange(environment.getProperty("seq.url")+"customer", HttpMethod.GET, entity, Long.class);
            final Long customerId = response.getBody();
            LOGGER.info("Response From Sequence For Customer . = {}",customerId);
            customer.setCustomerId(customerId);
            Customer resCustomer = customerRepository.save(customer);
            LOGGER.info("Response For Create Customer =" + resCustomer);
            return new ErrorCode(1001, "Customer Created Successfully",customerId);
        } catch (Exception exception) {
            LOGGER.error("{}", exception);
        }
        return new ErrorCode(1002, "Opps !! Customer Not Saved");
    }

    @Override
    public ErrorCode updateCustomer(CustomerBean customerBean) {
        Customer customer = new Customer(customerBean.getCustomerId(), customerBean.getCustomerName(), customerBean.getCustomerMobile()
                , customerBean.getCustomerMobileStatus(), customerBean.getCustomerEmail(), customerBean.getCustomerEmailStatus()
                , customerBean.getCustomerPassword(), customerBean.getCustomerAddress(), customerBean.getCustomerUserName()
                , customerBean.getCustomerStatus(), customerBean.getCustomerSecurityQuestion1(), customerBean.getCustomerSecurityAnswer1()
                , customerBean.getCustomerSecurityQuestion2(), customerBean.getCustomerSecurityAnswer2()
                , customerBean.getCustomerSecurityQuestion3(), customerBean.getCustomerSecurityAnswer3(), customerBean.getCustomer2FAStatus());

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(customer, customerBean);

        try {
            Customer resCustomer = customerRepository.save(customer);
            LOGGER.info("Response For Update Customer =" + resCustomer);
            return new ErrorCode(1003, "Customer Updated Successfully");
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        }
        return new ErrorCode(1004, "Opps !! Customer Not Updated");
    }

    @Override
    public ErrorCode getAllCustomer() {
        try {
            List<Customer> customers = customerRepository.findAll();
            LOGGER.info("Response For List Of Customers =" + customers);
            return new ErrorCode(1005, "Successfully Return Customer List",customers);
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        }
        return new ErrorCode(1006, "Opps !! No Record Found");
    }

    @Override
    public ErrorCode deleteCustomer(Long customerId) {

        try {
            Customer customer = customerRepository.findByCustomerId(customerId);
            LOGGER.info("Get Customer For Delete =" + customer);
            customer.setCustomerStatus(0);
            Customer delCustomer = customerRepository.save(customer);
            LOGGER.info("Delete Account =" + delCustomer);
            return new ErrorCode(1007, "Customer Deleted Successfully");
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        }
        return new ErrorCode(1008, "Customer Failed to Delete");
    }

    @Override
    public ErrorCode getCustomerByUserName(String userName) {
        try {
            Customer customer = customerRepository.findCustomerByCustomerUserName(userName);
            LOGGER.info("Response For the Customer By User Name" + customer);
            if(customer !=  null) {
                return new ErrorCode(1010, "Successfully Get Customer", customer);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return new ErrorCode(1006, "Opps !! No Record Found");
    }

    @Override
    public ErrorCode updateEmailStatus(Long customerId) {
        try {
            Customer customer = customerRepository.findByCustomerId(customerId);
            LOGGER.info("Find Record For Update Email Status");
            customer.setCustomerEmailStatus(1);
            customerRepository.save(customer);
            LOGGER.info("Updated The Email Verification Status");
            return new ErrorCode(1011, "Updated Email Verification Status");
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        }
        return new ErrorCode(1012, "Email Verification Status Not Updated");
    }

    @Override
    public ErrorCode updateMobileStatus(Long customerId) {
        try {
            Customer customer = customerRepository.findByCustomerId(customerId);
            LOGGER.info("Find Record For Update Mobile Status");
            customer.setCustomerMobileStatus(1);
            customerRepository.save(customer);
            LOGGER.info("Updated The Mobile Verification Status");
            return new ErrorCode(1013, "Updated Mobile Verification Status");
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        }
        return new ErrorCode(1014, "Mobile Verification Status Not Updated");
    }

    @Override
    public ErrorCode updateCustomer2FAStatus(Long customerId) {
        try {
            Customer customer = customerRepository.findByCustomerId(customerId);
            LOGGER.info("Find Record For Update 2FA Status");
            if (customer.getCustomerEmailStatus() == 0) {
                return new ErrorCode(1017, "Please Verify Email First");
            }
            if (customer.getCustomerMobileStatus() == 0) {
                return new ErrorCode(1018, "Please Verify Mobile First");
            }
            customer.setCustomer2FAStatus(1);
            customerRepository.save(customer);
            LOGGER.info("Updated The 2FA Verification Status");
            return new ErrorCode(1019, "Updated 2FA Verification Status");
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        }
        return new ErrorCode(1020, "Not Updated 2FA Verification Status");
    }

    @Override
    public ErrorCode updateCustomerStatus(Long customerId) {
        try {
            Customer customer = customerRepository.findByCustomerId(customerId);
            LOGGER.info("Find Record For Update Customer Status");
            if (customer.getCustomerEmailStatus() == 0) {
                return new ErrorCode("Please Verify Email First");
            }
            if (customer.getCustomerMobileStatus() == 0) {
                return new ErrorCode("Please Verify Mobile First");
            }
            if (customer.getCustomerEmailStatus() == 1 && customer.getCustomerMobileStatus() == 1) {
                customer.setCustomerStatus(1);
            }
            customerRepository.save(customer);
            LOGGER.info("Updated Customer Status as Active");
            return new ErrorCode(1015, "Updated Customer Status as Active");
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        }
        return new ErrorCode(1016, "Not Updated Customer Status");
    }

    @Override
    public ErrorCode customerStatus(Long customerId) {
        try {
            Optional<Customer> customer = customerRepository.findById(customerId);
            Integer customerStatus = customer.get().getCustomerStatus();
            LOGGER.info("Customer Status From Database Response ="+customerStatus);
            return new ErrorCode(1021,"Successfully Receive Customer Status",customerStatus);
        }catch (Exception exception){
            LOGGER.error("{}",exception);
        }
        return new ErrorCode(1022,"Not Receive Customer Status");
    }

    @Override
    public ErrorCode findByCustomerUserName(String customerUserName) {
        try{
            Customer customer = customerRepository.findCustomerByCustomerUserName(customerUserName);
            return new ErrorCode(1023,"Successfully Find Customer",customer);
        }catch (Exception exception){
            LOGGER.error("{}",exception);
        }
        return new ErrorCode(1024,"Sorry Credential Not Matched");
    }

    @Override
    public ErrorCode getCustomerByCustomerId(Long customerId) {
        try{
            Customer customer = customerRepository.findCustomerByCustomerId(customerId);
            if (customer != null){
                LOGGER.info("request for get customer"+customer);
            return new ErrorCode(1025,"Successfully get Customer By Customer ID",customer);}
            else {
                return new ErrorCode(1026,"No record Found For Customer");
            }
        }catch (Exception exception){
            LOGGER.error("{}",exception);
        }
        return new ErrorCode(1027,"No record Found For Customer");
    }

}
