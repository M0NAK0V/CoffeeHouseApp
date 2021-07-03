package com.coffeehouse.web.orders;

import com.coffeehouse.service.CoffeeService;
import com.coffeehouse.service.OrdersService;
import com.coffeehouse.service.UsersService;
import com.coffeehouse.session.UsersInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class OrdersController {
    private final UsersInfo usersInfo;
    private final OrdersService ordersService;
    private final UsersService usersService;
    private final CoffeeService coffeeservice;

    @GetMapping("/orders/addOrder")
    public String addOrder(@RequestParam(value = "coffeeUid") Long coffeeUid,
                           @RequestParam(value="count") Long count ,Model model){
        model.addAttribute("userid", usersInfo.getUserId());
        model.addAttribute("cardInfo", usersService.findAllCard(usersInfo));
        model.addAttribute("addrInfo", usersService.findAllAddr(usersInfo));
        model.addAttribute("coffeeInfo", coffeeservice.findCoffeeById(coffeeUid));
        model.addAttribute("count", count);

        return "orders/addOrder";
    }

    @GetMapping("/orders/addCartlistOrder")
    public String addCartlistOrder(@RequestParam(value = "coffeeUid[]") List<Long> coffeeUid,
                                   @RequestParam(value = "count[]") Long[] count , Model model){
        model.addAttribute("userid", usersInfo.getUserId());
        model.addAttribute("cardInfo", usersService.findAllCard(usersInfo));
        model.addAttribute("addrInfo", usersService.findAllAddr(usersInfo));
        model.addAttribute("coffeeInfo", coffeeservice.findCoffeeByArrayUid(coffeeUid));
        model.addAttribute("count", count);

        return "orders/addCartlistOrder";
    }


}
