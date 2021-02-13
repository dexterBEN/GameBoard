package com.dexlab.gameboard.model.dto;

import java.util.Objects;

import com.dexlab.gameboard.model.User;

public class AuthentUser {

    private User user;
    private String token; //use jwt token 


    public AuthentUser() {
    }

    public AuthentUser(User user, String token) {
        this.user = user;
        this.token = token;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public AuthentUser user(User user) {
        setUser(user);
        return this;
    }

    public AuthentUser token(String token) {
        setToken(token);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof AuthentUser)) {
            return false;
        }
        AuthentUser authentUser = (AuthentUser) o;
        return Objects.equals(user, authentUser.user) && Objects.equals(token, authentUser.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, token);
    }

    @Override
    public String toString() {
        return "{" +
            " user='" + getUser() + "'" +
            ", token='" + getToken() + "'" +
            "}";
    }

}
