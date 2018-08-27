package com.example.demo.controller;


import com.example.demo.config.DataResult;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.google.common.collect.Lists;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;
import org.apache.commons.lang3.StringUtils;


/**
 * @author by hj on 2018-1-30.
 */
@Controller
@RequestMapping("/user")
@Api("userController相关api")
public class UserController {

    // 创建线程安全的Map
    static Map<Integer, User> users = Collections.synchronizedMap(new HashMap<Integer, User>());
    @Autowired
    private UserRepository userRepository;

    @ApiOperation(value="获取图书列表", notes="获取图书列表")
    @RequestMapping(value={"book"}, method= RequestMethod.GET)
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
    public String  userlList(ModelMap modelMap){
        List<User> userList = userRepository.findAll();
        modelMap.put("userList", userList);
        return "system/hello";
    }

    /**
     *
     Description :返回查询人员列表
     @return List
     @author：hj
     @Create 2018-2-8 11:44
     */
    @ApiOperation(value = "获取查询用户",notes = "查询用户")
    @RequestMapping(value={"/searchusers"}, method=RequestMethod.GET)
    public String  searchUserlList(HttpServletRequest request,ModelMap modelMap){
        String search = request.getParameter("search");
        System.out.println(search);

        List<User> userList = Lists.newArrayList();
        if (StringUtils.isNotBlank(search)) {
            search = String.format("%%%s%%", search);
            userList = userRepository.findByNameLike(search);
        }else {
            userList = userRepository.findAll();
        }

        modelMap.put("list", userList);

        return "system/list";
    }


    /**
     * 编辑用户
     * @param request
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "edit")
    public String edit(HttpServletRequest request, ModelMap modelMap) {
        String id = request.getParameter("id");

        if (StringUtils.isNotBlank(id)) {
            Integer iid = Integer.parseInt(id);
            User user = userRepository.findOne(iid);
            modelMap.put("user", user);
        }
        return "system/edit";
    }

    @ApiOperation(value = "新增用户信息",notes = "新增用户")
    @RequestMapping(value = "save")
    @ResponseBody
    public DataResult save(HttpServletRequest request) {

        String  iid = request.getParameter("id");
        Integer id = null;
        if(StringUtils.isBlank(iid)){
        }else {
            id = Integer.parseInt(iid);
        }

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String age = request.getParameter("age");


        if (StringUtils.isBlank(username)) {
            return DataResult.buildError("用户名不能为空！");
        }
       /* User userByName = userDao.findByUserName(username);
        if (userByName != null && !userByName.getId().equals(id)) {
            return DataResult.buildError("用户名与其他用户重复！");
        }*/

        if (StringUtils.isBlank(password)) {
            password = "123456";
        }
        User user = new User();
        /*InvocationHandler handler = new CoderDynamicProxy(user);
        ClassLoader c = user.getClass().getClassLoader();
        Proxy.newProxyInstance(c,user.getClass().getInterfaces(),handler);
        */

        user.setUsername(username);
        user.setPassword(password);
        user.setName(name);
        user.setAge(age);



        if (id == null) {
            System.out.println("新");
            userRepository.save(user);
        }else {
            System.out.println("更新");
            userRepository.updateNameById(name,username,age,password,id);
        }

        System.out.println("保存成功");
        return DataResult.buildSuccess();
    }


    @ApiOperation(value = "根据id删除用户",notes = "删除")
    @RequestMapping(value = "deluser")
    @ResponseBody
    public DataResult delUser(HttpServletRequest request) {
        Integer id = Integer.parseInt(request.getParameter("id"));
        System.out.println(id);

        userRepository.removeUserById(id);
        System.out.println("成功");
        DataResult.buildError("删除失败");
        System.out.println("？？？？" );
        return DataResult.buildSuccess();
    }

    /**
     * 新增人员
     * @param user 用户
     * @return user
     */
    @ApiOperation(value = "新增用户",notes = "新增用户")
    @PostMapping(value = "/add")
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
    @GetMapping(value = "findbyid/{id}")
    public User fetchById(@PathVariable("id") Integer id){
        return userRepository.findOne(id);
    }

   /**
     * 根据name查询, /demo/user/search/黄健
     * @param name name
     * @return user
     */
    /*@ApiOperation(value = "查询用户信息",notes = "根据name查询用户信息")
    @ApiImplicitParam(name = "name",value = "用户name",dataType = "String",
            required=true)
    @ApiResponses({
            @ApiResponse(code=400,message="请求参数没填好"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对")
    })
    @RequestMapping(value = "search/{name}",method =RequestMethod.GET)
    public User fetchByName(@PathVariable("name") String name){
        DataResult resultJson = new DataResult();
        System.out.println(name);
        return userRepository.findByNameLike(name);
    }*/


    @ApiOperation(value="根据id更新用户信息",notes = "根据id更新用户信息")
    @ApiImplicitParam(name = "id",value = "用户id",dataType = "Integer",required =true)
    @PutMapping(value = "update/{id}")
    public User updateUser(@PathVariable("id") Integer id,
                           @RequestParam("name") String name ,
                           @RequestParam("username") String username,
                           @RequestParam("age") String age,
                           @RequestParam("password") String password){
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setName(name);
        user.setAge(age);
        user.setPassword(password);
        return userRepository.save(user);
    }

    @DeleteMapping(value = "del/{id}}")
    public void deleteUser(@PathVariable("id") Integer id){
        userRepository.delete(id);
    }

}
