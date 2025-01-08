package com.mtm.manifestation.v2.controller;


import com.mtm.manifestation.v1.dto.FetchAvatarResponse;
import com.mtm.manifestation.v2.dto.Demo;
import com.mtm.manifestation.v2.service.WebFluxService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

@RestController
@Slf4j
public class V2WebfluxController {
    @Autowired
    WebFluxService webFluxService;
    @Async("asyncExecutor")
    @Operation(
            operationId = "webflux",
            tags = {"Reactive"},
            responses ={@ApiResponse(
                    responseCode = "200",
                    description = "call successfully",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = Demo.class
                            )
                    )}
    )})
    @GetMapping("/v2/{id}")
    public Future<ResponseEntity<Demo>> getAction(@PathVariable(name = "id") UUID id) {
        log.info("inside the first class");
        //this is for weflux event.
        webFluxService.getConnect();
        //this is for error handling
        return new AsyncResult<>(ResponseEntity.ok(webFluxService.getDetails()));
    }


}
