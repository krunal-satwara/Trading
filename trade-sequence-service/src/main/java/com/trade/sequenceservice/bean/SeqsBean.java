package com.trade.sequenceservice.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeqsBean {

    @NotEmpty(message = "Sequence Name is required")
    private String seqName;
    @NotNull(message = "Sequence Value Must Not Be Empty")
    private long seqValue;
}
