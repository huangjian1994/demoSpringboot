package com.example.demo.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * @author by hj on 2018-1-19.
 */
@Controller
@RequestMapping("/hello")
public class HelloController {

    @ApiOperation(value = "获取所有用户",notes = "查询所有用户")
    @RequestMapping(value = {"/hi","say"},method = RequestMethod.GET)
    public String say(){
        return "/system/hello";
    }
}
