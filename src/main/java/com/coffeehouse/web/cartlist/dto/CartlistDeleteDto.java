package com.coffeehouse.web.cartlist.dto;

import com.coffeehouse.domain.coffee.Coffee;
import com.coffeehouse.domain.cart.Cart;
import com.coffeehouse.domain.cartlist.Cartlist;
import com.coffeehouse.domain.cartlist.MultiId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartlistDeleteDto {
    private MultiId multiId;
    private Long coffeeCount;
    private Cart cart;
    private Coffee coffee;

    public Cartlist toEntity(){
        return Cartlist.builder()
                .multiId(multiId)
                .coffeeCount(coffeeCount)
                .coffee(coffee)
                .cart(cart)
                .build();
    }
}
