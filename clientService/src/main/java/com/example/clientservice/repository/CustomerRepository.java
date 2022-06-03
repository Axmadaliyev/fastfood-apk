package com.example.clientservice.repository;

import com.example.clientservice.entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customers,Long> {

    @Query(value = "select * from customers where id=? and user_type='CLIENT'",nativeQuery = true)
    Optional<Customers> getOneClient(Long id);



    @Query(value = "select * from customers where id=? and user_type='OPERATOR'",nativeQuery = true)
    Optional<Customers> getOneOperator(Long id);


    @Query(value = "select * from customers where id=? and user_type='COURIER'",nativeQuery = true)
    Optional<Customers> getOneCurier(Long id);

}
