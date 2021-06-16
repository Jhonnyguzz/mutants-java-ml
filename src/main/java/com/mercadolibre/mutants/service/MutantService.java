package com.mercadolibre.mutants.service;

import com.mercadolibre.mutants.dto.MutantDto;
import com.mercadolibre.mutants.dto.StatsDto;
import com.mercadolibre.mutants.jpa.MutantJpa;

public interface MutantService {

    boolean isMutant(String[] dna);

    MutantJpa save(MutantDto mutantDto);

    StatsDto getStats();

}
