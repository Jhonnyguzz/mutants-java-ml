package com.mercadolibre.mutants.unit;

import com.mercadolibre.mutants.service.MutantService;
import com.mercadolibre.mutants.service.impl.MutantServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MutantsApplicationTests {

    private MutantService mutantService = new MutantServiceImpl();

    @Test
    public void testMutantService() {

        String[] dnaMutant = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        String[] dnaMutant2 = {"TTGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        String[] dnaMutant3 = {"ATGCGA","CAGCCC","TCATGT","CGAAGG","ACCCTA","TCACTG"};
        String[] dnaHuman = {"TTGCGA","CAGTTC","TTATGT","AGAAGG","CTCCTA","TCACTG"};

        Assertions.assertTrue(mutantService.isMutant(dnaMutant));
        Assertions.assertTrue(mutantService.isMutant(dnaMutant2));
        Assertions.assertTrue(mutantService.isMutant(dnaMutant3));
        Assertions.assertFalse(mutantService.isMutant(dnaHuman));
    }
}
