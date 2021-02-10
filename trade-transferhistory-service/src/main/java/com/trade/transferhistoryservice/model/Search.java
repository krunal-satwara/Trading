package com.trade.transferhistoryservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Search {
    private Long customerId;
    private String startDate;
    private String endDate;
}
