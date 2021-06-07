package models.dao.mysqlfactotries;

import models.dao.factory.Connector;
import models.entity.User;
import models.enums.Role;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UserDAOFactoryTest {
    private static final  String USER = "root";
    private static final String PASS  = "";
    private static final String DB_CONNECTION = "";
    private static final String URL_PATH = "jdbc:mysql://localhost:3307/test_final_project?useSSL=false&useUnicode=true&serverTimezone=UTC";

    @Test
    public void find() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void findByLogin_returnOneUser() {
        User user = UserDAOFactory.getInstance().findByLogin("userB");
        System.out.println(user);
        assertTrue(true);
    }

    @Test
    public void findAll_returnListOfUser() throws SQLException {
        List<User> users =  UserDAOFactory.getInstance().findAll();
        for(User user: users){
            System.out.println(user);
        }

        assertTrue(true);

    }

    @Test
    public void insert() {
        User user = new User.Builder().setName("Василь")
                .setSurname("Петров")
                .setPass("456")
                .setLogin("newUserLogin")
                .setRole(Role.USER.getRole())
                .setEmail("email@gmail.com").build();
        assertTrue(UserDAOFactory.getInstance().insert(user));
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void deleteByLogin() {
    }
}