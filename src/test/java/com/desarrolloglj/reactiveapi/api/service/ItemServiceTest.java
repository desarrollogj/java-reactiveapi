package com.desarrolloglj.reactiveapi.api.service;

import com.desarrolloglj.reactiveapi.api.domain.Item;
import com.desarrolloglj.reactiveapi.api.repository.ItemRepository;
import com.desarrolloglj.reactiveapi.api.service.impl.ItemServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {
    @InjectMocks
    private ItemServiceImpl itemService;

    @Mock
    private ItemRepository itemRepository;

    @Test
    public void whenGetAll_AndItemsExist_ThenReturnAListOfItems()
    {
        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item(1L, "Test Item", "Test Item 1", new BigDecimal("10.5")));
        when(itemRepository.findAll()).thenReturn(Flux.fromIterable(itemList));

        var serviceItems = itemService.getAll();
        var items = serviceItems.collectList().block();

        assert items != null;
        assertEquals(1, items.size());
    }

    @Test
    public void whenGetAll_AndItemsDoNotExist_ThenReturnAnEmptyListOfItems()
    {
        List<Item> itemList = new ArrayList<>();
        when(itemRepository.findAll()).thenReturn(Flux.fromIterable(itemList));

        var serviceItems = itemService.getAll();
        var items = serviceItems.collectList().block();

        assert items != null;
        assertEquals(0, items.size());
    }

    @Test
    public void whenGetById_AndExists_ThenReturnAnItem()
    {
        var mockItem = new Item(1L, "Test Item", "Test Item 1", new BigDecimal("10.5"));
        when(itemRepository.findById(1L)).thenReturn(Mono.just(mockItem));

        var serviceItem = itemService.getById(1L);
        var item= serviceItem.block();

        assert item != null;
        assertEquals(1L, item.getId());
        assertEquals("Test Item", item.getName());
        assertEquals("Test Item 1", item.getLongDescription());
        assertEquals(new BigDecimal("10.5"), item.getAmount());
    }

    @Test
    public void whenGetById_AndDoesNotExist_ThenReturnNull()
    {
        when(itemRepository.findById(1L)).thenReturn(Mono.empty());

        var serviceItem = itemService.getById(1L);
        var item= serviceItem.block();

        assert item == null;
    }

    @Test
    public void whenSave_ThenReturnCreatedItem()
    {
        var createdItem = new Item(1L, "Item", "Test item", BigDecimal.valueOf(100.50));
        when(itemRepository.save(any(Item.class))).thenReturn(Mono.just(createdItem));

        var inputItem = new Item(1L, "Item", "Test item", BigDecimal.valueOf(100.50));
        var outputItem = itemService.save(inputItem).block();

        assert outputItem == createdItem;
    }

    @Test
    public void whenUpdate_AndItemExists_ThenReturnUpdatedItem()
    {
        var currentItem = new Item(1L, "Old item", "An old item", BigDecimal.valueOf(10));
        when(itemRepository.findById(eq(1L))).thenReturn(Mono.just(currentItem));

        var updatedItem = new Item(1L, "Item", "Test item", BigDecimal.valueOf(100.50));
        when(itemRepository.save(any(Item.class))).thenReturn(Mono.just(updatedItem));

        var inputItem = new Item(1L, "Item", "Test item", BigDecimal.valueOf(100.50));
        var outputItem = itemService.update(inputItem).block();

        assert outputItem == updatedItem;
    }

    @Test
    public void whenUpdate_AndItemDoesNotExist_ThenReturnNull()
    {
        when(itemRepository.findById(eq(1L))).thenReturn(Mono.empty());

        var inputItem = new Item(1L, "Item", "Test item", BigDecimal.valueOf(100.50));
        var outputItem = itemService.update(inputItem).block();

        assert outputItem == null;
    }

    @Test
    public void whenDelete_AndExists_ThenReturnDeletedItem()
    {
        var currentItem = new Item(1L, "Item", "Test item", BigDecimal.valueOf(100.50));
        when(itemRepository.findById(eq(1L))).thenReturn(Mono.just(currentItem));
        when(itemRepository.deleteById(eq(1L))).thenReturn(Mono.empty());

        var outputItem = itemService.delete(1L).block();

        assert outputItem == currentItem;
    }

    @Test
    public void whenDelete_AndDoesNotExist_ThenReturnNull()
    {
        when(itemRepository.findById(eq(1L))).thenReturn(Mono.empty());

        var outputItem = itemService.delete(1L).block();

        assert outputItem == null;
    }
}
