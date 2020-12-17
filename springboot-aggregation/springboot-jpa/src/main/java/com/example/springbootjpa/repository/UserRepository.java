package com.example.springbootjpa.repository;

import com.example.springbootjpa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findAll();
}
