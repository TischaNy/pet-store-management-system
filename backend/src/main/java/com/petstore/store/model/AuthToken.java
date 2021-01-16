package com.petstore.store.model;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "tokens")
public class AuthToken {
    //public static final long TOKEN_VALIDITY = 60 * 60 * 1000; // duration 1 hour
    public static final long TOKEN_VALIDITY = 5 * 60 * 1000;
    public static final String TOKEN_KEY = "SECRET";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "token", length = 10000)
    private String token;

    @Column(name = "creationDate")
    private Date creationDate;

    @Column(name = "expiryDate")
    private Date expiryDate;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    public AuthToken(User user){
        long nowInMs = System.currentTimeMillis();
        this.creationDate = new Date(nowInMs);
        this.expiryDate = new Date(nowInMs + TOKEN_VALIDITY);
        this.user = user;
        this.token = this.generateToken(user.getUserName());
    }

    public AuthToken() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setToken(String token){
        this.token = token;
    }

    public String getToken(){
        return this.token;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String generateToken(String username){
        Map<String, Object> claims = new HashMap<>();
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(this.creationDate)
                .setExpiration(this.expiryDate)
                .signWith(SignatureAlgorithm.HS512,
                        TOKEN_KEY.getBytes())
                .compact();
    }

    public void refreshToken(String username){
        long nowInMs = System.currentTimeMillis();
        this.setCreationDate(new Date(nowInMs));
        this.setExpiryDate(new Date(nowInMs + TOKEN_VALIDITY));
        this.setToken(generateToken(username));
    }


}
