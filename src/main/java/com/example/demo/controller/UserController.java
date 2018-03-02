package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

/**
 * @author by hj on 2018-1-30.
 */
@RestController
@Api("userController相关api")
public class UserController {

    // 创建线程安全的Map
    static Map<Integer, User> users = Collections.synchronizedMap(new HashMap<Integer, User>());
    @Autowired
    private UserRepository userRepository;


    @ApiOperation(value="获取图书列表", notes="获取图书列表")
    @RequestMapping(value={""}, method= RequestMethod.GET)
    public List<User> getBook() {
        List<User> book = new ArrayList<>(users.values());
        return book;
    }


    /**
    	 *
    	Description :返回所有人员列表
    	@return List
    	@author：hj
    	@Create 2018-2-8 11:44
    	 */
    @ApiOperation(value = "获取所有用户",notes = "查询所有用户")
    @RequestMapping(value={"/users"}, method=RequestMethod.GET)
    public List<User> userlList(){
        return userRepository.findAll();
    }

    /**
     * 新增人员
     * @param user 用户
     * @return user
     */
    @ApiOperation(value = "新增用户",notes = "新增用户")
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
    @ApiOperation(value = "查询用户信息",notes = "根据ID查询用户信息")
    @ApiImplicitParam(name = "id",value = "用户id",dataType = "Integer",
            required=true)
    @ApiResponses({
            @ApiResponse(code=400,message="请求参数没填好"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对")
    })
    @GetMapping(value = "/user/{id}")
    public User fetchById(@PathVariable("id") Integer id){
        return userRepository.findOne(id);
    }

   /**
     * 根据id查询
     * @param name name
     * @return user
     */
    @ApiOperation(value = "查询用户信息",notes = "根据name查询用户信息")
    @ApiImplicitParam(name = "name",value = "用户name",dataType = "String",
            required=true)
    @ApiResponses({
            @ApiResponse(code=400,message="请求参数没填好"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对")
    })
    @RequestMapping(value = "/user/{name}",method =RequestMethod.GET)
    public User fetchByName(@PathVariable("name") String name){
        return userRepository.findByName(name);
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
