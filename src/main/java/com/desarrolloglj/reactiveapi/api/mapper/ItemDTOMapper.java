package com.desarrolloglj.reactiveapi.api.mapper;

import com.desarrolloglj.reactiveapi.api.domain.Item;
import com.desarrolloglj.reactiveapi.api.domain.dto.ItemDTO;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        implementationName = "MapStruct<CLASS_NAME>",
        implementationPackage = "<PACKAGE_NAME>.mapStruct")
public interface ItemDTOMapper {
    ItemDTO adaptToItemDTO(Item item);
    Item adaptToItem(ItemDTO itemDTO);
}
