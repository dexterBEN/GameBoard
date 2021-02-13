package com.dexlab.gameboard.model;

import java.util.Objects;

public class User {
    
    private String email;
    private String passWord;
    private String name;

    public User() {
    }

    public User(String email, String passWord, String name) {
        this.email = email;
        this.passWord = passWord;
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassWord() {
        return this.passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User email(String email) {
        setEmail(email);
        return this;
    }

    public User passWord(String passWord) {
        setPassWord(passWord);
        return this;
    }

    public User name(String name) {
        setName(name);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(email, user.email) && Objects.equals(passWord, user.passWord) && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, passWord, name);
    }

    @Override
    public String toString() {
        return "{" +
            " email='" + getEmail() + "'" +
            ", passWord='" + getPassWord() + "'" +
            ", name='" + getName() + "'" +
            "}";
    }
    
}
