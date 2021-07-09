package com.entity;

import java.util.Objects;

public class User {
    private String login;
    private String pass;
    private float money=0f;

    public User(String login) {
        this.login = login;
    }

    public User(String login,String pass) {
        this.login = login;
        this.pass = pass;
    }

    public String getPass() {
        return pass;
    }

    public String getLogin() {
        return login;
    }

    public String getLowerLogin(){
        return login.toLowerCase();
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public void addMoney(float money) {
        this.money += money;
    }

    public void takeMoney(float money) {
        this.money -= money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", money=" + money +
                '}';
    }
}
