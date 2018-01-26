package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * @author by hj on 2018-1-19.
 */
@Controller
public class HelloController {

    @RequestMapping(value = {"/hello","say"},method = RequestMethod.GET)
    public String say(){
        return "/system/hello";
    }
}
