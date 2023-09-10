package com.example.demo.controller;

import com.example.demo.DTO.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class Controller {

    @Autowired
    AuthenticationManager manager;

    @GetMapping(value = "/allowed")
    public String method1(){
        return "allowed method.";
    }

    @GetMapping(value = "/authMethod")
    public String method2(){
        return "need auth method";
    }

    @PostMapping(value = "/authManager")
    public ResponseEntity<?> method3(@RequestBody LoginDTO dto){
        var token = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());
        var authentication = manager.authenticate(token);

        return ResponseEntity.ok().body(authentication);
    }
}
