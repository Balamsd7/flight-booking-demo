package com.balamsd7.flightbooking.repository;

import com.balamsd7.flightbooking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRegisterRepository extends JpaRepository<User, Integer> {

    User findByUserName(String userName);

    Boolean existsByUserName(String username);

    Boolean existsByEmailId(String emailId);
}
