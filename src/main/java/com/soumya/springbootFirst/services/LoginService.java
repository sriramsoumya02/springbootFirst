package com.soumya.springbootFirst.services;

import org.springframework.stereotype.Component;

@Component
public class LoginService {

    public boolean isValidUser(String username, String password) {
        return username.equalsIgnoreCase("soumya") && password.equalsIgnoreCase("sriram");
    }
}
