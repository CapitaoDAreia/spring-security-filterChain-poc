package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class Controller {

    @GetMapping(value = "/allowed")
    public String method1(){
        return "allowed method.";
    }

    @GetMapping(value = "/authMethod")
    public String method2(){
        return "need auth method";
    }
}
