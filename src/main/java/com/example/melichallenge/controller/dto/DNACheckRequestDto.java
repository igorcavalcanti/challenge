package com.example.melichallenge.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder(toBuilder = true)
@Getter
public class DNACheckRequestDto {
    private String[] dna;
}
