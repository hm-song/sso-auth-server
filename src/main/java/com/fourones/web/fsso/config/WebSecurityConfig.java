package com.fourones.web.fsso.config;

import com.fourones.web.fsso.service.authentication.CustomAuthenticationFailureHandler;
import com.fourones.web.fsso.service.authentication.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //
        http
            // Matchers are considered in order.
            .authorizeRequests()
                // Any user can access to the resources starts with '/home', '/webjars', '/css', '/userInfo'
                .antMatchers("/home", "/static/**", "/templates/**", "/build/**", "/vendors/**",
                        "/userInfo", "/signIn").permitAll()

                // Resources starts with '/manage' only can access who has 'ROLE_ADMIN' role.
                // You can omit 'ROLE_' prefix
                // Can specify multiple roles using '.hasAnyRole()'
                .antMatchers("/manage/**").hasRole("ADMIN")

                // Every request must be authenticated
                .anyRequest().authenticated()
                .and()

            // Support form based authentication
            .formLogin()
                .usernameParameter("id")
                .passwordParameter("password")

                // URL which handles login request(username and password will be sent)
                .loginProcessingUrl("/login")

                // URL which shows login form
                .loginPage("/loginForm")

                .defaultSuccessUrl("/index")

                .failureHandler(failureHandler())

                // Allows anyone can access
                .permitAll()
                .and()
            .csrf()
                .requireCsrfProtectionMatcher(new AntPathRequestMatcher("/user*"))
                .disable()
            .logout()
                .logoutUrl("/logout")
                .permitAll();
    }

    //    @Bean
    //    public AuthenticationSuccessHandler successHandler() {
    //        SimpleUrlAuthenticationSuccessHandler handler = new SimpleUrlAuthenticationSuccessHandler();
    //    }

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public CustomAuthenticationFailureHandler failureHandler() {
        return new CustomAuthenticationFailureHandler();

    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
}
