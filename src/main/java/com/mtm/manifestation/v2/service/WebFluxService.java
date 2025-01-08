package com.mtm.manifestation.v2.service;

import com.mtm.manifestation.v2.dto.Demo;
import com.mtm.manifestation.v2.dto.DemoObject;
import com.mtm.manifestation.v2.exception.TestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class WebFluxService {
    private static final String URL_ONE="localhost:8081";
    private static final String URL_TWO="localhost:8082";
    private final WebClient webClientOne=WebClient.builder().baseUrl("http://"+URL_ONE)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_EVENT_STREAM_VALUE)
            .build();

    private final WebClient webClientTwo=WebClient.builder().baseUrl("http://"+URL_TWO)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_EVENT_STREAM_VALUE)
            .build();
    @Retryable(retryFor = TestException.class, maxAttempts = 2, backoff = @Backoff(delay = 100))
    public Demo getDetails() throws TestException{
        int i =0;
        if(i==0){
            log.info("inside exception handler");
            throw new TestException("demo exception");
        }else{
        return Demo.builder().id(1).name("indranil").build();
        }
    }
    @Recover
    public Demo getDetailsRecovery(TestException e){
    log.info("inside recovery");
        return Demo.builder().id(1).name(" recovered indranil").build();
    }
    public void getConnectOne(){
        Flux<DemoObject> returnObject = webClientOne.get().uri("/demoObjects/stream").
                retrieve().
                bodyToFlux(DemoObject.class);
        returnObject.log();

        //returnObject.doOnComplete(() -> System.out.println("First Event Is done"));
        returnObject.subscribe();
    }

    public void getConnectTwo(){
        Flux<DemoObject> returnObject = webClientTwo.get().uri("/demoObjects/stream").
                retrieve().
                bodyToFlux(DemoObject.class);
        returnObject.log();
        returnObject.subscribe();
    }

    public void getConnect(){
        getConnectOne();
        getConnectTwo();
    }

}
