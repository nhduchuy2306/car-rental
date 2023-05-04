package com.carrental.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @GetMapping("/hello")
    public String sayHello(){
        return "Hello World! Deploy successful!";
    }
    
    @GetMapping("/hello-2")
    public String sayHello2(){
        return "Hello World! Deploy successful! 2";
    }
    
    @GetMapping("/hello-3")
    public String sayHello3(){
        return "Hello World! Deploy successful! 3";
    }
}
