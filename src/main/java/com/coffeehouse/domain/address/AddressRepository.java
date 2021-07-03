package com.coffeehouse.domain.address;

import com.coffeehouse.domain.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findAllByUsers_Id(String userid);
}
