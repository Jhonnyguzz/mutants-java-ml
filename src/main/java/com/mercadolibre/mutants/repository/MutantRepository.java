package com.mercadolibre.mutants.repository;

import com.mercadolibre.mutants.jpa.MutantJpa;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MutantRepository extends PagingAndSortingRepository<MutantJpa, Long> {

}
