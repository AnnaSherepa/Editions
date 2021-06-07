package models.dao.interfaces;

import models.entity.BlockedUser;

import java.util.List;

public interface BlockedUserDAO {
    /**
     * This method get from db record of blocked_users by given id
     * and return new instance of BlockedUser object
     * @param id - value of record's id
     * @return BlockedUser
     *
     * */
    BlockedUser findById(int id);

    /**
     * Method return all records from db table blocked_users
     * as list of User objects
     * @return List/<Genre/>
     * */
    List<BlockedUser> findAll();

    /**
     * Method add new record to db blocked users table,
     * based on given BlockedUser object as a parameter
     *
     * If the insertion is successful,
     * the method returns true,
     * otherwise false
     *
     * @param user - object of BlockedUser type
     * @return boolean
     * */

    boolean insert(BlockedUser user);

    /**
     * Method update record in db blocked users table,
     * based on given User object as a parameter
     *
     * If the insertion is successful,
     * the method returns true,
     * otherwise false
     *
     * @param user - object of BlockedUser type
     * @return boolean
     * */
    boolean updateBlockedUser(BlockedUser user);
    /**
     * Method delete record from db table blocked_users,
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
}
