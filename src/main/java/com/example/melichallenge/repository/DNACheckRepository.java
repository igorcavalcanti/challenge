package com.example.melichallenge.repository;

import com.example.melichallenge.model.DNACheck;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DNACheckRepository extends JpaRepository<DNACheck, Long> {
    @Cacheable(value="DNACheck", unless="#result == null")
    Optional<DNACheck> findByHashEquals(String hash);
}
