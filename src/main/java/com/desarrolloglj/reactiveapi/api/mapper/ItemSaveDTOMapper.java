package com.desarrolloglj.reactiveapi.api.mapper;

import com.desarrolloglj.reactiveapi.api.domain.Item;
import com.desarrolloglj.reactiveapi.api.domain.dto.ItemSaveDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        implementationName = "MapStruct<CLASS_NAME>",
        implementationPackage = "<PACKAGE_NAME>.mapStruct")
public interface ItemSaveDTOMapper {
    ItemSaveDTO adaptToItemSaveDTO(Item item);

    @Mapping(target = "id", ignore = true)
    Item adaptToItem(ItemSaveDTO itemDTO);
}
