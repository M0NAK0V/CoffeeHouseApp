package com.coffeehouse.web.coffees;

import com.coffeehouse.service.CoffeeService;
import com.coffeehouse.session.UsersInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class CoffeesController {
    private final CoffeeService coffeeService;
    private final UsersInfo usersInfo;

    @GetMapping("/coffees/coffeesave")
    public String coffeesave(Model model){
        model.addAttribute("userid", usersInfo.getUserId());
        return "coffees/saveCoffee";
    }

    @GetMapping("/coffees/coffeedetail")
    public String coffeedetail(@RequestParam(value = "uid") Long uid, Model model) {
        model.addAttribute("userid", usersInfo.getUserId());
        model.addAttribute("coffeeInfo", coffeeService.findCoffeeById(uid));
        if(usersInfo.getUserId() != null){
            if(usersInfo.getUserId().equals("master")){
                model.addAttribute("master", usersInfo.getUserId());
            }
        }
        if(usersInfo.getUserId() == null){
            model.addAttribute("existSession", "notExistSession");
        }
        return "coffees/detailCoffee";
    }

    @GetMapping("/coffees/coffeeupdate")
    public String coffeeupdate(@RequestParam(value="uid") Long uid, Model model){
        model.addAttribute("userid", usersInfo.getUserId());
        model.addAttribute("coffeeInfo", coffeeService.findCoffeeById(uid));
        return "coffees/updateCoffee";
    }

    @GetMapping("/coffees/coffeeSearch")
    public String coffeesearch(@RequestParam(value = "sn") String search, Model model){
        model.addAttribute("userid", usersInfo.getUserId());
        model.addAttribute("coffeeInfo", coffeeService.findCoffeeByLike(search));
        return "coffees/searchCoffee";
    }
}
