package com.coffeehouse.domain.address;

import com.coffeehouse.domain.user.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @ManyToOne
    @JoinColumn(name = "USERS_ID")
    private Users users;

    private Long shippingNum;

    private String basicAddr;

    private String detailAddr;

    @Builder
    public Address(Long uid, Users users, Long shippingNum, String basicAddr, String detailAddr){
        this.uid = uid;
        this.users = users;
        this.shippingNum = shippingNum;
        this.basicAddr = basicAddr;
        this.detailAddr = detailAddr;
    }
}
