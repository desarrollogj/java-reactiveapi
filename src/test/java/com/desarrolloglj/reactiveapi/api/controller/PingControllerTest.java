package com.desarrolloglj.reactiveapi.api.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ActiveProfiles({"test"})
@ExtendWith(SpringExtension.class)
@WebFluxTest(PingController.class)
public class PingControllerTest {
    @Autowired
    private WebTestClient webClient;

    @Test
    void whenPing_ThenReturnPong() {
        webClient.get()
                .uri("/v1/ping")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(String.class)
                .isEqualTo("pong");
    }
}
