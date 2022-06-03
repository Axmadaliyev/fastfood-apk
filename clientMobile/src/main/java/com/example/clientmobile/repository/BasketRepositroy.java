package com.example.clientmobile.repository;

import com.example.clientmobile.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepositroy  extends JpaRepository<Basket,Long> {

}
