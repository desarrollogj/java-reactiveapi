package com.desarrolloglj.reactiveapi.api.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(value = "Items")
public class Item {
    @Id
    @Column(value = "id")
    private Long id;

    @Column(value = "name")
    private String name;

    @Column(value = "long_description")
    private String longDescription;

    @Column(value = "amount")
    private BigDecimal amount;
}
