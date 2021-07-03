package com.coffeehouse.service;

import com.coffeehouse.domain.address.Address;
import com.coffeehouse.domain.coffee.Coffee;
import com.coffeehouse.domain.card.Card;
import com.coffeehouse.domain.order.Orders;
import com.coffeehouse.domain.order.OrdersRepository;
import com.coffeehouse.domain.orderlist.OrderlistMultiid;
import com.coffeehouse.domain.orderlist.OrderlistRepository;
import com.coffeehouse.domain.user.Users;
import com.coffeehouse.session.UsersInfo;
import com.coffeehouse.web.orders.dto.OrdersCreateDto;
import com.coffeehouse.web.orders.dto.OrdersSearchDto;
import com.coffeehouse.web.orderslist.dto.OrderslistAddDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrdersService {
    private final CoffeeService coffeeService;
    private final UsersService usersService;
    private final OrdersRepository ordersRepository;
    private final OrderlistRepository orderlistRepository;
    private final UsersInfo usersInfo;


    Date now = new Date();
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy ss:mm:HH");
    String nowDate = format.format(now);


    @Transactional
    public void createOrders(List<Long> coffeeUid, List<Long> count, String cardId, Long addrId){
        int totalprice = 0;
        int index = 0;
        for (Long coffeeuid : coffeeUid) {
            System.out.println("123123123");
            Coffee coffee = new Coffee();
            coffee = coffeeService.findCoffeeById(coffeeuid);
            totalprice += (coffee.getCoffeePrice()*count.get(index));
            index++;
        }
        Long totalPrice = (long) totalprice;

        Card card = new Card();
        card = usersService.findCardByCardId(cardId);

        Address addr = new Address();
        addr = usersService.findAddrByUid(addrId);

        Users user = usersService.findUsers(usersInfo);
        
        OrdersCreateDto ordersCreateDto = new OrdersCreateDto();
        ordersCreateDto.setUsers(user);
        ordersCreateDto.setDate(nowDate);
        ordersCreateDto.setCardId(cardId);
        ordersCreateDto.setCardType(card.getType());
        ordersCreateDto.setCardDate(card.getDatetime());
        ordersCreateDto.setBasicaddr(addr.getBasicAddr());
        ordersCreateDto.setDetailaddr(addr.getDetailAddr());
        ordersCreateDto.setShippingNum(addr.getShippingNum());
        ordersCreateDto.setAmount(totalPrice);
        ordersRepository.save(ordersCreateDto.toEntity());
    }

    @Transactional
    public void addOrderslist(List<Long> coffeeUid, List<Long> count){
        int index = 0;
        for (Long coffeeuid : coffeeUid) {
            System.out.println(coffeeUid +"asdasd"+ count);
            Orders lastAddOrders = new Orders();
            lastAddOrders = lastAddOrders();

            Coffee coffee = new Coffee();
            coffee = coffeeService.findCoffeeById(coffeeuid);
    
            OrderlistMultiid orderlistMultiid = new OrderlistMultiid();
            orderlistMultiid.setCoffeeUid(coffeeuid);
            orderlistMultiid.setOrdersUid(lastAddOrders.getUid());
    
            OrderslistAddDto orderslistAddDto = new OrderslistAddDto();
            orderslistAddDto.setCoffee(coffee);
            orderslistAddDto.setOrderlistMultiid(orderlistMultiid);
            orderslistAddDto.setOrders(lastAddOrders);
            orderslistAddDto.setCount(count.get(index));
            
            orderlistRepository.save(orderslistAddDto.toEntity());
            index++;
        }

    }

    @Transactional(readOnly = true)
    public Orders lastAddOrders(){
        return ordersRepository.findAllByOrderByUidDesc().get(0);
    }

    @Transactional(readOnly = true)
    public List<OrdersSearchDto> orderSearch(){
        List<OrdersSearchDto> ordersSearchDto = new ArrayList<OrdersSearchDto>();
        List<Orders> orders = ordersRepository.findAllByUsers_Id(usersInfo.getUserId());
        for (Orders order : orders) {
            OrdersSearchDto osd = new OrdersSearchDto();
            osd.setOrderDate(order.getDate());
            osd.setPrice(order.getAmount());
            String Address = order.getBasicaddr()+" "+order.getDetailaddr();
            osd.setAddress(Address);
            ordersSearchDto.add(osd);
        }

        return ordersSearchDto;
    }
}
