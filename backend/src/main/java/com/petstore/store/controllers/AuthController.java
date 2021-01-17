package com.petstore.store.controllers;

import com.petstore.store.dao.AuthTokenDao;
import com.petstore.store.dao.CartDao;
import com.petstore.store.dao.UserDao;
import com.petstore.store.model.AuthRequest;
import com.petstore.store.model.AuthToken;
import com.petstore.store.model.Cart;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import restfullResponse.ApiController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/authentication")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private UserDao userDao;
    private CartDao cartDao;
    private AuthTokenDao authTokenDao;
    private BCryptPasswordEncoder encoder;
    private ApiController apiController;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserDao userDao, CartDao cartDao, AuthTokenDao authTokenDao, BCryptPasswordEncoder encoder){
        this.authenticationManager = authenticationManager;
        this.userDao = userDao;
        this.cartDao = cartDao;
        this.authTokenDao = authTokenDao;
        this.encoder = encoder;
    }


    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ApiController> signUp(@RequestBody User user) throws Exception {
        List<String> errorMessages = validateString(user);
        if(errorMessages.size() != 0){
            System.out.println("Errorrrrrrrrr");
            apiController = new ApiController( errorMessages, HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(apiController, apiController.getStatusCode());
        }
        if(userDao.findByUserName(user.getUserName()) == null){
            AuthToken authToken = new AuthToken(user);
            Cart cart = new Cart();

            user.setPassword(encoder.encode(user.getPassword()));
            user.setCart(cart);

            cartDao.save(cart);
            userDao.save(user);
            authTokenDao.save(authToken);

            apiController = new ApiController(authTokenDao.findAuthTokenByUserUserName(user.getUserName()), "New user created", HttpStatus.OK);
        }else{
            apiController = new ApiController("Username already taken", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(apiController, apiController.getStatusCode());
    }

    public List<String> validateString(User user){
        int MIN_LENGTH = 2;
        List<String> errorMessages = new ArrayList<>();
        if(user.getUserName().length() < MIN_LENGTH){
            errorMessages.add("Username must be min 2 characters long.");
        }
        if(user.getFirstName().length() < MIN_LENGTH){
            errorMessages.add("Firstname must be min 2 characters long.");
        }
        if(user.getLastName().length() < MIN_LENGTH){
            errorMessages.add("Lastname must be min 2 characters long.");
        }
        if(user.getPassword().length() < MIN_LENGTH + 6){
            errorMessages.add("Password must be min 8 characters long.");
        }

        if(!user.getUserName().chars().allMatch(Character::isLetter)){
            errorMessages.add("Username must only contain letters");
        }
        if(!user.getFirstName().chars().allMatch(Character::isLetter)){
            errorMessages.add("Firstname must only contain letters.");
        }
        if(!user.getLastName().chars().allMatch(Character::isLetter)){
            errorMessages.add("Lastname must only contain letters.");
        }
        return errorMessages;
    }


    @RequestMapping(value = "/token", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ApiController> refreshToken(@RequestBody AuthToken token) throws Exception {
        System.out.println("Expiry date token before refresh: " + token.getExpiryDate().getTime());
        if(token.getExpiryDate().before(new Date())){ // If token is expired, create new token
            token.refreshToken(token.getUser().getUserName());
            System.out.println("Expiry date token after refresh: " + token.getExpiryDate().getTime());
            authTokenDao.save(token);
            apiController = new ApiController(token, "New token generated", HttpStatus.OK);
        }else{
            apiController = new ApiController(token, "Could not generate token", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(apiController, apiController.getStatusCode());
    }


    @RequestMapping(value = "/sign-in", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ApiController> signIn(@RequestBody AuthRequest authRequest) throws Exception {
        try{
            // Checks if credentials are valid
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            if(auth.isAuthenticated()){
                SecurityContextHolder.getContext().setAuthentication(auth);
                apiController = new ApiController(authTokenDao.findAuthTokenByUserUserName(authRequest.getUsername()), "succesfull request", HttpStatus.OK);
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
