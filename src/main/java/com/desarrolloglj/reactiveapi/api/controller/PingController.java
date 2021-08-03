package com.desarrolloglj.reactiveapi.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/ping")
public class PingController {

    /**
     * Pings the Api
     * @return "pong" word
     */
    @GetMapping
    public Mono<String> get() {
        return Mono.just("pong");
    }
}
