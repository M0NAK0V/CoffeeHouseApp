package com.coffeehouse.domain.order;

import com.coffeehouse.domain.orderlist.Orderlist;
import com.coffeehouse.domain.user.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @ManyToOne
    @JoinColumn(name = "USERS_ID")
    private Users users;

    private String date;

    private Long amount;

    private String cardId;

    private String cardType;

    private String cardDate;

    private Long shippingNum;

    private String basicaddr;

    private String detailaddr;

    @OneToMany(mappedBy = "orders", orphanRemoval = true)
    private List<Orderlist> orderlists;


    @Builder
    public Orders(Long uid, Users users, String date, Long amount, String cardId, String cardType, String cardDate, Long shippingNum, String basicaddr, String detailaddr, List<Orderlist> orderlists){
        this.uid= uid;
        this.users = users;
        this.date = date;
        this.amount = amount;
        this.cardId = cardId;
        this.cardType = cardType;
        this.cardDate= cardDate;
        this.shippingNum = shippingNum;
        this.basicaddr = basicaddr;
        this.detailaddr = detailaddr;
        this.orderlists = orderlists;
    }

}
