package com.ecom.Authorisation_Authentication.User.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;


}
