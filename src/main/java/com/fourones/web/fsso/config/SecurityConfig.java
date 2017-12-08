package com.fourones.web.fsso.config;

import com.fourones.web.fsso.service.authentication.CustomAuthenticationFailureHandler;
import com.fourones.web.fsso.service.authentication.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .requestMatchers()
                .antMatchers("/login", "/login-error", "/signIn",
                        "/oauth/authorize", "/admin/**",
                        "/vendors/**", "/build/**")
                    .and()
                .authorizeRequests()
                    .antMatchers("/vendors/**", "/build/**").permitAll()
                    .antMatchers("/login", "/login-error", "/signIn").permitAll()
                    .antMatchers("/admin/**").hasAuthority("AUTH_ADMIN")
                    .anyRequest().authenticated()
                    .and()
                .authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .failureForwardUrl("/login-error")
                    .usernameParameter("id")
                    .permitAll()
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
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    public CustomUserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }
}
