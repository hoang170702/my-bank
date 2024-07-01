package com.bank.mybank.controller;

import com.bank.mybank.dto.LoginDto;
import com.bank.mybank.service.IAuthService;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IAuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> authenticationUser(@RequestBody LoginDto loginDto){
        try {
            String jwt = authService.getJwtToken(loginDto.getUsername(), loginDto.getPassword());
            return ResponseEntity.status(HttpStatus.OK).body("Bearer Token: "+ jwt);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
