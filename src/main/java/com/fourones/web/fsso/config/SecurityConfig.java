package com.fourones.web.fsso.config;

import com.fourones.web.fsso.service.authentication.CustomAuthenticationFailureHandler;
import com.fourones.web.fsso.service.authentication.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatchers()
                .antMatchers("/**")
//                .antMatchers("/login", "/loginForm", "/oauth/authorize", "/vendors/**", "/build/**")
                    .and()
                .authorizeRequests()
                    .antMatchers("/login", "/loginForm").permitAll()
                    .antMatchers("/vendors/**", "/build/**").permitAll()
                    .anyRequest()
                        .authenticated()
                    .and()
                .formLogin()
                    .loginProcessingUrl("/login")
                    .loginPage("/loginForm")
                    .failureHandler(new CustomAuthenticationFailureHandler())
                    .permitAll()
                    .usernameParameter("id")
                    .and()
                .csrf().disable()
                .httpBasic().disable();
    }


    @Bean
    public CustomAuthenticationFailureHandler failureHandler() {
        return new CustomAuthenticationFailureHandler();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception { // @formatter:off
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public CustomUserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }
}
