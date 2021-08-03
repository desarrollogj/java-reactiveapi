package com.desarrolloglj.reactiveapi.api.controller;

import com.desarrolloglj.reactiveapi.api.domain.Item;
import com.desarrolloglj.reactiveapi.api.domain.dto.ItemDTO;
import com.desarrolloglj.reactiveapi.api.service.ItemService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ActiveProfiles({"test"})
@ExtendWith(SpringExtension.class)
@WebFluxTest(ItemController.class)
@ContextConfiguration(classes = ItemControllerTest.class)
@ComponentScan(basePackages = "com.desarrolloglj.reactiveapi.api")
public class ItemControllerTest {
    @Autowired
    private WebTestClient webClient;

    @MockBean
    private ItemService itemService;

    @Test
    public void whenGetAll_AndItemsExist_ThenReturnAListOfItems()
    {
        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item(1L, "Test Item", "Test Item 1", new BigDecimal("10.5")));
        when(itemService.getAll()).thenReturn(Flux.fromIterable(itemList));

        webClient.get()
                .uri("/v1/items")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(ItemDTO.class);
    }

    @Test
    public void whenGetAll_AndItemsDoNotExist_ThenReturnAnEmptyListOfItems()
    {
        List<Item> itemList = new ArrayList<>();
        when(itemService.getAll()).thenReturn(Flux.fromIterable(itemList));

        webClient.get()
                .uri("/v1/items")
                .exchange()
                .expectStatus()
                .isNotFound();
    }

    @Test
    public void whenGetById_AndExists_ThenReturnAnItem()
    {
        var mockItem = new Item(1L, "Test Item", "Test Item 1", new BigDecimal("10.5"));
        when(itemService.getById(1L)).thenReturn(Mono.just(mockItem));

        webClient.get()
                .uri("/v1/items/1")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(ItemDTO.class)
                .isEqualTo(getTestItem());
    }

    @Test
    public void whenGetById_AndDoesNotExist_ThenReturnNull()
    {
        when(itemService.getById(1L)).thenReturn(Mono.empty());

        webClient.get()
                .uri("/v1/items/1")
                .exchange()
                .expectStatus()
                .isNotFound();
    }

    private ItemDTO getTestItem()
    {
        return new ItemDTO(1L, "Test Item", "Test Item 1", new BigDecimal("10.5"));
    }
}
