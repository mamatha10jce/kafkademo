package com.mamatha.kafkademo.config;

import com.mamatha.kafkademo.security.AuthenticateEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@PropertySource("classpath:application.properties")
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AuthenticateEntryPoint authenticateEntryPoint;

    @Autowired
    Environment environment;

    private String readProperties(String param) {
        return environment.getProperty(param);
    }

    private PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/api/**").access("hasRole('ROLE_USER') or  hasRole('ROLE_ADMIN')")
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .anyRequest().authenticated()
                .and().httpBasic()
                .authenticationEntryPoint(authenticateEntryPoint);
        http.headers().cacheControl().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(readProperties("username"))
                .password(passwordEncoder.encode(readProperties("userpassword")))
                .roles("USER")
                .and()
                .withUser(readProperties("useradmin"))
                .password(passwordEncoder.encode(readProperties("adminpassword")))
                .roles("ADMIN");

    }
}
