package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author by hj on 2018-2-8.
 */
public interface UserRepository extends JpaRepository<User,Integer> {

}
