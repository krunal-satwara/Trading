package com.trade.withdrawalservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trade.withdrawalservice.bean.WithdrawalBean;
import com.trade.withdrawalservice.model.Account;
import com.trade.withdrawalservice.model.ErrorCode;
import com.trade.withdrawalservice.model.Withdrawal;
import com.trade.withdrawalservice.repository.WithdrawalRepository;
import org.codehaus.jettison.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class WithdrawalServiceImpl implements WithdrawalService {

    @Autowired
    private WithdrawalRepository withdrawalRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment environment;

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(WithdrawalServiceImpl.class);

    HttpHeaders headers = new HttpHeaders();

    @Override
    public ErrorCode withdrawal(WithdrawalBean withdrawalBean) {

        Withdrawal withdrawal = new Withdrawal(withdrawalBean.getWithdrawalId(),withdrawalBean.getAccountId(),withdrawalBean.getAccountNumber(),withdrawalBean.getCustomerId(),withdrawalBean.getCustomerName(), withdrawalBean.getCreateTime()
                , withdrawalBean.getExecuteTIme(), withdrawalBean.getAmount(), withdrawalBean.getBankName()
                , withdrawalBean.getBankCode(), withdrawalBean.getStatus(), withdrawalBean.getRemarks(), withdrawalBean.getTransactionType());

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(withdrawal, withdrawalBean);


        try {
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<Withdrawal> accountEntity1 = new HttpEntity<Withdrawal>(headers);
            LOGGER.info("Request For Check Account Status");
            ResponseEntity<String> responseOfAccount = restTemplate.exchange(environment.getProperty("get.account.url") + withdrawal.getAccountId(), HttpMethod.GET, accountEntity1, String.class);
            String accountResponse = responseOfAccount.getBody();
            LOGGER.info("Account Response =" + accountResponse);
            JSONObject jsonObject = new JSONObject(accountResponse);
            String accountResponseForDepositId = jsonObject.getJSONObject("object").toString();
            Account account = new ObjectMapper().readValue(accountResponseForDepositId, Account.class);
            if (account.getAccountStatus() == 0) {
                return new ErrorCode(3009, "Account is not Activate");
            }
        } catch (Exception exception) {
            LOGGER.error("{}", exception);
            return new ErrorCode(3009, "Account is not Activate");
        }


        try {
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<String> entity2 = new HttpEntity<String>(headers);
            LOGGER.info("Request For Get Sequence For Withdrawal");
            ResponseEntity<Long> DepositSeqResponse = restTemplate.exchange(environment.getProperty("seq.url") + "withdrawal", HttpMethod.GET, entity2, Long.class);
            final Long depositId = DepositSeqResponse.getBody();
            LOGGER.info("Response From Sequence For Withdrawal Id = {}", depositId);
            withdrawal.setWithdrawalId(depositId);
            withdrawal.setCreateTime(DateUtils.convertToTimestamp(new Date()));
            withdrawal.setExecuteTime(DateUtils.convertToTimestamp(new Date()));
            Withdrawal resWithdrawal = withdrawalRepository.save(withdrawal);
            LOGGER.info("Response For Create Withdrawal =" + resWithdrawal);
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        }


        try {
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<Withdrawal> entity3 = new HttpEntity<Withdrawal>(withdrawal, headers);
            LOGGER.info("Request For Update Deposit Transfer History");
            ResponseEntity<String> transferResponse = restTemplate.exchange(environment.getProperty("transfer.history.withdrawal.url"), HttpMethod.PUT, entity3, String.class);
            System.out.println("Deposit Transfer History Response =" + transferResponse.getBody());
            return new ErrorCode(4001, "Amount Withdraw Successfully", withdrawal);
        } catch (Exception exception) {
            LOGGER.error("{}", exception);
        }

        return new ErrorCode(4002, "Opps ! Amount Not Withdraw");
    }

    @Override
    public ErrorCode withdrawal(Long withdrawalId) {
        try {
            Optional<Withdrawal> withdrawal = withdrawalRepository.findById(withdrawalId);
            LOGGER.info("Response For Get Withdrawal =" + withdrawal);
            return new ErrorCode(4003, "Get Withdrawal", withdrawal);
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        }
        return new ErrorCode(4004, "Did Not Get Withdrawal For WithdrawalId =" + withdrawalId);
    }

    @Override
    public ErrorCode withdrawals() {
        try {
            List<Withdrawal> withdrawals = withdrawalRepository.findAll();
            LOGGER.info("Response For List Of Withdrawals =" + withdrawals);
            return new ErrorCode(4005, "Successfully Return Withdrawals", withdrawals);
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        }
        return new ErrorCode(4006, "Opps ! Not Return Withdrawals");
    }

    @Override
    public ErrorCode getWithdrawalByCustomerID(Long customerId) {
        try {
            List<Withdrawal> withdrawalList = withdrawalRepository.findWithdrawalByCustomerId(customerId);
            LOGGER.info("Response For List Of Withdrawals =" + withdrawalList);
            if (withdrawalList.isEmpty()) {
                return new ErrorCode(4008, "Opps ! Not Return Withdrawals");
            } else {
                return new ErrorCode(4007, "Successfully Return Withdrawals", withdrawalList);
            }
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        }
        return new ErrorCode(4008, "Opps ! Not Return Withdrawals");

    }


}
