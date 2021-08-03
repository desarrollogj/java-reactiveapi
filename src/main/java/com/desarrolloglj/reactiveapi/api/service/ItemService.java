package com.desarrolloglj.reactiveapi.api.service;

import com.desarrolloglj.reactiveapi.api.domain.Item;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ItemService {
    Flux<Item> getAll();
    Mono<Item> getById(long id);
    Mono<Item> save(Item item);
    Mono<Item> update(Item item);
    Mono<Item> delete(long id);
}
