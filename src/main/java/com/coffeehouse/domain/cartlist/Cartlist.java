package com.coffeehouse.domain.cartlist;

import com.coffeehouse.domain.coffee.Coffee;
import com.coffeehouse.domain.cart.Cart;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Entity
@NoArgsConstructor
public class Cartlist implements Serializable {

    @EmbeddedId
    private MultiId multiId;

    private Long coffeeCount;

    @MapsId("cartUid")
    @ManyToOne
    @JoinColumn(name = "CART_UID")
    private Cart cart;

    @MapsId("coffeeUid")
    @ManyToOne
    @JoinColumn(name = "COFFEE_UID")
    private Coffee coffee;


    @Builder
    public Cartlist(MultiId multiId, Long coffeeCount, Cart cart, Coffee coffee){
        this.multiId = multiId;
        this.coffeeCount = coffeeCount;
        this.coffee = coffee;
        this.cart = cart;
    }
}
