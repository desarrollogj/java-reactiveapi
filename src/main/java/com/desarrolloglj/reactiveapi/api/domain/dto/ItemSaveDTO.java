package com.desarrolloglj.reactiveapi.api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemSaveDTO {
    @NotNull(message = "Name is required")
    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Description is required")
    @NotBlank(message = "Description is required")
    private String longDescription;

    @NotNull(message = "Amount is required")
    @Min(value = 0, message = "Amount should be zero or a positive value")
    private BigDecimal amount;
}
