package com.mercadolibre.mutants.functions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.mutants.dto.StatsDto;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import org.springframework.cloud.function.adapter.azure.FunctionInvoker;
import reactor.core.publisher.Mono;

import java.util.Optional;

public class StatsHandler extends FunctionInvoker<String, StatsDto> {

    @FunctionName("stats")
    public HttpResponseMessage execute(
            @HttpTrigger(name = "request", methods = {HttpMethod.GET}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
            ExecutionContext context) throws JsonProcessingException {
        context.getLogger().info("Executing Lambda Stats Function");

        StatsDto statsDto = handleRequest("", context);
        ObjectMapper objectMapper = new ObjectMapper();
        String response = objectMapper.writeValueAsString(statsDto);

        return request
                .createResponseBuilder(HttpStatus.OK)
                .body(response)
                .header("Content-Type", "application/json")
                .build();
    }
}
