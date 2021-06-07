package models.dao.interfaces;

import models.entity.Edition;
import models.entity.User;

import java.math.BigDecimal;
import java.util.List;

public interface UserDAO {
    /**
     * This method get from db record of users by given id
     * and return new instance of User object
     * @param id - value of record's id
     * @return User
     *
     * */
    User findById(int id);

    /**
     * This method get from db record of users by given login
     * and return new instance of User object
     *
     *
     * @param login - unique login of user
     * @return User
     * */
    User findByLogin(String login);

    /**
     * Method return all records from db table users
     * as list of User objects
     * @return List/<Genre/>
     * */
    List<User> findAll();

    /**
     * Method add new record to db users table,
     * based on given User object as a parameter
     *
     * If the insertion is successful,
     * the method returns true,
     * otherwise false
     *
     * @param user - object of User type
     * @return boolean
     * */
    boolean insert(User user);

    /**
     * Method delete record from db table users,
     * based on given id as a parameter
     *
     * If the removal is successful,
     * the method returns true,
     * otherwise false
     *
     * @param id - id of record which have to be deleted
     * @return boolean
     * */
    boolean deleteById(int id);

    /**
     * Method delete record from db table users,
     * based on given login as a parameter
     *
     * If the removal is successful,
     * the method returns true,
     * otherwise false
     *
     * @param login - id of record which have to be deleted
     * @return boolean
     * */
    boolean deleteByLogin(String login);


    boolean deleteUserEditionById(int idUser, int idEdition);
    boolean setEditionsToUser(int id, List<Edition> editions);

    List<User> findAllUsers();

    boolean updateBalance(int id, BigDecimal balance);
    boolean updateStatus(int id, int newStatus);
    List<User> findAllUsersWithoutEditions(int size, int from);
    int tableSize();
}
