package com.example.melichallenge.service;

import com.example.melichallenge.model.DNACheck;
import com.example.melichallenge.repository.DNACheckRepository;
import org.apache.tomcat.util.buf.HexUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Stream;

@Service
public class DNACheckService {
    @Autowired
    private DNACheckRepository repository;

    @Transactional
    public boolean checkIfDNAIsSimian(String[] dnaSample) throws NoSuchAlgorithmException {
        String sampleHash = this.makeSHA256Hash(String.join("", dnaSample));

        return this.repository.findByHashEquals(sampleHash)
                .map(DNACheck::isResult)
                .orElseGet(() -> {
                    boolean result = false;
                    char[][] data = getChars(dnaSample);
                    int count = 0;

                    for (int line = 0; line < data.length; line++) {
                        for (int column = 0; column < data[line].length; column++) {
                            count += this.checkFrom(line, column, data);

                            if (count > 1) {
                                result = true;
                            }
                        }
                    }
                    this.saveCheckResult(sampleHash, result);
                    return result;
                });
    }

    void saveCheckResult(String sampleHash, boolean result) {
        DNACheck dnaCheck = new DNACheck();
        dnaCheck.setHash(sampleHash);
        dnaCheck.setResult(result);
        this.repository.save(dnaCheck);

        if (result) {
            this.incrementCounterForSimian();
        } else {
            this.incrementCounterForHuman();
        }
    }


    char[][] getChars(String[] dnaSample) {
        return Stream.of(dnaSample)
                .map(String::toCharArray)
                .toArray(i -> new char[i][]);
    }

    int checkFrom(int line, int column, char[][] data) {
        int result = 0;

        if (this.hasNorthEastDirectionAnswerFrom(line, column, data)) {
            result++;
        }
        if (this.hasEastDirectionAnswerFrom(line, column, data)) {
            result++;
        }
        if (this.hasSouthEastDirectionAnswerFrom(line, column, data)) {
            result++;
        }
        if (this.hasSouthDirectionAnswerFrom(line, column, data)) {
            result++;
        }
        return result;
    }

    boolean hasNorthEastDirectionAnswerFrom(int line, int column, char[][] data) {
        boolean result = false;
        boolean northEastDirectionViable = line >= 3 && data[line].length - column >= 4;

        if (northEastDirectionViable) {
            result = true;
            char reference = data[line][column];
            int lineDecrement = 0;
            int columnIncrement = 0;

            while (lineDecrement < 3) {
                if (data[line - (++lineDecrement)][column + (++columnIncrement)] != reference) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    boolean hasEastDirectionAnswerFrom(int line, int column, char[][] data) {
        boolean result = false;
        boolean eastDirectionViable = data[line].length - column >= 4;

        if (eastDirectionViable) {
            result = true;
            char reference = data[line][column];
            int columnIncrement = 0;

            while (columnIncrement < 3) {
                if (data[line][column + (++columnIncrement)] != reference) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    boolean hasSouthEastDirectionAnswerFrom(int line, int column, char[][] data) {
        boolean result = false;
        boolean southEastDirectionViable = data.length - line >= 4 && data[line].length - column >= 4;

        if (southEastDirectionViable) {
            result = true;
            char reference = data[line][column];
            int lineIncrement = 0;
            int columnIncrement = 0;

            while (lineIncrement < 3) {
                if (data[line + (++lineIncrement)][column + (++columnIncrement)] != reference) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    boolean hasSouthDirectionAnswerFrom(int line, int column, char[][] data) {
        boolean result = false;
        boolean southDirectionsViable = data.length - line >= 4;

        if (southDirectionsViable) {
            result = true;
            char reference = data[line][column];
            int lineIncrement = 0;

            while (lineIncrement < 3) {
                if (data[line + (++lineIncrement)][column] != reference) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    String makeSHA256Hash(String input) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        return HexUtils.toHexString(digest.digest(input.getBytes(StandardCharsets.UTF_8)));
    }

    void incrementCounterForSimian() {
        this.repository.incrementSimiansCount();
    }

    void incrementCounterForHuman() {
        this.repository.incrementHumansCount();
    }

    public BigInteger getSiminasCount() {
        return this.repository.getSimiansCount().orElse(BigInteger.ZERO);
    }

    public BigInteger getHumansCount() {
        return this.repository.getHumansCount().orElse(BigInteger.ZERO);
    }
}
