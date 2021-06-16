package com.mercadolibre.mutants.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.mutants.controller.MutantController;
import com.mercadolibre.mutants.dto.MutantDto;
import com.mercadolibre.mutants.repository.MutantRepository;
import com.mercadolibre.mutants.service.MutantService;
import com.mercadolibre.mutants.service.impl.MutantServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = MutantController.class)
@AutoConfigureWebTestClient(timeout = "120000")
@Import(MutantServiceImpl.class)
public class IntegrationTestWebFlux {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private MutantService mutantService;

    @MockBean
    private MutantRepository mutantRepository;

    @Test
    public void testIsMutant() throws JsonProcessingException {

        String bodyString = "{\n" +
                "    \"dna\": [\n" +
                "        \"TTGCGA\",\n" +
                "        \"CAGTCC\",\n" +
                "        \"TTATGT\",\n" +
                "        \"AGAAGG\",\n" +
                "        \"CCCCTA\",\n" +
                "        \"TCACTA\"\n" +
                "    ]\n" +
                "}";

        ObjectMapper objectMapper = new ObjectMapper();
        MutantDto mutantDto = objectMapper.readValue(bodyString, MutantDto.class);

        Mockito.when(mutantRepository.save(ArgumentMatchers.any())).thenReturn(mutantDto.toJpaEntity());

        webTestClient.post().uri("/mutant")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(mutantDto)
                .exchange()
                .expectStatus().isOk();

    }

    @Test
    public void testIsHuman() throws JsonProcessingException {

        String bodyString = "{\n" +
                "    \"dna\": [\n" +
                "        \"TTGCGA\",\n" +
                "        \"CAGTCC\",\n" +
                "        \"TTATGT\",\n" +
                "        \"AGAAGG\",\n" +
                "        \"CACCTA\",\n" +
                "        \"TCACTA\"\n" +
                "    ]\n" +
                "}";

        ObjectMapper objectMapper = new ObjectMapper();
        MutantDto mutantDto = objectMapper.readValue(bodyString, MutantDto.class);

        Mockito.when(mutantRepository.save(ArgumentMatchers.any())).thenReturn(mutantDto.toJpaEntity());

        webTestClient.post().uri("/mutant")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(mutantDto)
                .exchange()
                .expectStatus().isForbidden();

    }

}
