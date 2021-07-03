package com.coffeehouse.web.coffees.dto;

import com.coffeehouse.domain.coffee.Coffee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CoffeeInfoDto {

    private Long uid;

    private String coffeeName;

    private Long coffeeCount;

    private Long coffeePrice;

    private String coffeeDetail;

    private String coffeePublish;

    private String coffeeAuthor;

    public Coffee toEntity(){
        return Coffee.builder().uid(uid).coffeeName(coffeeName).coffeeCount(coffeeCount).coffeePrice(coffeePrice)
                .coffeeDetail(coffeeDetail).coffeePublish(coffeePublish).coffeeAuthor(coffeeAuthor).build();
    }
}
