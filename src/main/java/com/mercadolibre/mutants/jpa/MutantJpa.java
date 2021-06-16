package com.mercadolibre.mutants.jpa;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class MutantJpa {

    @Id
    private String dna;
    private boolean isMutant;
}
