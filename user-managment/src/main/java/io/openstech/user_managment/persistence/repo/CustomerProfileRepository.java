package io.openstech.user_managment.persistence.repo;

import io.openstech.user_managment.persistence.domian.CustomerProfile;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerProfileRepository extends JpaRepository<CustomerProfile,Long> {
}
