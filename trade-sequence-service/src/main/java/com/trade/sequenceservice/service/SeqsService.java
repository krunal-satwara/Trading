package com.trade.sequenceservice.service;


import com.trade.sequenceservice.bean.SeqsBean;
import com.trade.sequenceservice.model.Seqs;

import java.util.List;

public interface SeqsService {

    long getSequence(String seqName);

    Seqs saveSequence(SeqsBean seqsBean);

    List<Seqs> getAllSeqRecord();
}
