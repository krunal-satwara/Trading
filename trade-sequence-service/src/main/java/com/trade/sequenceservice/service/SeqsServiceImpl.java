package com.trade.sequenceservice.service;

import com.trade.sequenceservice.bean.SeqsBean;
import com.trade.sequenceservice.model.Seqs;
import com.trade.sequenceservice.repository.SeqsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;


@Service
public class SeqsServiceImpl implements SeqsService {

    @Autowired
    private SeqsRepository seqsRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(SeqsServiceImpl.class);

    public long getSequence(String seqName) {
        try {
            Optional<Seqs> seqs2 = seqsRepository.findById(seqName);
            LOGGER.info("Get Sequence Details Based On SeqName =" + seqs2);

            if (seqs2.isPresent()) {
                long seqValue = seqs2.get().getSeqValue();
                seqs2.get().setSeqValue(seqValue + 1);
                Seqs seqs = seqsRepository.save(seqs2.get());
                LOGGER.info("Response After Update the Sequence value =" + seqs);
                return seqs2.get().getSeqValue();
            } else {
                Seqs seqs1 = new Seqs();
                seqs1.setSeqName(seqName);
                seqs1.setSeqValue(1);
                Seqs seqs = seqsRepository.save(seqs1);
                LOGGER.info("Response After First Time Save SeqName =" + seqs);
                return seqs1.getSeqValue();
            }
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        }
        return 0;
    }

    @Override
    public Seqs saveSequence(SeqsBean seqsBean) {
        Seqs seqs = new Seqs(seqsBean.getSeqName(), seqsBean.getSeqValue());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(seqs, seqsBean);
        try {
            Seqs resSave = seqsRepository.save(seqs);
            LOGGER.info("Response For Save Sequence =" + resSave);
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        }
        return null;
    }

    @Override
    public List<Seqs> getAllSeqRecord() {
        return seqsRepository.findAll();
    }

}


