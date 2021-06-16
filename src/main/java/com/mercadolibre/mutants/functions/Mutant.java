package com.mercadolibre.mutants.functions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.mutants.dto.MutantDto;
import com.mercadolibre.mutants.service.MutantService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Component
public class Mutant implements Function<String, Boolean> {

    @Autowired
    private MutantService mutantService;

    @SneakyThrows
    @Override
    public Boolean apply(String stringMessage) {
        String value = stringMessage;

        ObjectMapper objectMapper = new ObjectMapper();
        MutantDto dna = objectMapper.readValue(value, MutantDto.class);

        Mono.just(mutantService.save(dna)).subscribe();
        return mutantService.isMutant(dna.toCharArray());
    }
}
