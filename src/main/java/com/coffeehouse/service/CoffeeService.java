package com.coffeehouse.service;

import com.coffeehouse.domain.coffee.Coffee;
import com.coffeehouse.domain.coffee.CoffeeRepository;
import com.coffeehouse.domain.cart.CartRepository;
import com.coffeehouse.session.UsersInfo;
import com.coffeehouse.web.coffees.dto.CoffeeDeleteDto;
import com.coffeehouse.web.coffees.dto.CoffeeSaveDto;
import com.coffeehouse.web.coffees.dto.CoffeeUpdateCountDto;
import com.coffeehouse.web.coffees.dto.CoffeeUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CoffeeService {
    private final CoffeeRepository coffeeRepository;
    private final CartRepository cartRepository;
    private final UsersInfo usersInfo;

    @Transactional
    public String saveCoffee(CoffeeSaveDto coffeeSaveDto){
        return coffeeRepository.save(coffeeSaveDto.toEntity()).toString();
    }

    @Transactional(readOnly = true)
    public List<Coffee> findAllCoffee(){
        return coffeeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Coffee findCoffeeById(Long coffeeUid){
        return coffeeRepository.findById(coffeeUid).get();
    }

    @Transactional
    public void  updateCoffee(Long coffeeUid, CoffeeUpdateDto coffeeUpdateDto){
        findCoffeeById(coffeeUid).updateCoffee(coffeeUpdateDto);
    }

    @Transactional
    public void deleteCoffee(Long uid){
        coffeeRepository.delete(findCoffeeById(uid));
    }

    @Transactional(readOnly = true)
    public List<Coffee> findCoffeeByLike(String name){
        return coffeeRepository.findAllByCoffeeNameIgnoreCaseContainingOrCoffeeAuthorIgnoreCaseContainingOrCoffeePublishIgnoreCaseContaining(name, name, name);
    }

    @Transactional
    public void updateCountCoffee(List<Long> coffeeUid, List<Long> count){
        int index = 0;
        for (Long coffeeuid : coffeeUid) {
            System.out.println("Coffee count update");
            Coffee coffee = new Coffee();
            coffee = findCoffeeById(coffeeuid);
            Long updateCoffeeCount = coffee.getCoffeeCount() - count.get(index);
            CoffeeUpdateCountDto coffeeUpdateCountDto = new CoffeeUpdateCountDto();
            coffeeUpdateCountDto.setCoffeeCount(updateCoffeeCount);
            findCoffeeById(coffeeuid).updateCount(coffeeUpdateCountDto);
            index++;
        }
    }

    @Transactional(readOnly = true)
    public List<Coffee> findCoffeeByArrayUid(List<Long> coffeeUid){
        List<Coffee> arrCoffee = new ArrayList<Coffee>();
        for (Long uid : coffeeUid) {
            arrCoffee.add(coffeeRepository.getOne(uid));
        }
        return arrCoffee;
    }
}

