package com.example.springsecurityjwt.service;


import com.example.springsecurityjwt.entity.User;
import com.example.springsecurityjwt.enums.Role;
import com.example.springsecurityjwt.repository.UserRepostory;
import com.example.springsecurityjwt.request.RequestUser;
import com.example.springsecurityjwt.request.UserReq;
import com.example.springsecurityjwt.response.ResponseUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {


  
   private final UserRepostory userRepostory;

   private final AuthenticationManager authenticationManager;
   
   private final  JwtService jwtService;

   private final PasswordEncoder passwordEncoder;
    public ResponseUser save(RequestUser userDto) {
        
        User user =User.builder().username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .nameSurname(userDto.getNameSurname())
                .role(Role.USER)
                .build();
        userRepostory.save(user);

       var token = jwtService.generateToken(user);
       
        return  ResponseUser.builder().token(token).build();
    }

    public ResponseUser auth(UserReq userReq) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userReq.getUsername(),userReq.getPassword()));
        User user=userRepostory.findByUsername(userReq.getUsername()).orElseThrow(()->new UsernameNotFoundException("user not found"));
        String token=jwtService.generateToken(user);
        return ResponseUser.builder().token(token).build();

    }
}
