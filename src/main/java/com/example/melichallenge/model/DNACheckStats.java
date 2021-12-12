package com.example.melichallenge.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Table(name = "dna_check_stats")
@Getter
@Setter
public class DNACheckStats implements Serializable {
    @Id
    private Long id;
    @Column
    private BigInteger humansCount;
    @Column
    private BigInteger simiansCount;
}
