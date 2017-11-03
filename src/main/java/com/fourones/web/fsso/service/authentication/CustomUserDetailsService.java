package com.fourones.web.fsso.service.authentication;

import com.fourones.web.fsso.entity.User;
import com.fourones.web.fsso.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findOne(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found named = " + username);
        }
        return user;
    }

    @Transactional
    public void createUser(User user) {
        repo.saveAndFlush(user);
    }
}