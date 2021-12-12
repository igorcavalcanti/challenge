package com.example.melichallenge.repository;

import com.example.melichallenge.model.DNACheck;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DNACheckRepository extends JpaRepository<DNACheck, Long> {
    Optional<DNACheck> findByHashEquals(String hash);
}
