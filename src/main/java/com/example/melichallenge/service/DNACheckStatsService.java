package com.example.melichallenge.service;

import com.example.melichallenge.model.DNACheckStats;
import com.example.melichallenge.repository.DNACheckStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@Service
public class DNACheckStatsService {
    @Autowired
    private DNACheckStatsRepository repository;

    public DNACheckStats get() {
        return this.repository.findById(1L).get();
    }

    @Transactional
    public int incrementHumanCountFromValue(BigInteger lockReferenceValue) {
        return this.repository.incrementHumanCountFromValue(lockReferenceValue);
    }

    @Transactional
    public int incrementSimianCountFromValue(BigInteger lockReferenceValue) {
        return this.repository.incrementSimianCountFromValue(lockReferenceValue);
    }

    @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
    public DNACheckStats findById() {
        return this.repository.findById(1L).get();
    }
}
