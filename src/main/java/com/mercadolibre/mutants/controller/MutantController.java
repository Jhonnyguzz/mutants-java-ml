package com.mercadolibre.mutants.controller;

import com.mercadolibre.mutants.dto.MutantDto;
import com.mercadolibre.mutants.service.MutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class MutantController {

    @Autowired
    private MutantService mutantService;

    @PostMapping("/mutant")
    public Mono<ResponseEntity> checkMutant(@RequestBody MutantDto dna) {
        Mono.just(mutantService.save(dna)).subscribe();
        return Mono.just(mutantService.isMutant(dna.toCharArray()))
                .map(flag -> flag
                ? ResponseEntity.ok().build()
                : ResponseEntity.status(403).build());
    }

    @GetMapping("/stats")
    public Mono<ResponseEntity> stats() {
        return Mono.just(ResponseEntity.ok(mutantService.getStats()));
    }
}