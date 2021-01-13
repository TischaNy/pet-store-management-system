package com.petstore.store.controllers;

import com.petstore.store.model.AuthRequest;
import com.petstore.store.service.AuthUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authentication")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    private AuthUserDetailsService userDetailsService;


    public AuthController(){

    }

    @RequestMapping(value = "/sign-in", method = RequestMethod.POST)
    @ResponseBody
    public String signIn(@RequestBody AuthRequest authRequest) throws Exception {
        try{
            // Checks if credentials are valid
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        }
        catch(Exception e){
            throw new Exception("invalid username or password");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());


        return "authenticated";

    }
}
