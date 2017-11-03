package com.fourones.web.fsso.web;

import com.fourones.web.fsso.entity.User;
import com.fourones.web.fsso.service.authentication.CustomUserDetailsService;
import com.fourones.web.fsso.service.authentication.type.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private PasswordEncoder pwEncoder;

    @Autowired
    private CustomUserDetailsService service;

    @PostMapping(value = "/signIn")
    public void signIn(String id, String password, String userName) {
        String encodedPwd = pwEncoder.encode(password);
        User user = new User(id, userName, encodedPwd, UserRole.ADMIN);
        service.createUser(user);
    }

}
