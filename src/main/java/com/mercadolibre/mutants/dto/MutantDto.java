package com.mercadolibre.mutants.dto;

import com.mercadolibre.mutants.jpa.MutantJpa;
import lombok.Data;

import java.util.List;

@Data
public class MutantDto {

    List<String> dna;

    public String[] toCharArray() {
        String[] arr = new String[dna.size()];
        return dna.toArray(arr);
    }

    public MutantJpa toJpaEntity() {
        MutantJpa mutantJpa = new MutantJpa();
        mutantJpa.setDna(String.join(",",dna));
        return mutantJpa;
    }
}
