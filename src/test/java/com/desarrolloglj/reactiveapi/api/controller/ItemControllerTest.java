package com.desarrolloglj.reactiveapi.api.controller;

import com.desarrolloglj.reactiveapi.api.domain.Item;
import com.desarrolloglj.reactiveapi.api.domain.dto.ItemDTO;
import com.desarrolloglj.reactiveapi.api.domain.dto.ItemSaveDTO;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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
                .isEqualTo(getTestItemDTO());
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

    @Test
    public void whenSave_ThenReturnCreatedItem()
    {
        var createdItem = getTestItem();
        when(itemService.save(any(Item.class))).thenReturn(Mono.just(createdItem));

        var inputItem = getTestSaveDTO();
        webClient.post()
                .uri("/v1/items")
                .body(Mono.just(inputItem), ItemSaveDTO.class)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(ItemDTO.class);
    }

    @Test
    public void whenUpdate_AndItsSuccessful_ThenReturnUpdatedItem()
    {
        var updatedItem = getTestItem();
        when(itemService.update(any(Item.class))).thenReturn(Mono.just(updatedItem));

        var inputItem = getTestItemDTO();
        webClient.put()
                .uri("/v1/items")
                .body(Mono.just(inputItem), ItemDTO.class)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(ItemDTO.class);
    }

    @Test
    public void whenUpdate_AndItemDoesNotExists_ThenReturnHttpStatusNotFound()
    {
        when(itemService.update(any(Item.class))).thenReturn(Mono.empty());

        var inputItem = getTestItemDTO();
        webClient.put()
                .uri("/v1/items")
                .body(Mono.just(inputItem), ItemDTO.class)
                .exchange()
                .expectStatus()
                .isNotFound();
    }

    @Test
    public void whenUpdate_AndItsSuccessful_ThenReturnDeletedItem()
    {
        var deletedItem = getTestItem();
        when(itemService.delete(eq(1L))).thenReturn(Mono.just(deletedItem));

        webClient.delete()
                .uri("/v1/items/1")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(ItemDTO.class);
    }

    @Test
    public void whenDelete_AndItemDoesNotExist_ThenReturnHttpStatusBadRequest()
    {
        when(itemService.delete(eq(1L))).thenReturn(Mono.empty());

        webClient.delete()
                .uri("/v1/items/1")
                .exchange()
                .expectStatus()
                .isBadRequest();
    }

    private ItemDTO getTestItemDTO()
    {
        return new ItemDTO(1L, "Test Item", "Test Item 1", new BigDecimal("10.5"));
    }

    private ItemSaveDTO getTestSaveDTO()
    {
        return new ItemSaveDTO("Test Item", "Test Item 1", new BigDecimal("10.5"));
    }

    private Item getTestItem()
    {
        return new Item(1L, "Test Item", "Test Item 1", new BigDecimal("10.5"));
    }
}
