package com.example.springsecurityjwt.config;

import com.example.springsecurityjwt.repository.UserRepostory;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
@AllArgsConstructor


public class ApplicationConfig {

     private  final UserRepostory userRepostory;

     @Bean
     public UserDetailsService userDetailsService(){

         return (username)->{return userRepostory.findByUsername(username)
                 .orElseThrow(()->new UsernameNotFoundException("User not found"));};
     }

     @Bean
     public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
     }


     @Bean
    public AuthenticationProvider authenticationProvider(){

       // dogrulamani yoxluyur
         DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
         authenticationProvider.setPasswordEncoder(passwordEncoder());
         authenticationProvider.setUserDetailsService(userDetailsService());

         return authenticationProvider;
     }

      @Bean
      public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{


         return authenticationConfiguration.getAuthenticationManager();
      }

}
