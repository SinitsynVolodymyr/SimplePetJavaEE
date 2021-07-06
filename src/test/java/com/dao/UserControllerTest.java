package com.dao;

import com.entity.User;
import com.exceptions.controller.UserIsExistException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserControllerTest {

    @Test
    void getAll() {

        try {
            UserController userC = new UserController();
            List<User> all = userC.getAll();
            assertTrue(all.size()>0);
        } catch (ClassNotFoundException e) {
            fail(e);
        } catch (SQLException e2) {
            fail(e2);
        }

    }

    @Order(1)
    @Test
    void create() throws ClassNotFoundException, SQLException {
        UserController uc = new UserController();
        User user = new User("Yura", "aba452");
        try {
            assertTrue(uc.create(user));
        } catch (UserIsExistException | SQLException e) {
            fail(e);
        }

    }

    @Order(2)
    @Test
    void getEntityByKey() throws ClassNotFoundException, SQLException {
        UserController uc = new UserController();
        try {
            User yura = uc.getEntityByKey("Yura");
            assertNotNull(yura);
        }catch (SQLException e) {
                fail(e);
            }
    }

    @Order(3)
    @Test
    void updateMoney() throws SQLException, ClassNotFoundException {
        UserController uc = new UserController();
        User yura = new User("Yura");
        yura.setMoney(1200);
        try {
            assertTrue(uc.updateMoney(yura));
        }catch (SQLException e) {
            fail(e);
        }
        User yura1 = uc.getEntityByKey("Yura");
        assertEquals(yura.getMoney(),yura1.getMoney());

    }

    @Order(4)
    @Test
    void update() throws SQLException, ClassNotFoundException, InterruptedException {
        User yura = new User("YURA");
        yura.setMoney(8000);

        UserController uc = new UserController();
        try {
            assertTrue(uc.update(yura));
        }catch (SQLException e) {
            fail(e);
        }

        assertEquals(uc.getEntityByKey("Yura").getMoney(),8000);
        assertEquals(uc.getEntityByKey("Yura").getLogin(),"YURA");
        User userCheck1 = new User("YURA", "aba452");
        assertTrue(uc.isAuth(userCheck1));


        User yura2 = new User("YUra","kki546aARa");
        try {
            assertTrue(uc.update(yura2));
        }catch (SQLException e) {
            fail(e);
        }

        assertEquals(uc.getEntityByKey("Yura").getMoney(),0);
        assertEquals(uc.getEntityByKey("Yura").getLogin(),"YUra");
        User userCheck2 = new User("YURA", "kki546aARa");
        assertTrue(uc.isAuth(userCheck2));



    }

    @Order(5)
    @Test
    void remove() throws SQLException, ClassNotFoundException {

        UserController uc = new UserController();

        assertTrue(uc.remove("yura"));

        assertNull(uc.getEntityByKey("yura"));


    }



}