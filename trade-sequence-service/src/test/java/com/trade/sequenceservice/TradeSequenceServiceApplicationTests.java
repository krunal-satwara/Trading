package com.trade.sequenceservice;

import com.trade.sequenceservice.bean.SeqsBean;
import com.trade.sequenceservice.model.Seqs;
import com.trade.sequenceservice.service.SeqsService;
import com.trade.sequenceservice.service.SeqsServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.when;
@SpringBootTest
public class TradeSequenceServiceApplicationTests {

    @MockBean
    private SeqsService seqsService;

    @Autowired
    private SeqsServiceImpl seqsServiceImpl;

    private static final Seqs seqs = new Seqs("seqName", 1);

    public SeqsBean seqsBean;

    @Before
    public void setUp(){
        seqsBean = new SeqsBean("before",4);
    }
    @Test
    public void saveSeq(){
        when(seqsService.saveSequence(seqsBean)).thenReturn(seqs);
        Assert.assertEquals(seqs , seqsServiceImpl.saveSequence(seqsBean));
    }

    /*@Test
    public void getSeq(){
        when(seqsRepository.findById(seqs.getSeqName())).thenReturn(java.util.Optional.of(seqs));
        assertEquals(seqs.getSeqValue() , seqsServiceImpl.getSequence(seqs.getSeqName()));
    }*/
}
