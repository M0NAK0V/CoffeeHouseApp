package com.coffeehouse.web.orderslist.dto;

import com.coffeehouse.domain.coffee.Coffee;
import com.coffeehouse.domain.order.Orders;
import com.coffeehouse.domain.orderlist.Orderlist;
import com.coffeehouse.domain.orderlist.OrderlistMultiid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderslistAddDto {
        private OrderlistMultiid orderlistMultiid;

        private Long  count;

        private Orders orders;

        private Coffee coffee;

        public Orderlist toEntity(){
            return Orderlist.builder()
            .orderlistMultiid(orderlistMultiid)
            .count(count)
            .orders(orders)
            .coffee(coffee)
            .build();
        }
}
