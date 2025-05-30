package com.authsystem.model;

public class User {
    private int id;
    private String username;
    private String email;
    private String password;
    
    // Constructors, getters, and setters
    public User() {}
    
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
    
    public void setId(int id){
        this.id=id;
    }
    
    public int getId(){
        return id;
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    
    public String getUsername(){
        return username;
    }
    
    public void setEmail(String email){
        this.email=email;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setPassword(String password){
        this.password=password;
    }
    
    public String getPassword(){
        return password;
    }
}