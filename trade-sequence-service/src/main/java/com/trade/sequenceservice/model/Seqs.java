package com.trade.sequenceservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Seqs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seqs {

    @Id
    private String seqName;
    private long seqValue;

}
