package com.bank.mybank.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class TestController {
    @GetMapping("test")
    public String test() {
        return "Hello from MyBank";
    }

    @GetMapping("need-admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String needAdmin() {
        return "You need to be an admin to access this endpoint";
    }

    @GetMapping("need-user")
    @PreAuthorize("hasAuthority('USER')")
    public String needUser() {
        return "You need to be a user to access this endpoint";
    }
}
