package com.coffeehouse.domain.coffee;

import com.coffeehouse.domain.cartlist.Cartlist;
import com.coffeehouse.domain.orderlist.Orderlist;
import com.coffeehouse.web.coffees.dto.CoffeeUpdateCountDto;
import com.coffeehouse.web.coffees.dto.CoffeeUpdateDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Coffee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COFFEE_UID")
    private Long uid;

    private String coffeeName;

    private Long coffeeCount;

    private Long coffeePrice;

    private String coffeeDetail;

    private String coffeePublish;

    private String coffeeAuthor;

    @OneToMany(mappedBy = "coffee", cascade = {CascadeType.REMOVE})
    private List<Cartlist> cartlists;

    @OneToMany(mappedBy = "coffee", orphanRemoval = true)
    private List<Orderlist> orderlists;

    @Builder
    public Coffee(Long uid, String coffeeName, Long coffeeCount, Long coffeePrice, String coffeeDetail,
                  String coffeePublish, String coffeeAuthor, List<Cartlist> cartlists, List<Orderlist> orderlists){
        this.uid = uid;
        this.coffeeName = coffeeName;
        this.coffeeCount = coffeeCount;
        this.coffeePrice = coffeePrice;
        this.coffeeDetail = coffeeDetail;
        this.coffeePublish = coffeePublish;
        this.coffeeAuthor = coffeeAuthor;
        this.cartlists = cartlists;
        this.orderlists = orderlists;
    }

    public void updateCoffee(CoffeeUpdateDto coffeeUpdateDto){
        this.coffeeName = coffeeUpdateDto.getCoffeeName();
        this.coffeeCount = coffeeUpdateDto.getCoffeeCount();
        this.coffeePrice = coffeeUpdateDto.getCoffeePrice();
        this.coffeeDetail = coffeeUpdateDto.getCoffeeDetail();
        this.coffeePublish = coffeeUpdateDto.getCoffeePublish();
        this.coffeeAuthor = coffeeUpdateDto.getCoffeeAuthor();
    }

    public void updateCount(CoffeeUpdateCountDto coffeeUpdateCountDto){
        this.coffeeCount = coffeeUpdateCountDto.getCoffeeCount();
    }
}
