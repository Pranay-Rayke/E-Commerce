package com.example.ECommerce.Repository;

import com.example.ECommerce.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer findByEmailId(String email);

    Customer findByMobileNo(String mobileNo);

}
