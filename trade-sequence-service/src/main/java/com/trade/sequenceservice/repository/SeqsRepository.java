package com.trade.sequenceservice.repository;

import com.trade.sequenceservice.model.Seqs;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeqsRepository extends MongoRepository<Seqs, String> {
    Optional<Seqs> findById(String seqName);

}
