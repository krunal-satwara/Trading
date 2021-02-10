package com.trade.accountservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.trade.accountservice.bean.AccountBean;
import com.trade.accountservice.model.Account;
import com.trade.accountservice.model.Deposit;
import com.trade.accountservice.model.ErrorCode;
import com.trade.accountservice.repository.AccountRepository;
import org.codehaus.jettison.json.JSONException;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment environment;


    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);
    HttpHeaders headers = new HttpHeaders();

    String authToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdHJpbmciLCJleHAiOjE1OTU3MjczMzIsImlhdCI6MTU5MzA5OTMzMn0.bDE0-VI58RjKQvgq4hEKQ61Y2bP0M5QJHTHzyQ4FDU4";

    @Override
    public ErrorCode account(AccountBean accountBean) throws JsonProcessingException, JSONException {
        Account account = new Account(accountBean.getAccountId(), accountBean.getCustomerId(), accountBean.getAccountNumber()
                , accountBean.getAccountName(), accountBean.getAccountType(), accountBean.getAccountBalance(), accountBean.getAccountStatus()
                , accountBean.getAccountRemarks());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(account, accountBean);
        List<Account> accounts = accountRepository.findAll();

        if (account.getAccountId() == null) {
            if (true) {
                for (Account account1 : accounts) {
                    if (accountBean.getAccountNumber().equals(account1.getAccountNumber())) {
                        return new ErrorCode(2009, "Account Name Already Exits");
                    }
                }
            }
        }

        try {
            if (account.getAccountId() == null || account.getAccountId() == 0) {
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
                headers.set("Authorization", "Token " + authToken);
                HttpEntity<String> entity2 = new HttpEntity<String>(headers);
                LOGGER.info("Request For Sequence For Customer");
                ResponseEntity<Long> AccountSeqResponse = restTemplate.exchange(environment.getProperty("seq.url") + "account", HttpMethod.GET, entity2, Long.class);
                final Long accountId = AccountSeqResponse.getBody();
                LOGGER.info("Response From Sequence For Account Id = {}", accountId);
                account.setAccountId(accountId);
                Account resAccount = accountRepository.save(account);
                LOGGER.info("Response For Create Account =" + resAccount);
                return new ErrorCode(2001, "Account Created Successfully", accountId);
            } else {
                Account resAccount = accountRepository.save(account);
                LOGGER.info("Response For Create Account =" + resAccount);
                return new ErrorCode(2015, "Account Update Successfully");
            }

        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        }
        return new ErrorCode(2002, "Opps ! Account Not Created");
    }

    @Override
    public ErrorCode accounts() {
        try {
            List<Account> accounts = accountRepository.findAll();
            LOGGER.info("Response For List Of Account =" + accounts);
            return new ErrorCode(2003, "Successfully Received Accounts", accounts);
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        }
        return new ErrorCode(2004, "Opps ! Accounts Not Received");
    }

    @Override
    public ErrorCode account(Long accountId) {
        try {
            Account account = accountRepository.findByAccountId(accountId);
            LOGGER.info("Response For Get Account =" + account);
            if (account != null) {
                return new ErrorCode(2005, "Successfully Received Account Details", account);
            } else {
                return new ErrorCode(2006, "No Account Details Found", account);
            }
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        }
        return new ErrorCode(2006, "Opps ! Account Details Not Received");
    }

    @Override
    public ErrorCode getAccountUsingAccountNumber(Long accountNumber) {
        try {
            Account account = accountRepository.findByAccountNumber(accountNumber);
            LOGGER.info("Response For Get Account =" + account);
            if (account != null) {
                return new ErrorCode(2005, "Successfully Received Account Details", account);
            } else {
                return new ErrorCode(2006, "No Account Details Found", account);
            }
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        }
        return new ErrorCode(2006, "Opps ! Account Details Not Received");
    }

    @Override
    public ErrorCode deleteAccount(Long accountId) {
        try {
            Account account = accountRepository.findByAccountId(accountId);
            LOGGER.info("Get Account For Delete =" + account);
            account.setAccountStatus(0);
            account = accountRepository.save(account);
            LOGGER.info("Delete Account =" + account);
            if (account != null) {
                return new ErrorCode(2007, "Account Deleted Successfully");
            } else {
                return new ErrorCode(2008, "Opps ! Account Not Deleted");
            }
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        }
        return new ErrorCode(2008, "Opps ! Account Not Deleted");
    }

    @Override
    public ErrorCode updateDepositAmountForAccount(Deposit deposit) {
        try {
            LOGGER.info("Request to Fetch Information Based On Account Id & Customer Id" + deposit);
            Account account = accountRepository.findByAccountIdAndCustomerId(deposit.getAccountId(), deposit.getCustomerId());
            LOGGER.info("Retrieve Information Based On Account Id & Customer Id" + account);
            account.setAccountBalance(deposit.getAmount());
            Account savedAccount = accountRepository.save(account);
            if (savedAccount != null) {
                return new ErrorCode(2011, "Successfully Updated Deposit Amount For Account");
            } else {
                return new ErrorCode(2012, "Not Updated Deposit Amount For Account");
            }
        } catch (Exception exception) {
            LOGGER.error("{}", exception);
        }
        return new ErrorCode(2012, "Not Updated Deposit Amount For Account");
    }

    @Override
    public ErrorCode activateAccount(Long accountId) {
        try {
            LOGGER.info("Request to Fetch Information Based On Account Id" + accountId);
            Account account = accountRepository.findByAccountId(accountId);
            account.setAccountStatus(1);
            Account saveResponse = accountRepository.save(account);
            if (saveResponse != null) {
                return new ErrorCode(2013, "Successfully Activate Account");
            } else {
                return new ErrorCode(2014, "No Account Found");
            }
        } catch (Exception exception) {
            LOGGER.error("{}", exception);
        }
        return new ErrorCode(2014, "Not Activate Account");
    }

    @Override
    public ErrorCode findAccountDetailByCustomerId(Long customerId) {
        try {
            List<Account> account = accountRepository.findByCustomerId(customerId);
            System.out.println("list from service impl :-------" + account);
            LOGGER.info("Response For Get Account =" + account);
            if (account != null) {
                return new ErrorCode(2015, "Successfully Received Account Details From Customer ID", account);
            } else {
                return new ErrorCode(2016, "No Account Details Found From Customer ID", account);
            }
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        }
        return new ErrorCode(2017, "Opps ! Account Details Not Received From Customer ID");
    }

    @Override
    public ErrorCode findAccountDetailByAccountNumber(Long accountNumber) {
        try {
            Account account = accountRepository.findAccountByAccountNumber(accountNumber);
            LOGGER.info("Response For the Customer By User Name" + accountNumber);
            if (account != null) {
                return new ErrorCode(2018, "Successfully Get Account By Account Number", account);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return new ErrorCode(2019, "Opps !! No Record Found");

    }

    @Override
    public ErrorCode getWithdrawalByTwoID(Long customerId, Long accountId) {
        try {
            List<Account> withdrawalListByTwoId = accountRepository.findAccountByCustomerIdAndAccountId(customerId, accountId);
            LOGGER.info("Response For List Of Withdrawals =" + withdrawalListByTwoId);
            if (withdrawalListByTwoId.isEmpty()) {
                return new ErrorCode(2021, "Opps ! Not Return Withdrawals");
            } else {
                return new ErrorCode(2020, "Successfully Return Withdrawals", withdrawalListByTwoId);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return new ErrorCode(2021, "Opps ! Not Return Withdrawals");
    }

    public ErrorCode findAllAccountDetailByStatus(Long customerId) {
        try {
            List<Account> accounts = accountRepository.findAllByAccountStatusAndCustomerId(1, customerId);
            System.out.println("accounts list of status ====1  " + accounts);
            if (accounts.isEmpty()) {
                return new ErrorCode(2019, "No Account Details Found Based on Status", accounts);
            } else {
                return new ErrorCode(2018, "Successfully Received Account Details Based on Status", accounts);
            }
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        }
        return new ErrorCode(2020, "Opps ! Account Details Not Received Based on Status");
    }

    public ErrorCode findByCustomerId(Long customerId) {
        try {
            List<Account> accounts = accountRepository.findByCustomerId(customerId);
            System.out.println("accounts id base list ==== :   " + accounts);
            if (accounts != null) {
                return new ErrorCode(2021, "Successfully Received Account Ids Based on Customer ID", accounts);
            } else {
                return new ErrorCode(2022, "No Account Id exists Based on Customer ID", accounts);
            }
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        }

        return new ErrorCode(2023, "Opps ! Account id Found Based on Customer ID");
    }


    public ErrorCode generateAccountNumber() {
        Long accountNumberPattern = 9678558953L;
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", "Token " + authToken);
        HttpEntity<String> entity2 = new HttpEntity<String>(headers);
        LOGGER.info("Request For Generate Account Number");
        ResponseEntity<Long> AccountSeqResponse = restTemplate.exchange(environment.getProperty("seq.url") + "accountNumber", HttpMethod.GET, entity2, Long.class);
        final Long accountNumber = AccountSeqResponse.getBody();
        Long finalAccountNumber = accountNumberPattern + accountNumber;
        System.out.println("Final Account Number =" + finalAccountNumber);
        return new ErrorCode(2030, "Account Number Generated", finalAccountNumber);
    }

}
