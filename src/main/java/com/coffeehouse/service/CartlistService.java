package com.coffeehouse.service;

import com.coffeehouse.domain.coffee.CoffeeRepository;
import com.coffeehouse.domain.cart.Cart;
import com.coffeehouse.domain.cart.CartRepository;
import com.coffeehouse.domain.cartlist.Cartlist;
import com.coffeehouse.domain.cartlist.CartlistRepository;
import com.coffeehouse.domain.cartlist.MultiId;
import com.coffeehouse.session.UsersInfo;
import com.coffeehouse.web.cartlist.dto.CartCreateDto;
import com.coffeehouse.web.cartlist.dto.CartlistAddDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CartlistService {
    private final UsersInfo usersInfo;
    private final CartRepository cartRepository;
    private final CoffeeRepository coffeeRepository;
    private final CartlistRepository cartlistRepository;
    private final UsersService usersService;
    private final CoffeeService coffeeService;

    Date now = new Date();
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy ss:mm:HH");
    String nowDate = format.format(now);

    @Transactional(readOnly = true)
    public Cart cartfindByUser(){
        return cartRepository.findByUsers_Id(usersInfo.getUserId());
    }

    @Transactional(readOnly = true)
    public boolean existCart(){
        if(cartRepository.findByUsers_Id(usersInfo.getUserId()) != null){
            return true;
        }else {
            return false;
        }
    }

    @Transactional
    public String createCart(){
        CartCreateDto cartCreateDto = new CartCreateDto();
        cartCreateDto.setCreatetime(nowDate);
        cartCreateDto.setModifytime(nowDate);
        cartCreateDto.setUsers(usersService.findUsers(usersInfo));

        return cartRepository.save(cartCreateDto.toEntity()).toString();
    }

    @Transactional
    public void deleteCart(){
        Cart cart = cartfindByUser();
        cartRepository.delete(cart);
    }

    @Transactional
    public String addCartlist(Long coffeeUid, CartlistAddDto cartlistAddDto){
        Long cartUid = cartRepository.findByUsers_Id(usersInfo.getUserId()).getUid();
        MultiId multiId = new MultiId();
        multiId.setCartUid(cartUid);
        multiId.setCoffeeUid(coffeeUid);
        cartlistAddDto.setMultiId(multiId);
        cartlistAddDto.setCoffee(coffeeService.findCoffeeById(coffeeUid));
        cartlistAddDto.setCart(cartfindByUser());

        return cartlistRepository.save(cartlistAddDto.toEntity()).toString();
    }

    @Transactional
    public void updateModifyTimeInCart(){
        Cart cart = cartfindByUser();
        cart.updateModifytime(nowDate);
    }

    @Transactional(readOnly = true)
    public List<Cartlist> findByCartuid(){
        Long cartUid = cartfindByUser().getUid();
        return cartlistRepository.findAllByCart_Uid(cartUid);
    }

    @Transactional
    public void deleteCartlist(Long coffeeUid){
        Cart cart = cartfindByUser();
        MultiId mId = new MultiId();
        mId.setCartUid(cart.getUid());
        mId.setCoffeeUid(coffeeUid);
        cartlistRepository.deleteById(mId);
    }

}
