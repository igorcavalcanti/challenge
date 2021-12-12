package com.example.melichallenge.service;

import com.example.melichallenge.model.DNACheck;
import com.example.melichallenge.model.DNACheckStats;
import com.example.melichallenge.repository.DNACheckRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DNACheckServiceTest {

    @Autowired
    DNACheckService dnaCheckService;
    @MockBean
    DNACheckRepository repository;
    @MockBean
    DNACheckStatsService dnaCheckStatsService;

    @Test
    void checkIfNorthEastDirection() {
        String[] payload = {"CTGAG", "TCCGA", "ATGTA", "TGACA", "GTACA"};

        assertTrue(dnaCheckService.hasNorthEastDirectionAnswerFrom(4, 0, dnaCheckService.getChars(payload)));
        assertTrue(dnaCheckService.hasNorthEastDirectionAnswerFrom(3, 1, dnaCheckService.getChars(payload)));
        assertFalse(dnaCheckService.hasNorthEastDirectionAnswerFrom(2, 2, dnaCheckService.getChars(payload)));
    }

    @Test
    void checkIfEastDirection() {
        String[] payload = {"CTGAG",
                "CCCGC",
                "TTTTT",
                "TGACA",
                "GTACA"};

        assertTrue(dnaCheckService.hasEastDirectionAnswerFrom(2, 0, dnaCheckService.getChars(payload)));
        assertTrue(dnaCheckService.hasEastDirectionAnswerFrom(2, 1, dnaCheckService.getChars(payload)));
        assertFalse(dnaCheckService.hasEastDirectionAnswerFrom(2, 2, dnaCheckService.getChars(payload)));
        assertFalse(dnaCheckService.hasEastDirectionAnswerFrom(1, 1, dnaCheckService.getChars(payload)));
    }

    @Test
    void checkIfSouthEastDirection() {
        String[] payload = {"CTGAG",
                "CCTGC",
                "TTTTT",
                "TGACT",
                "GTACA"};

        assertTrue(dnaCheckService.hasSouthEastDirectionAnswerFrom(0, 1, dnaCheckService.getChars(payload)));
        assertFalse(dnaCheckService.hasSouthEastDirectionAnswerFrom(1, 0, dnaCheckService.getChars(payload)));
    }

    @Test
    void checkIfSouthDirection() {
        String[] payload = {"CTGAG",
                "CCGGC",
                "TTGTT",
                "TGGCT",
                "GTACA"};

        assertTrue(dnaCheckService.hasSouthDirectionAnswerFrom(0, 2, dnaCheckService.getChars(payload)));
        assertFalse(dnaCheckService.hasSouthDirectionAnswerFrom(1, 0, dnaCheckService.getChars(payload)));
    }

    @Test
    void givenSimian_WhenProcess_thenResultTrue_1() {
        String[] payload = {"CTGAGA", "CTATGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG"};
        DNACheck check = new DNACheck();
        check.setResult(false);

        Mockito.when(this.repository.findByHashEquals(ArgumentMatchers.any())).thenReturn(Optional.ofNullable(null));

        DNACheckStats t = new DNACheckStats();
        t.setHumansCount(BigInteger.ZERO);
        t.setSimiansCount(BigInteger.ZERO);
        Mockito.when(this.dnaCheckStatsService.findById()).thenReturn(t);

        Mockito.when(this.dnaCheckStatsService.incrementHumanCountFromValue(ArgumentMatchers.any())).thenReturn(1);
        Mockito.when(this.dnaCheckStatsService.incrementSimianCountFromValue(ArgumentMatchers.any())).thenReturn(1);

        try {
            assertTrue(dnaCheckService.checkIfDNAIsSimian(payload));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Test
    void givenSimian_WhenProcess_thenResultTrue_2() {
        String[] payload = {"AGAGGGCGCT",
                            "TCTCGCGGGC",
                            "CACTGTGTAT",
                            "GTGAGGGGTA",
                            "ATATATATAT",
                            "TATATATATA",
                            "GTGAGGGGTA",
                            "TATATATATA",
                            "TATATATATA",
                            "TATATATATA"};

        Mockito.when(this.repository.findByHashEquals(ArgumentMatchers.any())).thenReturn(Optional.ofNullable(null));

        DNACheckStats t = new DNACheckStats();
        t.setHumansCount(BigInteger.ZERO);
        t.setSimiansCount(BigInteger.ZERO);
        Mockito.when(this.dnaCheckStatsService.findById()).thenReturn(t);

        Mockito.when(this.dnaCheckStatsService.incrementHumanCountFromValue(ArgumentMatchers.any())).thenReturn(1);
        Mockito.when(this.dnaCheckStatsService.incrementSimianCountFromValue(ArgumentMatchers.any())).thenReturn(1);

        try {
            assertTrue(dnaCheckService.checkIfDNAIsSimian(payload));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Test
    void generatingHash() {
        String[] payload = {"CTGAGA", "CTATGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG"};
        String first = null;
        String second = "";

        try {
            first = dnaCheckService.makeSHA256Hash(String.join("", payload));
            second = dnaCheckService.makeSHA256Hash(String.join("", payload));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        assertEquals(first, second);
    }
}