package com.bank.mybank.repository;

import com.bank.mybank.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
    @Query("select distinct user from users user where user.username = ?1")
    Optional<User> findByUsername(@Param("username") String username);
}
