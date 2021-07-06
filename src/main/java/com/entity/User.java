package com.entity;

import java.util.Objects;

public class User {
    private String login;
    private float money=0f;

    public User(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
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
