package com.petstore.store.controllers;

import com.petstore.store.dao.UserDao;
import com.petstore.store.model.AuthRequest;
import com.petstore.store.model.User;
import com.petstore.store.service.AuthUserDetailsService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import restfullResponse.ApiController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/authentication")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDao userDao;
    private ApiController apiController;

    public AuthController(){

    }

    @RequestMapping(value = "/sign-in", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ApiController> signIn(@RequestBody AuthRequest authRequest) throws Exception {
        try{
            // Checks if credentials are valid
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            if(auth.isAuthenticated()){
                SecurityContextHolder.getContext().setAuthentication(auth);
                apiController = new ApiController(userDao.findByUserName(authRequest.getUsername()), "succesfull request", HttpStatus.OK);
            }
        }
        catch(Exception e){
            apiController = new ApiController("Invalid username or password", HttpStatus.BAD_REQUEST);
        }


        return new ResponseEntity<>(apiController, apiController.getStatusCode());
    }


    @RequestMapping(value="/sign-out", method=RequestMethod.GET)
    public ResponseEntity<ApiController> signOut(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        apiController = new ApiController<>("succesfull request", HttpStatus.OK);
        return new ResponseEntity<>(apiController, apiController.getStatusCode());
    }
}
