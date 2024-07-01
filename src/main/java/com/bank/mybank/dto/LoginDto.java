package com.bank.mybank.dto;

import lombok.*;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginDto implements Serializable {
    public String username;
    public String password;
}
