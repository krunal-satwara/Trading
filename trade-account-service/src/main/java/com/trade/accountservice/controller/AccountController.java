package com.trade.accountservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.trade.accountservice.bean.AccountBean;
import com.trade.accountservice.model.Deposit;
import com.trade.accountservice.model.ErrorCode;
import com.trade.accountservice.service.AccountService;
import org.codehaus.jettison.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Path;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/trade/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    @GetMapping("/{accountId}")
    public ErrorCode account(@PathVariable("accountId") Long accountId) {
        LOGGER.info("Request For Get AccountID =" + accountId);
        return accountService.account(accountId);
    }

    /*@GetMapping("/{accountNumber}")
    public ErrorCode getAccountUsingAccountNumber(@PathVariable("accountNumber") Long accountNumber) {
        LOGGER.info("Request For Get AccountID =" + accountNumber);
        return accountService.account(accountNumber);
    }*/

    @PostMapping("/create")
    public ErrorCode account(@Valid @RequestBody AccountBean accountBean) throws JsonProcessingException, JSONException {
        LOGGER.info("Request For Create Account =" + accountBean);
        return accountService.account(accountBean);
    }

    @PutMapping("/update")
    public ErrorCode updateAccount(@Valid @RequestBody AccountBean accountBean) throws JsonProcessingException, JSONException {
        LOGGER.info("Request For Update Account =" + accountBean);
        return accountService.account(accountBean);
    }

    @GetMapping("/accounts")
    public ErrorCode accounts() {
        LOGGER.info("Request For List Of Accounts");
        return accountService.accounts();
    }

    @DeleteMapping("/delete/{accountId}")
    public ErrorCode deleteAccount(@PathVariable("accountId") Long accountId) {
        LOGGER.info("Request For Delete Of Account =" + accountId);
        return accountService.deleteAccount(accountId);
    }

    @PutMapping("/activate/{accountId}")
    public ErrorCode activateAccount(@PathVariable("accountId") Long accountId) {
        LOGGER.info("Request For Activate Of Account =" + accountId);
        return accountService.activateAccount(accountId);
    }

    @PutMapping("/updateDepositAmount")
    public ErrorCode updateDepositAmountForAccount(@RequestBody Deposit deposit) {
        LOGGER.info("Request For Update Deposit Amount For Account =" + deposit);
        return accountService.updateDepositAmountForAccount(deposit);
    }

    @GetMapping("/getByAccount/{customerId}")
    public ErrorCode getAccountRecordByCustomerId(@PathVariable("customerId") Long customerId) {
        LOGGER.info("Request For Get Account Record By Customer ID =" + customerId);
        return accountService.findAccountDetailByCustomerId(customerId);
    }
    @GetMapping("/getAccountDetail/{AccountNumber}")
    public  ErrorCode getAccountDetailByAccountNumber(@PathVariable Long AccountNumber){
        LOGGER.info("Request for get account detail by Account Number");
        return accountService.findAccountDetailByAccountNumber(AccountNumber);
    }
    @GetMapping("/getWithdrawalByTwoID/{CustomerId}/{AccountId}")
    public ErrorCode getWithdrawalByTwoID(@PathVariable Long CustomerId, @PathVariable Long AccountId){
        return accountService.getWithdrawalByTwoID(CustomerId,AccountId);
    }

    @GetMapping("/getAllActiveAccounts/{customerId}")
    public ErrorCode getAllAccountByActivatedStatus(@PathVariable("customerId") Long customerId) {
        LOGGER.info("Request For Get Account Record Based On Status is Active =" + customerId);
        return accountService.findAllAccountDetailByStatus(customerId);
    }

    /*@GetMapping("/getAccountNameByCustomerId/{accountNumber}/{customerId}")
    public ErrorCode getRecordAccountNumberByCustomerId(@PathVariable("accountNumber") Long accountNumber , @PathVariable("customerId") Long customerId){
        LOGGER.info("Request For Get Account Record Based on Account Number =" + accountNumber + " customer id is :--- " + customerId);
        return accountService.findAccountNameByCustomerId(accountNumber,customerId);
    }*/

    @GetMapping("/getAllAccountIdByCustomerId/{customerId}")
    public ErrorCode getByCustomerId(@PathVariable("customerId") Long customerId){
        LOGGER.info("Request For Get Account ID List Based On Customer ID");
        return accountService.findByCustomerId(customerId);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, @PathVariable String AccountNumber) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return errors;
    }

    @GetMapping("/generateAccountNumber")
    public ErrorCode generateAccountNumber(){
        return accountService.generateAccountNumber();
    }

}
