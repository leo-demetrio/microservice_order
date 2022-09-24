package com.leodemetrio.order.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemOfOrderDto {

    private Long id;
    private Integer amount;
    private String description;
}
