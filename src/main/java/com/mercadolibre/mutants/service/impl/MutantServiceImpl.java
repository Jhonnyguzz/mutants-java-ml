package com.mercadolibre.mutants.service.impl;

import com.mercadolibre.mutants.dto.MutantDto;
import com.mercadolibre.mutants.dto.StatsDto;
import com.mercadolibre.mutants.jpa.MutantJpa;
import com.mercadolibre.mutants.repository.MutantRepository;
import com.mercadolibre.mutants.service.MutantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class MutantServiceImpl implements MutantService {

    @Autowired
    private MutantRepository mutantRepository;

    @Override
    public boolean isMutant(String[] dna) {
        char[][] matrix = new char[dna.length][dna.length];
        for (int i = 0; i < dna.length; i++)
            matrix[i] = Arrays.copyOf(dna[i].toCharArray(), dna.length);

        boolean flag = false;
        for (int i = 0; i < dna.length; i++) {
            for (int j = 0; j < dna.length; j++) {
                flag = check(i, j, matrix);
                if(flag) {
                    log.info("Matrix Position for mutant: ("+i+","+j+")");
                    break;
                }
            }
            if(flag)
                break;
        }
        return flag;
    }

    @Override
    public MutantJpa save(MutantDto mutantDto) {
        MutantJpa mutantJpa = mutantDto.toJpaEntity();
        mutantJpa.setMutant(this.isMutant(mutantDto.toCharArray()));
        return mutantRepository.save(mutantJpa);
    }

    @Override
    public StatsDto getStats() {
        Supplier<Stream<MutantJpa>> supp = () -> StreamSupport.stream(mutantRepository.findAll().spliterator(), true);
        long total = supp.get().count();
        long mutants = supp.get().filter(MutantJpa::isMutant).count();
        long humans = total - mutants;
        return new StatsDto(mutants, humans, (double)mutants/(double)humans);
    }

    private boolean check(int row, int col, char[][] matrix) {
        char letter = matrix[row][col];
        boolean flag = false;
        if(col+3<matrix.length) {
            String s = new String(matrix[row]);
            s = s.substring(col, col+4);
            flag = s.contains(String.valueOf(letter).repeat(4));
        }
        if(!flag && row+3<matrix.length) {
            String s = new String(new char[]{matrix[row][col], matrix[row+1][col], matrix[row+2][col], matrix[row+3][col]});
            flag = s.contains(String.valueOf(letter).repeat(4));
        }
        if(!flag && row+3<matrix.length && col+3<matrix.length) {
            String s = new String(new char[]{matrix[row][col], matrix[row+1][col+1], matrix[row+2][col+2], matrix[row+3][col+3]});
            flag = s.contains(String.valueOf(letter).repeat(4));
        }
        if(!flag && row+3<matrix.length && col-3>=0) {
            String s = new String(new char[]{matrix[row][col], matrix[row+1][col-1], matrix[row+2][col-2], matrix[row+3][col-3]});
            flag = s.contains(String.valueOf(letter).repeat(4));
        }
        return flag;
    }
}
