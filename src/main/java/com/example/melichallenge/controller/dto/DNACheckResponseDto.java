package com.example.melichallenge.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder(toBuilder = true)
public class DNACheckResponseDto {
    @JsonProperty("is_simian")
    private boolean isSimian;
}
