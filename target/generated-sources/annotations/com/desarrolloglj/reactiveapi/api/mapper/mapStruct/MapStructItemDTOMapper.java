package com.desarrolloglj.reactiveapi.api.mapper.mapStruct;

import com.desarrolloglj.reactiveapi.api.domain.Item;
import com.desarrolloglj.reactiveapi.api.domain.dto.ItemDTO;
import com.desarrolloglj.reactiveapi.api.mapper.ItemDTOMapper;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-08-02T23:29:10-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (Oracle Corporation)"
)
@Component
public class MapStructItemDTOMapper implements ItemDTOMapper {

    @Override
    public ItemDTO adaptToItemDTO(Item item) {
        if ( item == null ) {
            return null;
        }

        ItemDTO itemDTO = new ItemDTO();

        itemDTO.setId( item.getId() );
        itemDTO.setName( item.getName() );
        itemDTO.setLongDescription( item.getLongDescription() );
        itemDTO.setAmount( item.getAmount() );

        return itemDTO;
    }

    @Override
    public Item adaptToItem(ItemDTO itemDTO) {
        if ( itemDTO == null ) {
            return null;
        }

        Item item = new Item();

        item.setId( itemDTO.getId() );
        item.setName( itemDTO.getName() );
        item.setLongDescription( itemDTO.getLongDescription() );
        item.setAmount( itemDTO.getAmount() );

        return item;
    }
}
