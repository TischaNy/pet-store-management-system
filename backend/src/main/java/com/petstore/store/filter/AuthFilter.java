package com.petstore.store.filter;

import com.petstore.store.dao.AuthTokenDao;
import com.petstore.store.dao.UserDao;
import com.petstore.store.model.AuthToken;
import com.petstore.store.service.AuthUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Component
public class AuthFilter extends OncePerRequestFilter {
    @Autowired
    private AuthTokenDao authTokenDao;
    @Autowired
    private AuthUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = httpServletRequest.getHeader("Authorization");
        if(authHeader != null){
            String tokenString = authHeader.substring("Bearer ".length());
            AuthToken token = authTokenDao.findByToken(tokenString);
            if(token != null){
                if(token.getExpiryDate().before(new Date())){ // If token is expired, create new token
                    token.refreshToken(token.getUser().getUserName());
                }

                UserDetails userDetails = userDetailsService.loadUserByUsername(token.getUser().getUserName());

                UsernamePasswordAuthenticationToken authToken
                        = new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(httpServletRequest)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
        return;
    }

    // retrieve username from token
    public String getUsernameFromToken(String token){
        return getClaimsFromToken(token).getSubject().toString();
    }

    // retrieve information from token
    public Claims getClaimsFromToken(String token){
        Claims claims = Jwts.parser().setSigningKey(AuthToken.TOKEN_KEY.getBytes()).parseClaimsJws(token).getBody();
        return claims;
    }

}
