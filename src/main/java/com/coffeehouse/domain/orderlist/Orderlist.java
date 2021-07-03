package com.coffeehouse.domain.orderlist;

import com.coffeehouse.domain.coffee.Coffee;
import com.coffeehouse.domain.order.Orders;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Entity
@NoArgsConstructor
public class Orderlist implements Serializable {

    @EmbeddedId
    private OrderlistMultiid orderlistMultiid;

    private Long count;

    @MapsId("ordersUid")
    @ManyToOne
    @JoinColumn(name = "ORDERS_UID")
    private Orders orders;

    @MapsId("coffeeUid")
    @ManyToOne
    @JoinColumn(name = "COFFEE_UID")
    private Coffee coffee;

    @Builder
    public Orderlist(OrderlistMultiid orderlistMultiid, Long count, Orders orders, Coffee coffee){
        this.orderlistMultiid = orderlistMultiid;
        this.count = count;
        this.orders = orders;
        this.coffee = coffee;
    }
}
