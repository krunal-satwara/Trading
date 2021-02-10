package com.trade.accountservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.trade.accountservice.bean.AccountBean;
import com.trade.accountservice.model.Account;
import com.trade.accountservice.repository.AccountRepository;
import com.trade.accountservice.service.AccountServiceImpl;
import org.codehaus.jettison.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountTradeServiceApplicationTests {

    private static final Account account = new Account(0L, 23L, 101010L, "accountname", 1, 100000L, 1, "remarks");

    private static AccountBean accountBean = new AccountBean(0L, 23L, 101010L, "accountname", 1, 100000L, 1, "remarks");;

    @Autowired
    private AccountServiceImpl accountServiceImpl;

    @MockBean
    private AccountRepository accountRepository;

    @Test
    public void getAccountList() {
        when(accountRepository.findAll()).thenReturn(Stream.of(new Account(0L, 22L, 101010L, "accountname", 1, 100000L, 1, "remarks")).collect(Collectors.toList()));
        assertEquals(java.util.Optional.of(2003), java.util.Optional.of(accountServiceImpl.accounts().getStatusCode()));
    }

    @Test
    public void getAccountByAccountNumber() {
        Long accountNumber = account.getAccountNumber();
        when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(account);
        assertEquals(java.util.Optional.of(2006), java.util.Optional.of(accountServiceImpl.account(accountNumber).getStatusCode()));
    }

    @Test
    public void createAccount() throws JsonProcessingException, JSONException {
        when(accountRepository.save(account)).thenReturn(account);
        assertEquals(java.util.Optional.of(2001),java.util.Optional.of( accountServiceImpl.account(accountBean).getStatusCode()));
    }

    /*@Test
    public void deleteAccount() throws JsonProcessingException, JSONException {
        assertEquals(java.util.Optional.of(2007),java.util.Optional.of(accountServiceImpl.deleteAccount(1L).getStatusCode()));

    }*/

}
