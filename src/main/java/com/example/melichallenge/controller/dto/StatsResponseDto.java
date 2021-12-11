package com.example.melichallenge.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.math.BigDecimal;
import java.math.BigInteger;

@Builder(toBuilder = true)
public class StatsResponseDto {
    @JsonProperty("count_simian_dna")
    private BigInteger simiansCount;

    @JsonProperty("count_human_dna")
    private BigInteger humansCount;

    @JsonProperty("ratio")
    public BigDecimal getRatio() {
        BigDecimal divide = BigDecimal.ZERO;

        if (this.humansCount.compareTo(BigInteger.ZERO) > 0) {
            divide = new BigDecimal(this.simiansCount.toString())
                    .divide(new BigDecimal(this.humansCount.toString()));
        }
        return divide.setScale(2);
    }
}
