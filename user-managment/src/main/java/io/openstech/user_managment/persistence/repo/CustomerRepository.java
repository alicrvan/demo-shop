package io.openstech.user_managment.persistence.repo;

import io.openstech.user_managment.persistence.domian.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Optional<Customer> findByUsername(String username);
    void deleteByUsername(String username);
}
