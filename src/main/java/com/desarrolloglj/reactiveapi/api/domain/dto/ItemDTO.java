package com.desarrolloglj.reactiveapi.api.domain.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
    private Long id;

    private String name;

    private String longDescription;

    private BigDecimal amount;
}
