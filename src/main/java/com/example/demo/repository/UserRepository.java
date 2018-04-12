package com.example.demo.repository;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author by hj on 2018-2-8.
 */
public interface UserRepository extends JpaRepository<User,Integer> {

    /**
     * 根据名字查或者用户名
     * @param name
     * @return
     */
    User findByNameLike(String name);


}
