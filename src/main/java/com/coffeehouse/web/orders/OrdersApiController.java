package com.coffeehouse.web.orders;

import com.coffeehouse.service.CoffeeService;
import com.coffeehouse.service.CartlistService;
import com.coffeehouse.service.OrdersService;
import com.coffeehouse.service.UsersService;
import com.coffeehouse.session.UsersInfo;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Api(value = "Order", description = "\n" + "Order management", tags = { "Order" })
@RequestMapping("/orders")
@RestController
@RequiredArgsConstructor
public class OrdersApiController {
    private final CartlistService cartlistService;
    private final OrdersService ordersService;
    private final UsersService usersService;
    private final UsersInfo usersInfo;
    private final CoffeeService coffeeService;

    Date now = new Date();
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy ss:mm:HH");
    String nowDate = format.format(now);

    @PostMapping("/addOrder")
    @ResponseBody
    public void addOrder(
        @RequestParam(value = "coffeeUid[]") List<Long> coffeeUid,
        @RequestParam(value = "count[]") List<Long> count,
        @RequestParam(value = "cardid") String cardid,
        @RequestParam(value = "addrUid") Long addrUid) {

        ordersService.createOrders(coffeeUid, count, cardid, addrUid);

        ordersService.addOrderslist(coffeeUid, count);

        coffeeService.updateCountCoffee(coffeeUid, count);

    }

    @PostMapping("/cartOrder")
    @ResponseBody
    public void cartlistOrder(
        @RequestParam(value = "coffeeUid[]") List<Long> coffeeUid,
        @RequestParam(value = "count[]") List<Long> count,
        @RequestParam(value = "cardid") String cardid,
        @RequestParam(value = "addrUid") Long addrUid
    ){

        ordersService.createOrders(coffeeUid, count, cardid, addrUid);

        ordersService.addOrderslist(coffeeUid, count);

        coffeeService.updateCountCoffee(coffeeUid, count);

        cartlistService.deleteCart();

    }
}
