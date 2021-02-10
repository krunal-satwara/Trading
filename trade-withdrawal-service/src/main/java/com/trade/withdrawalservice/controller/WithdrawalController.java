package com.trade.withdrawalservice.controller;

import com.trade.withdrawalservice.TradeWithdrawalServiceApplication;
import com.trade.withdrawalservice.bean.WithdrawalBean;
import com.trade.withdrawalservice.model.ErrorCode;
import com.trade.withdrawalservice.service.WithdrawalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/trade/withdrawal")
public class WithdrawalController {


    @Autowired
    private WithdrawalService withdrawalService;

    private static final Logger LOGGER = LoggerFactory.getLogger(TradeWithdrawalServiceApplication.class);

    @PostMapping("/create")
    public ErrorCode withdrawal(@Valid @RequestBody WithdrawalBean withdrawalBean) {
        LOGGER.info("Request For Create Withdrawal =" + withdrawalBean);
        return withdrawalService.withdrawal(withdrawalBean);
    }

    @GetMapping("/getWithdrawal/{withdrawalId}")
    public ErrorCode withdrawal(@PathVariable("withdrawalId") Long withdrawalId) {
        LOGGER.info("Request For Get Withdrawal =" + withdrawalId);
        return withdrawalService.withdrawal(withdrawalId);
    }

    @GetMapping("/withdrawals")
    public ErrorCode withdrawals() {
        LOGGER.info("Request For List Of Withdrawal");
        return withdrawalService.withdrawals();
    }

    @GetMapping("/getWithdrawalByCustomerID/{CustomerId}")
    public ErrorCode getWithdrawalByCustomerID(@PathVariable Long CustomerId) {
        LOGGER.info("Request For Get Withdrawal =" + CustomerId);
        return withdrawalService.getWithdrawalByCustomerID(CustomerId);
    }


    /*@PutMapping("/update")
    public ErrorCode updateWithdrawal(@Valid @RequestBody WithdrawalBean withdrawalBean) {
        LOGGER.info("Request For Update Of Withdrawal ="+ withdrawalBean);
        return withdrawalService.withdrawal(withdrawalBean);
    }*/
}
