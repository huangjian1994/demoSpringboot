package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author by hj on 2018-1-30.
 */
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    /**
    	 *
    	Description :返回所有人员列表
    	@return List
    	@author：hj
    	@Create 2018-2-8 11:44
    	 */
    @GetMapping(value = "/users")
    public List<User> userlList(){
        return userRepository.findAll();
    }

    /**
     * 新增人员
     * @param user 用户
     * @return user
     */
    @PostMapping(value = "/adduser")
    public User addUser(@Valid User user, BindingResult bindingResult ){
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
            return null;
        }
    	user.setName(user.getName());
    	user.setUsername(user.getUsername());
    	user.setAge(user.getAge());
    	user.setPassword(user.getPassword());
    	return userRepository.save(user);
    }

    /**
     * 根据id查询
     * @param id id
     * @return user
     */
    @GetMapping(value = "/user/{id}")
    public User fetchById(@PathVariable("id") Integer id){
        return userRepository.findOne(id);
    }

    @PutMapping(value = "/user/{id}")
    public User updateUser(@PathVariable("id") Integer id,
                           @RequestParam("name") String name ,
                           @RequestParam("username") String username,
                           @RequestParam("age") Integer age,
                           @RequestParam("password") String password){
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setName(name);
        user.setAge(age);
        user.setPassword(password);
        return userRepository.save(user);
    }

    @DeleteMapping(value = "/user/{id}")
    public void deleteUser(@PathVariable("id") Integer id){
        userRepository.delete(id);
    }

}
