package com.fourones.web.fsso.web;

import com.fourones.web.fsso.entity.User;
import com.fourones.web.fsso.service.authentication.CustomUserDetailsService;
import com.fourones.web.fsso.service.authentication.type.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Arrays;

@RestController
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private CustomUserDetailsService service;

    @PostMapping(value = "/signIn")
    public void signIn(String id, String password, String userName) {
        User UserController = new User(id, userName, password, Arrays.asList(new SimpleGrantedAuthority("USER")));
        service.createUser(UserController);
    }

    @GetMapping(value = "/me")
    public Principal getUser(Principal principal) {
        logger.info("[TEST] {}", principal);
        return principal;
    }
}
