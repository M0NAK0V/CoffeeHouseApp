package com.coffeehouse.web.coffees.dto;

import com.coffeehouse.domain.coffee.Coffee;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CoffeeSaveDto {

    private String coffeeName;

    private Long coffeeCount;

    private Long coffeePrice;

    private String coffeeDetail;

    private String coffeePublish;

    private String coffeeAuthor;

    @Builder
    public CoffeeSaveDto(String coffeeName, String coffeeAuthor, String coffeePublish,
                         String coffeeDetail, Long coffeeCount, Long coffeePrice){
        this.coffeeName = coffeeName;
        this.coffeeAuthor = coffeeAuthor;
        this.coffeePublish = coffeePublish;
        this.coffeeDetail = coffeeDetail;
        this.coffeeCount = coffeeCount;
        this.coffeePrice = coffeePrice;
    }

    public Coffee toEntity(){
        return Coffee.builder().coffeeName(coffeeName).coffeeCount(coffeeCount).coffeePrice(coffeePrice)
                .coffeeDetail(coffeeDetail).coffeePublish(coffeePublish).coffeeAuthor(coffeeAuthor).build();
    }
}
