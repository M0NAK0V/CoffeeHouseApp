package com.coffeehouse.domain.orderlist;


import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class OrderlistMultiid implements Serializable {

    private Long ordersUid;

    private Long coffeeUid;
}
