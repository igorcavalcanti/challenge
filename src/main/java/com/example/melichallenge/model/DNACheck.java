package com.example.melichallenge.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "dna_check")
@Getter
@Setter
public class DNACheck implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "hash")
    private String hash;

    @Column(name = "result")
    private boolean result;
}
