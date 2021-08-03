package com.desarrolloglj.reactiveapi.api.service.impl;

import com.desarrolloglj.reactiveapi.api.domain.Item;
import com.desarrolloglj.reactiveapi.api.repository.ItemRepository;
import com.desarrolloglj.reactiveapi.api.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {
  private final ItemRepository itemRepository;

  @Override
  public Flux<Item> getAll() {
    return itemRepository.findAll();
  }

  @Override
  public Mono<Item> getById(long id) {
    return itemRepository.findById(id);
  }

  @Override
  public Mono<Item> save(Item item) {
    return itemRepository.save(item);
  }

  @Override
  public Mono<Item> update(Item item) {
    return itemRepository
        .findById(item.getId())
        .map(
            currentItem -> {
              currentItem.setName(item.getName());
              currentItem.setLongDescription(item.getLongDescription());
              currentItem.setAmount(item.getAmount());
              return currentItem;
            })
        .flatMap(itemRepository::save)
        .switchIfEmpty(Mono.empty());
  }

  @Override
  public Mono<Item> delete(long id) {
    return itemRepository
        .findById(id)
        .flatMap(currentItem -> itemRepository.deleteById(id).then(Mono.just(currentItem)))
        .flux()
        .singleOrEmpty();
  }
}
