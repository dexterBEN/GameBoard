package com.dexlab.gameboard.controller;

import java.util.HashMap;
import java.util.Map;

import com.dexlab.gameboard.helpers.Helpers;
import com.dexlab.gameboard.model.dto.AuthentUser;
import com.dexlab.gameboard.service.AuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AuthenticationController {
    
    @Autowired
    AuthenticationService authenticationService;


    @PostMapping(path="/gameboard/authenticate")
    @ResponseBody
    public AuthentUser  authenticate(
        @RequestParam("email") String email,
        @RequestParam("password") String password
    ){

        String hashedStr = Helpers.encodeStringToSha1(password);
    
        return authenticationService.login(email, hashedStr);
    }

    @PostMapping(path="/gameboard/register")
    @ResponseBody
    public String  createAccount(
        @RequestParam("name") String name,
        @RequestParam("email") String email,
        @RequestParam("password") String password
    ){


        Map<String, Object> userDoc = new HashMap<>();

        userDoc.put("mail", email);
        userDoc.put("name", name);
        userDoc.put("password", Helpers.encodeStringToSha1(password));
        
        authenticationService.createAccount(userDoc);
        return null;
    }



}
