package com.example.blog2.config;

import com.example.blog2.model.User;
import com.example.blog2.UserDetailsServiceImpl;
import com.example.blog2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl userDetailsService;
    private UserRepository userRepository;
@Autowired
    public SecurityConfig(UserDetailsServiceImpl userDetailsService, UserRepository userRepository) {
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
   // auth.inMemoryAuthentication().withUser("tomek").password("{noop}tom123").roles("USER");
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());

    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
//    @EventListener(ApplicationReadyEvent.class)
//    public void get(){
//        User user1 = new User("tomek",passwordEncoder().encode("tom123"), "USER");
//        User user2 = new User("elvis_presley",passwordEncoder().encode("tom123"), "USER");
//    userRepository.save(user1);
//    userRepository.save(user2);
//
//
//    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http


        .csrf().disable().

                authorizeRequests()
                .antMatchers( "/posts").permitAll()
                .antMatchers( "/post/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**")

                .permitAll().anyRequest().authenticated().and().httpBasic();







    }
}
