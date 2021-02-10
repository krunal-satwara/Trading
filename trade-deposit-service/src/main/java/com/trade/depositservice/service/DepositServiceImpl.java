package com.trade.depositservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trade.depositservice.bean.DepositBean;
import com.trade.depositservice.model.Account;
import com.trade.depositservice.model.Deposit;
import com.trade.depositservice.model.ErrorCode;
import com.trade.depositservice.repository.DepositRespository;
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
public class DepositServiceImpl implements DepositService {


    @Autowired
    private DepositRespository depositRespository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment environment;

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(DepositServiceImpl.class);

    HttpHeaders headers = new HttpHeaders();


    @Override
    public ErrorCode deposit(DepositBean depositBean) {

        Deposit deposit = new Deposit(depositBean.getDepositId(), depositBean.getAccountId(), depositBean.getAccountNumber(), depositBean.getCustomerId(), depositBean.getCustomerName(), depositBean.getAmount(), depositBean.getTransactionType(), depositBean.getCreateTime(), depositBean.getExecuteTIme(), depositBean.getStatus(), depositBean.getRemarks());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(deposit, depositBean);

        try{
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<Deposit> accountEntity1 = new HttpEntity<Deposit>(headers);
            LOGGER.info("Request For Check Account Status");
            ResponseEntity<String> responseOfAccount = restTemplate.exchange(environment.getProperty("get.account.url") + deposit.getAccountId(), HttpMethod.GET, accountEntity1, String.class);
            String accountResponse = responseOfAccount.getBody();
            LOGGER.info("Account Response =" + accountResponse);
            JSONObject jsonObject = new JSONObject(accountResponse);
            String accountResponseForDepositId = jsonObject.getJSONObject("object").toString();
            Account account = new ObjectMapper().readValue(accountResponseForDepositId, Account.class);
            if (account.getAccountStatus() == 0) {
                return new ErrorCode(3009, "Account is not Activate");
            }
        }catch (Exception exception){
            LOGGER.error("{}",exception);
            return new ErrorCode(3009, "Account is not Activate");
        }


        try {
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<String> entity2 = new HttpEntity<String>(headers);
            LOGGER.info("Request For Get Sequence For Deposit");
            ResponseEntity<Long> DepositSeqResponse = restTemplate.exchange(environment.getProperty("seq.url") + "deposit", HttpMethod.GET, entity2, Long.class);
            final Long depositId = DepositSeqResponse.getBody();
            LOGGER.info("Response From Sequence For Deposit Id = {}", depositId);
            deposit.setDepositId(depositId);
            deposit.setCreateTime(DateUtils.convertToTimestamp(new Date()));
            deposit.setExecuteTIme(DateUtils.convertToTimestamp(new Date()));
            Deposit resDeposit = depositRespository.save(deposit);
            LOGGER.info("Response For Save Deposit =" + resDeposit);
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        }

        try{
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<Deposit> entity3 = new HttpEntity<Deposit>(deposit, headers);
            LOGGER.info("Request For Update Deposit Transfer History");
            ResponseEntity<String> transferResponse = restTemplate.exchange(environment.getProperty("transfer.history.deposit.url"), HttpMethod.PUT, entity3, String.class);
            System.out.println("Deposit Transfer History Response =" + transferResponse.getBody());
            return new ErrorCode(3001, "Amount Deposited Successfully");
        }catch (Exception exception){
            LOGGER.error("{}",exception);
        }

        return new ErrorCode(3002, "Opps ! Something Wrong Amount Not Deposited");
    }

    @Override
    public ErrorCode updateDeposit(DepositBean depositBean) {
        Deposit deposit = new Deposit(depositBean.getDepositId(), depositBean.getAccountId(), depositBean.getAccountNumber(), depositBean.getCustomerId(), depositBean.getCustomerName(), depositBean.getAmount(), depositBean.getTransactionType(), depositBean.getCreateTime(), depositBean.getExecuteTIme(), depositBean.getStatus(), depositBean.getRemarks());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(deposit, depositBean);
        try {
            Deposit resDeposit = depositRespository.save(deposit);
            LOGGER.info("Response For Create Deposit =" + resDeposit);

            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<Deposit> entity3 = new HttpEntity<Deposit>(resDeposit, headers);
            LOGGER.info("Request For Update Deposit Transfer History");
            ResponseEntity<String> response = restTemplate.exchange(environment.getProperty("transfer.history.update.deposit.url"), HttpMethod.POST, entity3, String.class);
            System.out.println("Update Deposit Transfer History Response =" + response.getBody());

            return new ErrorCode(3007, "Updated Deposit Successfully");
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        }
        return new ErrorCode(3008, "Opps ! Something Wrong Deposit Not Updated");
    }

    @Override
    public ErrorCode deposit(Long depositId) {
        try {
            Optional<Deposit> deposit = depositRespository.findById(depositId);
            LOGGER.info("Get Deposit =" + deposit);
            return new ErrorCode(3003, "Successfully Retrieve Deposit Details", deposit);
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        }
        return new ErrorCode(3004, "Opps ! Not Retrieve Deposit Details");
    }

    @Override
    public ErrorCode deposits() {
        try {
            List<Deposit> deposits = depositRespository.findAll();
            LOGGER.info("Response For List Of Deposits =" + deposits);
            return new ErrorCode(3005, "Successfully Retrieve Deposit List", deposits);
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        }
        return new ErrorCode(3006, "Not Retrieve Deposit List");
    }

    @Override
    public ErrorCode getAllByCustomerId(Long customerId) {
        try{
            List<Deposit> depositList = depositRespository.getAllByCustomerId(customerId);
            LOGGER.info("Response For List Of Deposits By CustomerID =" + depositList);
            if (depositList.isEmpty()){
                return new ErrorCode(3008, "Not Retrieve Deposit List By CustomerID",depositList);
            }
            else {
                return new ErrorCode(3007, "Successfully Retrieve Deposit List By CustomerID", depositList);
            }
        }catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        }
        return new ErrorCode(3008, "Not Retrieve Deposit List By CustomerID");
    }

}
