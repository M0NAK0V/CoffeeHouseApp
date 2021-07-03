package com.coffeehouse.domain.cartlist;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartlistRepository extends JpaRepository<Cartlist, MultiId> {
    List<Cartlist> findAllByCart_Uid(Long uid);
}
