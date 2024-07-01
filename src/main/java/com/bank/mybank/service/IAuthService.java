package com.bank.mybank.service;

public interface IAuthService {
    String getJwtToken(String username, String password);
}
