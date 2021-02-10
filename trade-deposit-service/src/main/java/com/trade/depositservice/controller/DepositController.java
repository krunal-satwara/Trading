package com.trade.depositservice.controller;

import com.trade.depositservice.bean.DepositBean;
import com.trade.depositservice.model.ErrorCode;
import com.trade.depositservice.service.DepositService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/trade/deposit")
public class DepositController {

    @Autowired
    private DepositService depositService;

    private static final Logger LOGGER = LoggerFactory.getLogger(DepositController.class);

    @PostMapping("/create")
    public ErrorCode deposit(@Valid @RequestBody DepositBean depositBean) {
        LOGGER.info("Request For Create Deposit =" + depositBean);
        return depositService.deposit(depositBean);
    }

    @GetMapping("/getDeposite/{depositId}")
    public ErrorCode deposit(@PathVariable("depositId") Long depositId) {
        LOGGER.info("Request For Get Deposit =" + depositId);
        return depositService.deposit(depositId);
    }

    @GetMapping("/deposits")
    public ErrorCode deposits() {
        LOGGER.info("Request For Get List Of Deposits");
        return depositService.deposits();
    }

    @GetMapping("/getAllByCustomer/{customerId}")
    public ErrorCode findAllByCustomerId(@PathVariable("customerId") Long customerId) {
        LOGGER.info("Request For Get List Of Deposits By Customer ID");
        return depositService.getAllByCustomerId(customerId);
    }

}
