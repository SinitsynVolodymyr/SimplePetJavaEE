package com.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void getPass() {
        User user1 = new User("login1");
        User user2 = new User("login2", "pass1");

        assertNull(user1.getPass());
        assertNotNull(user2.getPass());
        assertEquals(user2.getPass(),"pass1");
    }

    @Test
    void getLogin() {

        User user1 = new User("login");
        User user2 = new User("LOgin");
        User user3 = new User("LOGIN");

        assertEquals(user1.getLogin(),"login");
        assertEquals(user2.getLogin(),"LOgin");
        assertEquals(user3.getLogin(),"LOGIN");

    }

    @Test
    void getLowerLogin() {

        User user1 = new User("login");
        User user2 = new User("LOgin");
        User user3 = new User("LOGIN");

        assertEquals(user1.getLowerLogin(),"login");
        assertEquals(user2.getLowerLogin(),"login");
        assertEquals(user3.getLowerLogin(),"login");

    }

    @Test
    void getMoney() {

        User user1 = new User("login");

        assertEquals(user1.getMoney(),0.0f);

    }

    @Test
    void setMoney() {

        User user1 = new User("login");
        assertEquals(user1.getMoney(),0.0f);

        user1.setMoney(100);
        assertEquals(user1.getMoney(),100.0f);

        user1.setMoney(200);
        assertEquals(user1.getMoney(),200.0f);

    }

    @Test
    void addMoney() {

        User user1 = new User("login");
        assertEquals(user1.getMoney(),0.0f);

        user1.addMoney(100);
        assertEquals(user1.getMoney(),100.0f);

        user1.addMoney(200);
        assertEquals(user1.getMoney(),300.0f);

    }

    @Test
    void takeMoney() {

        User user1 = new User("login");
        assertEquals(user1.getMoney(),0.0f);
        user1.setMoney(300);
        user1.takeMoney(100);

        assertEquals(user1.getMoney(),200.0f);

    }

    @Test
    void testEquals() {
        User user1 = new User("login");
        User user2 = new User("login","pass");
        user1.setMoney(1000);

        assertTrue(user1.equals(user2));
        assertTrue(user2.equals(user1));
        assertEquals(user1,user2);

    }

    @Test
    void testHashCode() {
        User user1 = new User("login");
        User user2 = new User("login","pass");
        user1.setMoney(1000);

        assertEquals(user1.hashCode(),user2.hashCode());
    }

    @Test
    void testToString() {
        User user = new User("login","pass");
        user.setMoney(1100);
        User user2 = new User("login","445");
        user2.setMoney(1100);

        assertNotNull(user.toString());
        assertEquals(user2.toString(),user.toString());
    }


}