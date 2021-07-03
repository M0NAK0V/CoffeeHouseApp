package com.coffeehouse.web.orders.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrdersSearchDto {
    private String orderDate;
    private String Address;
    private Long price;
}
