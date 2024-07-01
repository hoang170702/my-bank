package com.bank.mybank.service.Impl;

import com.bank.mybank.config.jwt.JwtTokenProvider;
import com.bank.mybank.service.IAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService implements IAuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public String getJwtToken(String username, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            username,
                            password
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtTokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
            return jwt;
        } catch (Exception e) {
            log.error("Auth service:" + e.getMessage());
            return null;
        }
    }
}
