package com.coffeehouse.domain.coffee;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
    List<Coffee> findAllByCoffeeNameIgnoreCaseContainingOrCoffeeAuthorIgnoreCaseContainingOrCoffeePublishIgnoreCaseContaining(String name, String Author, String Publish);
}
