package com.example.springsecurityjwt.controller;


import com.example.springsecurityjwt.request.RequestUser;
import com.example.springsecurityjwt.request.UserReq;
import com.example.springsecurityjwt.response.ResponseUser;
import com.example.springsecurityjwt.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthenticationController {


private  final AuthenticationService authenticationService;



   @PostMapping ("/test")
   public  String test(){
       return "test";
   }

    @PostMapping("/save")
    public ResponseEntity<ResponseUser> save(@RequestBody RequestUser userDto){


         return ResponseEntity.ok(authenticationService.save(userDto));


    }


    @PostMapping("/auth")
    public ResponseEntity<ResponseUser> auth(@RequestBody UserReq userReq){


        return ResponseEntity.ok(authenticationService.auth(userReq));


    }


}
