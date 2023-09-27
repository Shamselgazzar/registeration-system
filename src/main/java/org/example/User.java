package org.example;

import lombok.Getter;

@Getter
public class User {
    private final String name;
    private final String email;
    private final String password;

    public User() {
        this.name = "name";
        this.email = "email";
        this.password = "password";
    }
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public long sum (long x, long y){
        return x+y;
    }

}
