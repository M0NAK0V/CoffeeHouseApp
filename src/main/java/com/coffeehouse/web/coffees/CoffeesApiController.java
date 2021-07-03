package com.coffeehouse.web.coffees;

import com.coffeehouse.config.ApiResponse;
import com.coffeehouse.service.CoffeeService;
import com.coffeehouse.web.coffees.dto.CoffeeSaveDto;
import com.coffeehouse.web.coffees.dto.CoffeeUpdateDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Api(value = "coffees", description = "\n" +
        "Coffee management", tags = { "\n" +
        "coffees" })
@RequestMapping("/coffees")
@RestController
@RequiredArgsConstructor
public class CoffeesApiController {
    private final CoffeeService coffeeService;

    @ApiOperation(value = "Coffee registration")
    @PostMapping("/saveCoffees")
    public ResponseEntity<?> saveCoffees(@RequestBody CoffeeSaveDto coffeeSaveDto){
        ApiResponse result = null;
        try {
            result = new ApiResponse(true, "\n" + "success", coffeeService.saveCoffee(coffeeSaveDto));
            return ResponseEntity.ok().body(result);
        }catch (Exception e){
            e.printStackTrace();
            result = new ApiResponse(false, e.getMessage(), null);
            return ResponseEntity.badRequest().body(result);
        }
    }

    @ApiOperation(value = "\n" + "Coffee modification")
    @PostMapping("/updateCoffees/{uid}")
    public ResponseEntity<?> updateCoffees(@PathVariable("uid") Long uid, @RequestBody CoffeeUpdateDto coffeeUpdateDto){
        ApiResponse result = null;
        coffeeUpdateDto.setUid(uid);
        try {
            coffeeService.updateCoffee(uid, coffeeUpdateDto);
            result = new ApiResponse(true, "\n" + "success", coffeeService.findCoffeeById(uid));
            return ResponseEntity.ok().body(result);
        }catch (Exception e){
            e.printStackTrace();
            result = new ApiResponse(false, e.getMessage(), null);
            return ResponseEntity.badRequest().body(result);
        }
    }

    @ApiOperation(value = "\n" + "Delete coffee")
    @PostMapping("/deleteCoffees/{uid}")
    public RedirectView deleteCoffees(@PathVariable("uid") Long uid){
        coffeeService.deleteCoffee(uid);
        return new RedirectView("/");
    }

}
