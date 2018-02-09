package com.example.demo.service;

import com.example.demo.entity.User;
import org.springframework.stereotype.Service;

/**
 * @author by hj on 2018-2-9.
 */

@Service
public interface UserService {

    /**
     *
     * @return
     */
    User findOne(Integer id);
}
