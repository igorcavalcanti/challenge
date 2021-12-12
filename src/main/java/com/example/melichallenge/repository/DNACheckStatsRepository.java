package com.example.melichallenge.repository;

import com.example.melichallenge.model.DNACheckStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;

public interface DNACheckStatsRepository extends JpaRepository<DNACheckStats, Long> {

    @Modifying
    @Query("update DNACheckStats stats set stats.humansCount = stats.humansCount + 1 where stats.humansCount = :referenceValue")
    public int incrementHumanCountFromValue(BigInteger referenceValue);

    @Modifying
    @Query("update DNACheckStats stats set stats.simiansCount = stats.simiansCount + 1 where stats.simiansCount = :referenceValue")
    public int incrementSimianCountFromValue(@Param("referenceValue") BigInteger referenceValue);
}
