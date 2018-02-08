package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * @author by hj on 2018-1-19.
 */
@Controller
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping(value = {"/hi","say"},method = RequestMethod.GET)
    public String say(){
        return "/system/hello";
    }
}
