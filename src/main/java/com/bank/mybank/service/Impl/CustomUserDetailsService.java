package com.bank.mybank.service.Impl;


import com.bank.mybank.entities.User;
import com.bank.mybank.repository.IUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private IUserRepository userRepository;

    public  CustomUserDetailsService(IUserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()){
            log.error("custom user details service - error not found username: "+username);
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user.get());
    }

    public UserDetails loadUserByUserId(int userId) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()){
            log.error("custom user details service - error not found user Id: "+userId);
            throw new UsernameNotFoundException(""+userId);
        }
        return new CustomUserDetails(user.get());
    }
}