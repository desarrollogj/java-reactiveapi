package com.desarrolloglj.reactiveapi.api.mapper.mapStruct;

import com.desarrolloglj.reactiveapi.api.domain.Item;
import com.desarrolloglj.reactiveapi.api.domain.dto.ItemSaveDTO;
import com.desarrolloglj.reactiveapi.api.mapper.ItemSaveDTOMapper;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-08-02T23:29:10-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (Oracle Corporation)"
)
@Component
public class MapStructItemSaveDTOMapper implements ItemSaveDTOMapper {

    @Override
    public ItemSaveDTO adaptToItemSaveDTO(Item item) {
        if ( item == null ) {
            return null;
        }

        ItemSaveDTO itemSaveDTO = new ItemSaveDTO();

        itemSaveDTO.setName( item.getName() );
        itemSaveDTO.setLongDescription( item.getLongDescription() );
        itemSaveDTO.setAmount( item.getAmount() );

        return itemSaveDTO;
    }

    @Override
    public Item adaptToItem(ItemSaveDTO itemDTO) {
        if ( itemDTO == null ) {
            return null;
        }

        Item item = new Item();

        item.setName( itemDTO.getName() );
        item.setLongDescription( itemDTO.getLongDescription() );
        item.setAmount( itemDTO.getAmount() );

        return item;
    }
}
