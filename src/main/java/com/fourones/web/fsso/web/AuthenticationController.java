package com.fourones.web.fsso.web;

import com.fourones.web.fsso.entity.User;
import com.fourones.web.fsso.service.authentication.CustomUserDetailsService;
import com.fourones.web.fsso.service.authentication.type.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Arrays;

@Controller
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private CustomUserDetailsService service;

    // Login form with error
    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @PostMapping(value = "/signIn")
    @ResponseBody
    public void signIn(String id, String password, String userName) {
        new BCryptPasswordEncoder().encode(password);
        User UserController = new User(id, userName, password, Arrays.asList(new SimpleGrantedAuthority("USER")));
        service.createUser(UserController);
    }

    @GetMapping(value = "/me", produces = "application/json")
    @ResponseBody
    public Principal getUser(Principal principal) {
        return principal;
    }
}
