package com.example.melichallenge.repository;

import com.example.melichallenge.model.DNACheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.Optional;

public interface DNACheckRepository extends JpaRepository<DNACheck, Long> {

    Optional<DNACheck> findByHashEquals(String hash);

    @Query(value = "select current_value  from information_schema.sequences where sequence_name = 'HUMANS_COUNT'", nativeQuery = true)
    Optional<BigInteger> getHumansCount();

    @Query(value = "select HUMANS_COUNT.nextval from dual", nativeQuery = true)
    BigInteger incrementHumansCount();

    @Query(value = "select current_value  from information_schema.sequences where sequence_name = 'SIMIANS_COUNT'", nativeQuery = true)
    Optional<BigInteger> getSimiansCount();

    @Query(value = "select SIMIANS_COUNT.nextval from dual", nativeQuery = true)
    BigInteger incrementSimiansCount();
}
