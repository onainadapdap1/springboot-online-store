package com.spring.ecommerceyt.model;

import lombok.Data;

@Data
public class SignupRequest {
    private String username;
    private String password;
    private String email;
    private String nama;
}
