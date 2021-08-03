package com.desarrolloglj.reactiveapi.api.repository;

import com.desarrolloglj.reactiveapi.api.domain.Item;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends ReactiveCrudRepository<Item, Long> {
}
