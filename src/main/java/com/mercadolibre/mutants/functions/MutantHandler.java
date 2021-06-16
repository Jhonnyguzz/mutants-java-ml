package com.mercadolibre.mutants.functions;

import java.util.*;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import org.springframework.cloud.function.adapter.azure.FunctionInvoker;

public class MutantHandler extends FunctionInvoker<String, Boolean> {

    @FunctionName("mutant")
    public HttpResponseMessage execute(
            @HttpTrigger(name = "request", methods = {HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
            ExecutionContext context) {
        context.getLogger().info("Executing Lambda Function");
        boolean flag = handleRequest(request.getBody().get(), context);
        return request
                .createResponseBuilder(flag ? HttpStatus.OK : HttpStatus.FORBIDDEN)
                .header("Content-Type", "application/json")
                .build();
    }
}
