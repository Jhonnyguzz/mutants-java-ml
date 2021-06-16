package com.mercadolibre.mutants.functions;

import com.mercadolibre.mutants.dto.StatsDto;
import com.mercadolibre.mutants.service.MutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Component
public class Stats implements Function<String, StatsDto> {

    @Autowired
    private MutantService mutantService;

    @Override
    public StatsDto apply(String message) {
        return mutantService.getStats();
    }
}
