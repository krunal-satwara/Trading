package com.trade.sequenceservice.controller;

import com.trade.sequenceservice.bean.SeqsBean;
import com.trade.sequenceservice.model.Seqs;
import com.trade.sequenceservice.service.SeqsService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/trade/seq")
@Api(value = "/trade/seq")
public class SeqsController {

    @Autowired
    SeqsService seqsService;

    private static final Logger LOGGER = LoggerFactory.getLogger(SeqsController.class);

    @GetMapping("/getSeq/{seqName}")
    public Long getSequence(@PathVariable String seqName) {
        LOGGER.info("Request For Get Sequence Id Based On SeqName =" + seqName);
        return seqsService.getSequence(seqName);
    }

    @PostMapping("/setSeq/{seqName}/{seqValue}")
    public Seqs saveSequence(@Valid @RequestBody SeqsBean seqsBean) {
        LOGGER.info("Request For Save Sequence =" + seqsBean);
        return seqsService.saveSequence(seqsBean);
    }

    @GetMapping("/getAllSeq")
    public List<Seqs> getAllSeqRecord() {
        return seqsService.getAllSeqRecord();
    }
}
