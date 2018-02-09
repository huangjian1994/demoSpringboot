package com.example.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author by hj on 2018-2-8.
 */
@Aspect
@Component
public class HttpAspect {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpAspect.class);

    @Pointcut("execution(public * com.example.demo.controller.UserController.*(..))")
    public void log(){
        System.out.println(1111111);
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint){

        ServletRequestAttributes attributes
                =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //url
        LOGGER.info("url={}",request.getRequestURL());
        //method
        LOGGER.info("method={}",request.getMethod());
        //ip
        LOGGER.info("ip={}",request.getRemoteAddr());
        //类方法
        LOGGER.info("class_name={}",joinPoint.getSignature().getDeclaringTypeName()
                +"." +joinPoint.getSignature().getName());
        //请求参数
        LOGGER.info("args={}",joinPoint.getArgs());

    }

    @After("log()")
    public void doAfter(){

        System.out.println("2222222222222222222");
    }

    @AfterReturning(returning = "object",pointcut = "log()")
    public void afterReturning(Object object){
//        LOGGER.info("response={}",object.toString());
    }
}
