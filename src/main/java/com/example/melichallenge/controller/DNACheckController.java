package com.example.melichallenge.controller;

import com.example.melichallenge.controller.dto.DNACheckRequestDto;
import com.example.melichallenge.controller.dto.DNACheckResponseDto;
import com.example.melichallenge.controller.dto.StatsResponseDto;
import com.example.melichallenge.service.DNACheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
public class DNACheckController {

    private final DNACheckService dnaCheckService;

    @Autowired
    public DNACheckController(DNACheckService dnaCheckService) {
        this.dnaCheckService = dnaCheckService;
    }

    @PostMapping("/simian")
    public ResponseEntity<DNACheckResponseDto> checkIfSimian(@RequestBody DNACheckRequestDto requestData) throws NoSuchAlgorithmException {
        return ResponseEntity.ok(DNACheckResponseDto.builder()
                .isSimian(this.dnaCheckService.checkIfDNAIsSimian(requestData.getDna()))
                .build());
    }

    @GetMapping("/stats")
    public ResponseEntity<StatsResponseDto> getStats() {
        return ResponseEntity.ok(StatsResponseDto.builder()
                .humansCount(this.dnaCheckService.getHumansCount())
                .simiansCount(this.dnaCheckService.getSiminasCount())
                .build());
    }
}
