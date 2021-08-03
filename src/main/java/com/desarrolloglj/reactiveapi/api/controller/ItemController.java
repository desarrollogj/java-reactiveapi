package com.desarrolloglj.reactiveapi.api.controller;

import com.desarrolloglj.reactiveapi.api.domain.dto.ItemDTO;
import com.desarrolloglj.reactiveapi.api.domain.dto.ItemSaveDTO;
import com.desarrolloglj.reactiveapi.api.mapper.ItemDTOMapper;
import com.desarrolloglj.reactiveapi.api.mapper.ItemSaveDTOMapper;
import com.desarrolloglj.reactiveapi.api.service.ItemService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/items")
@Slf4j
@Validated
@AllArgsConstructor
public class ItemController {
    private final ItemService service;
    private final ItemDTOMapper itemDTOMapper;
    private final ItemSaveDTOMapper itemSaveDTOMapper;

    /**
     * Return a list of items
     * @return A list of {@link ItemDTO}
     */
    @GetMapping
    public Flux<ItemDTO> getAll() {
        return service
                .getAll()
                .map(itemDTOMapper::adaptToItemDTO)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Items not found")));
    }

    /**
     * Return an item by its id
     * @param id Item id
     * @return {@link ItemDTO} data
     */
    @GetMapping(value = "/{id}")
    public Mono<ItemDTO> getById(@PathVariable(value = "id") long id) {
        return service
                .getById(id)
                .map(itemDTOMapper::adaptToItemDTO)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Item with id %d not found", id))));
    }

    /**
     * Save an item
     * @param item {@link ItemDTO} data to save
     * @return {@link ItemDTO} create item
     */
    @PostMapping()
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<ItemDTO> save(@Valid @RequestBody ItemSaveDTO item) {
        return service
                .save(itemSaveDTOMapper.adaptToItem(item))
                .map(itemDTOMapper::adaptToItemDTO);
    }

    /**
     * Update an item
     * @param item {@link ItemDTO} data to update
     * @return {@link ItemDTO} updated item
     */
    @PutMapping()
    public Mono<ItemDTO> update(@RequestBody ItemDTO item) {
        return service
                .update(itemDTOMapper.adaptToItem(item))
                .map(itemDTOMapper::adaptToItemDTO)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Item with id %d not found", item.getId()))));
    }

    /**
     * Delete an item
     * @param id Item id
     * @return {@link ItemDTO} deleted item
     */
    @DeleteMapping(value = "/{id}")
    public Mono<ItemDTO> delete(@PathVariable(value = "id") long id) {
        return service
                .delete(id)
                .map(itemDTOMapper::adaptToItemDTO)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Item with id %d could not be deleted", id))));
    }
}
