package com.trade.transferhistoryservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trade.transferhistoryservice.bean.TransferHistoryBean;
import com.trade.transferhistoryservice.model.*;
import com.trade.transferhistoryservice.repository.TransferRepository;
import org.codehaus.jettison.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransferHistoryServiceImpl implements TransferHistoryService {

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private Environment environment;

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(TransferHistoryServiceImpl.class);

    HttpHeaders headers = new HttpHeaders();

    @Override
    public ErrorCode transferHistory(TransferHistoryBean transferHistoryBean) {

        TransferHistory transferHistory = new TransferHistory(transferHistoryBean.getTransferId(), transferHistoryBean.getAccountId(), transferHistoryBean.getAccountNumber(),
                transferHistoryBean.getCustomerId(),transferHistoryBean.getCustomerName(), transferHistoryBean.getTransactionTypeId(), transferHistoryBean.getTransferType()
                , transferHistoryBean.getBeforeBalance(), transferHistoryBean.getAfterBalance(), transferHistoryBean.getAmount()
                , transferHistoryBean.getNote(), transferHistoryBean.getCreateTime());

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(transferHistory, transferHistoryBean);
        try {
            TransferHistory resTransferHistory = transferRepository.save(transferHistory);
            LOGGER.info("Response For Create Customer =" + resTransferHistory);
            return new ErrorCode(5003, "Successfully Save Transfer History");
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        }
        return new ErrorCode(5004, "Not Save Transfer History");
    }

    @Override
    public ErrorCode transferHistory(Long transferId) {
        try {
            Optional<TransferHistory> transferHistory = transferRepository.findById(transferId);
            LOGGER.info("Response For Get Transfer History =" + transferHistory);
            return new ErrorCode(5005, "Get Transfer History", transferHistory);
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        }
        return new ErrorCode(5006, "Not Get Transfer History");
    }

    @Override
    public ErrorCode transferHistories() {
        try {
            List<TransferHistory> transferHistories = transferRepository.findAll();
            LOGGER.info("Response For List Of Transfer History =" + transferHistories);
            return new ErrorCode(5007, "List Of Transfer History", transferHistories);
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        }
        return new ErrorCode(5008, "Not Return List Of Transfer History");
    }

    @Override
    public ErrorCode saveTransferHistory(Deposit deposit) {

        Long beforeBalace = 0L;
        try {
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<Deposit> accountEntity1 = new HttpEntity<Deposit>(headers);
            LOGGER.info("Request For Update Deposit Amount At Account Service");
            ResponseEntity<String> responseOfAccount = restTemplate.exchange(environment.getProperty("get.account.url") + deposit.getAccountId(), HttpMethod.GET, accountEntity1, String.class);
            String accountResponse = responseOfAccount.getBody();
            LOGGER.info("Account Response =" + accountResponse);
            JSONObject jsonObject = new JSONObject(accountResponse);
            String accountResponseForDepositId = jsonObject.getJSONObject("object").toString();
            Account account = new ObjectMapper().readValue(accountResponseForDepositId, Account.class);
            beforeBalace = account.getAccountBalance();
            Long totalAccountAmount = account.getAccountBalance() + deposit.getAmount();
            account.setAccountBalance(totalAccountAmount);


            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<Account> accountEntity = new HttpEntity<Account>(account, headers);
            LOGGER.info("Request For Update Deposit Amount At Account Service");
            ResponseEntity<String> response = restTemplate.exchange(environment.getProperty("account.save.url"), HttpMethod.POST, accountEntity, String.class);
            LOGGER.info("Update Account Response", response.getBody());


        } catch (Exception exception) {
            LOGGER.error("{}", exception);
        }


        try {
            TransferHistory transferHistory = new TransferHistory();
            LOGGER.info("Request For Find The Information As Per Deposit Account From Database");
            transferHistory.setAccountId(deposit.getAccountId());
            transferHistory.setAccountNumber(deposit.getAccountNumber());
            transferHistory.setCustomerId(deposit.getCustomerId());
            transferHistory.setCustomerName(deposit.getCustomerName());
            transferHistory.setTransactionTypeId(deposit.getDepositId());
            transferHistory.setTransferType(deposit.getTransactionType());
            transferHistory.setBeforeBalance(beforeBalace);
            transferHistory.setAfterBalance(deposit.getAmount() + beforeBalace);
            transferHistory.setAmount(deposit.getAmount());
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<String> entity2 = new HttpEntity<String>(headers);
            LOGGER.info("Request For Sequence For Transfer History");
            ResponseEntity<Long> DepositSeqResponse = restTemplate.exchange(environment.getProperty("seq.url") + "transferHistory", HttpMethod.GET, entity2, Long.class);
            final Long transferId = DepositSeqResponse.getBody();
            LOGGER.info("Response From Sequence For Transfer Id = {}", transferId);
            transferHistory.setTransferId(transferId);
            transferHistory.setNote(deposit.getRemarks());
            transferHistory.setCreateTime(deposit.getCreateTime());

            TransferHistory savedTransferHistory = transferRepository.save(transferHistory);
            LOGGER.info("Transfer History Saved For Deposit =" + savedTransferHistory);
            return new ErrorCode(5001, "Successfully Saved The Data To Transfer History");

        } catch (Exception exception) {
            LOGGER.error("{}", exception);
        }
        return new ErrorCode(5002, "Opps ! Something Wrong Not Saved Transfer History");
    }

    @Override
    public ErrorCode saveTransferHistory(Withdrawal withdrawal) {
        Long beforeBalace = 0L;
        try {
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<Deposit> accountEntity1 = new HttpEntity<Deposit>(headers);
            LOGGER.info("Request For Update Withdrawal Amount At Account Service");
            ResponseEntity<String> responseOfAccount = restTemplate.exchange(environment.getProperty("get.account.url") + withdrawal.getAccountId(), HttpMethod.GET, accountEntity1, String.class);
            String accountResponse = responseOfAccount.getBody();
            LOGGER.info("Account Response =" + accountResponse);
            JSONObject jsonObject = new JSONObject(accountResponse);
            String accountResponseForDepositId = jsonObject.getJSONObject("object").toString();
            Account account = new ObjectMapper().readValue(accountResponseForDepositId, Account.class);
            beforeBalace = account.getAccountBalance();
            Long totalAccountAmount = account.getAccountBalance() - withdrawal.getAmount();
            account.setAccountBalance(totalAccountAmount);


            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<Account> accountEntity = new HttpEntity<Account>(account, headers);
            LOGGER.info("Request For Update Withdrawal Amount At Account Service");
            ResponseEntity<String> response = restTemplate.exchange(environment.getProperty("account.save.url"), HttpMethod.POST, accountEntity, String.class);
            LOGGER.info("Update Account Response", response.getBody());


        } catch (Exception exception) {
            LOGGER.error("{}", exception);
        }


        try {
            TransferHistory transferHistory = new TransferHistory();
            transferHistory.setAccountId(withdrawal.getAccountId());
            transferHistory.setAccountNumber(withdrawal.getAccountNumber());
            transferHistory.setCustomerId(withdrawal.getCustomerId());
            transferHistory.setCustomerName(withdrawal.getCustomerName());
            transferHistory.setTransactionTypeId(withdrawal.getWithdrawalId());
            transferHistory.setTransferType(withdrawal.getTransactionType());
            transferHistory.setBeforeBalance(beforeBalace);
            transferHistory.setAfterBalance(beforeBalace - withdrawal.getAmount());
            transferHistory.setAmount(withdrawal.getAmount());
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<String> entity2 = new HttpEntity<String>(headers);
            LOGGER.info("Request For Sequence For Transfer History");
            ResponseEntity<Long> DepositSeqResponse = restTemplate.exchange(environment.getProperty("seq.url") + "transferHistory", HttpMethod.GET, entity2, Long.class);
            final Long transferId = DepositSeqResponse.getBody();
            LOGGER.info("Response From Sequence For Transfer Id = {}", transferId);
            transferHistory.setTransferId(transferId);
            transferHistory.setNote(withdrawal.getRemarks());
            transferHistory.setCreateTime(withdrawal.getCreateTime());

            TransferHistory savedTransferHistory = transferRepository.save(transferHistory);
            LOGGER.info("Transfer History Saved For Withdrawal =" + savedTransferHistory);
            return new ErrorCode(5001, "Successfully Saved The Data To Transfer History");

        } catch (Exception exception) {
            LOGGER.error("{}", exception);
        }
        return new ErrorCode(5002, "Opps ! Something Wrong Not Saved Transfer History");
    }

    @Override
    public ErrorCode updateTransferHistory(Deposit deposit) {
        try {
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<Deposit> accountEntity1 = new HttpEntity<Deposit>(headers);
            LOGGER.info("Request For Update Deposit Amount At Account Service");
            ResponseEntity<String> responseOfAccount = restTemplate.exchange(environment.getProperty("get.account.url") + deposit.getAccountId(), HttpMethod.GET, accountEntity1, String.class);
            String accountResponse = responseOfAccount.getBody();
            LOGGER.info("Account Response =" + accountResponse);
            JSONObject jsonObject = new JSONObject(accountResponse);
            String accountResponseForDepositId = jsonObject.getJSONObject("object").toString();
            Account account = new ObjectMapper().readValue(accountResponseForDepositId, Account.class);
            Long beforeBalace = account.getAccountBalance();
            account.setAccountBalance(beforeBalace + deposit.getAmount());


            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<Account> accountEntity = new HttpEntity<Account>(account, headers);
            LOGGER.info("Request For Update Deposit Amount At Account Service");
            ResponseEntity<String> response = restTemplate.exchange(environment.getProperty("account.save.url"), HttpMethod.POST, accountEntity, String.class);
            System.out.println("Update Account Response =" + response.getBody());

            TransferHistory savedTransferHistory = new TransferHistory();
            List<TransferHistory> transferHistories = transferRepository.findAll();
            for (TransferHistory transferHistory : transferHistories) {
                if (transferHistory.getTransactionTypeId() == deposit.getDepositId()) {
                    transferHistory.setAfterBalance(beforeBalace + deposit.getAmount());
                    transferHistory.setAmount(deposit.getAmount());
                    savedTransferHistory = transferRepository.save(transferHistory);
                }
            }
            LOGGER.info("Transfer History Saved For Deposit =" + savedTransferHistory);
            return new ErrorCode(5003, "Successfully Update The Data To Transfer History");


        } catch (Exception exception) {
            LOGGER.error("{}", exception);
        }

        return new ErrorCode(5004, "Not Update The Data To Transfer History");
    }

    @Override
    public ErrorCode transferhistorieslist(Long customerId) {
        try {
            List<TransferHistory> transferHistoryList = transferRepository.findTransferHistoriesByCustomerId(customerId);
            LOGGER.info("Response For List Of TransferHistory =" + transferHistoryList);
            if (transferHistoryList.isEmpty()) {
                return new ErrorCode(5006, "Opps ! Not Return TransferHistory");
            } else {
                return new ErrorCode(5005, "Successfully Return TransferHistory", transferHistoryList);
            }
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        }
        return new ErrorCode(5006, "Opps ! Not Return TransferHistory");

    }

    @Override
    public ErrorCode transferHistorySearch(Search search) {
        try{
            Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(search.getStartDate());
            Date date2=new SimpleDateFormat("yyyy-MM-dd").parse(search.getEndDate());
            List<TransferHistory> transferHistoryList = transferRepository.findTransferHistoriesByCustomerIdAndCreateTimeIsBetween(search.getCustomerId(),DateUtils.convertToTimestamp(date1),DateUtils.convertToTimestamp(date2));
            return new ErrorCode(5055,"Successfully Return Search Transfer History",transferHistoryList);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return new ErrorCode(5056,"No Search Found");
    }
}



