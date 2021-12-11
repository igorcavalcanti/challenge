package com.example.melichallenge.service;

import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

class DNACheckServiceTest {

    @Test
    void checkIfNorthEastDirection() {
        String[] payload = {"CTGAG", "TCCGA", "ATGTA", "TGACA", "GTACA"};
        DNACheckService dnaCheckService = new DNACheckService();

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
        DNACheckService dnaCheckService = new DNACheckService();

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
        DNACheckService dnaCheckService = new DNACheckService();

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
        DNACheckService dnaCheckService = new DNACheckService();

        assertTrue(dnaCheckService.hasSouthDirectionAnswerFrom(0, 2, dnaCheckService.getChars(payload)));
        assertFalse(dnaCheckService.hasSouthDirectionAnswerFrom(1, 0, dnaCheckService.getChars(payload)));
    }

    @Test
    void contextLoads() {
        String[] payload = {"CTGAGA", "CTATGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG"};

        try {
            assertTrue(new DNACheckService().checkIfDNAIsSimian(payload));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Test
    void generatingHash() {
        DNACheckService dnaCheckService = new DNACheckService();

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