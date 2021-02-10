package com.trade.transferhistoryservice.controller;

import com.trade.transferhistoryservice.bean.TransferHistoryBean;
import com.trade.transferhistoryservice.model.Deposit;
import com.trade.transferhistoryservice.model.ErrorCode;
import com.trade.transferhistoryservice.model.Search;
import com.trade.transferhistoryservice.model.Withdrawal;
import com.trade.transferhistoryservice.service.TransferHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/trade/transferHistory")
public class TransferHistoryController {

    @Autowired
    private TransferHistoryService transferHistoryService;

    private static final Logger LOGGER = LoggerFactory.getLogger(TransferHistoryController.class);

    @PostMapping("/create")
    public ErrorCode transferHistory(@Valid @RequestBody TransferHistoryBean transferHistoryBean) {
        LOGGER.info("Request For Create Transfer History =" + transferHistoryBean);
        return transferHistoryService.transferHistory(transferHistoryBean);
    }

    @GetMapping("/getTransferHistory/{transferId}")
    public ErrorCode transferHistory(@PathVariable("transferId") Long transferId) {
        LOGGER.info("Request For Get Transfer History =" + transferId);
        return transferHistoryService.transferHistory(transferId);
    }

    @GetMapping("/transferHistories")
    public ErrorCode transferHistories() {
        LOGGER.info("Request For List Of Transfer History ");
        return transferHistoryService.transferHistories();
    }

    @GetMapping("/transferhistorieslist/{customerId}")
    public ErrorCode transferhistorieslist(@PathVariable Long customerId){
        LOGGER.info("Request For List Of Transfer History By Customer ID");
        return transferHistoryService.transferhistorieslist(customerId);

    }

    /*@PutMapping("/update")
    public ErrorCode updatetransferHistory(@Valid @RequestBody TransferHistoryBean transferHistoryBean) {
        LOGGER.info("Request For Update Of Transfer History =" + transferHistoryBean);
        return transferHistoryService.transferHistory(transferHistoryBean);
    }*/

    @PutMapping("/saveDepositTransferHistory")
    public ErrorCode saveDepositTransferHistory(@RequestBody Deposit deposit){
        LOGGER.info("Request For Save Of Deposit Transfer History");
        return transferHistoryService.saveTransferHistory(deposit);
    }

    /*@PostMapping("/updateDepositTransferHistory")
    public ErrorCode updateDepositTransferHistory(@RequestBody Deposit Deposit) {
        LOGGER.info("Request For Update Of Deposit Transfer History");
        return transferHistoryService.updateTransferHistory(Deposit);
    }*/


    @PutMapping("/saveWithdrawalTransferHistory")
    public ErrorCode saveWithdrawalTransferHistory(@RequestBody Withdrawal withdrawal){
        LOGGER.info("Request For Save Of Deposit Transfer History");
        return transferHistoryService.saveTransferHistory(withdrawal);
    }

    @PostMapping("/searchTransferHistories")
    public ErrorCode transferHistorySearch(@RequestBody Search search) throws ParseException {
        return transferHistoryService.transferHistorySearch(search);
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
