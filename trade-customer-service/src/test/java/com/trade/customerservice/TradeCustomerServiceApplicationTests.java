package com.trade.customerservice;

import com.trade.customerservice.bean.CustomerBean;
import com.trade.customerservice.model.Customer;
import com.trade.customerservice.repository.CustomerRepository;
import com.trade.customerservice.service.CustomerServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TradeCustomerServiceApplicationTests {

    private static final CustomerBean customerBean = new CustomerBean(0L, "customername", 9876543211L, 1, "test@gmail.com", 1, "password", "address", "unique", 1, "q1", "a1", "q2", "a2", "q3", "a3", 1);

    private static final Customer customer = new Customer(0L, "customername", 9876543211L, 1, "test@gmail.com", 1, "password", "address", "unique", 1, "q1", "a1", "q2", "a2", "q3", "a3", 1);

    @InjectMocks
     CustomerServiceImpl customerServiceImpl;

   // public CustomerBean customerBean1;

    @Mock
     CustomerRepository customerRepository;

    String userName="unique";


/*@Before
public void setUp(){
customerBean1 = new CustomerBean(1L, "customername", 9876543211L, 1, "test@gmail.com", 1, "password", "address", "unique", 1, "q1", "a1", "q2", "a2", "q3", "a3", 1);
}*/

    @Test
    public void createCustomer() {
        Mockito.when(customerRepository.save(customer)).thenReturn(customer);
        assertEquals(java.util.Optional.of(1001), java.util.Optional.of(customerServiceImpl.createCustomer(customerBean).getStatusCode()));
    }
    @Test
    public void getCustomerList() {
        Mockito.when(customerRepository.findAll()).thenReturn(Stream.of(new Customer(1L, "customername", 9876543211L, 1, "test@gmail.com", 1, "password", "address", "unique", 1, "q1", "a1", "q2", "a2", "q3", "a3", 1)).collect(Collectors.toList()));
        assertEquals(java.util.Optional.of(1005), java.util.Optional.of(customerServiceImpl.getAllCustomer().getStatusCode()));
    }
    @Test
    public void getCustomer(){
        Mockito.when(customerRepository.findByCustomerUserName("unique")).thenReturn(customer);
        assertEquals(java.util.Optional.of(1010),java.util.Optional.of(customerServiceImpl.getCustomerByUserName(userName).getStatusCode()));
    }
}