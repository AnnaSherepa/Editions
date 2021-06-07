package models.dao.interfaces;

import models.entity.Edition;
import models.entity.User;

import java.util.List;

public interface UsersEditionsDAO {
    /**
     * Method return list of editions for user.
     * User described by id
     *
     * @param id - user id
     * @return List/<Edition/>
     * */
    List<Edition> findAllUsersEditions(int id);
    /**
     * Method return list of users for edition.
     * Edition described by id
     *
     * @param id - edition id
     * @return List/<User/>
     * */
    List<User> findAllEditionsUsers(int id);

    /**
     * Method set pairs of values,
     * formed by id author and id of each edition in editions list
     *
     * Return true if operation finished successful
     *
     * @param user - author object which list of editions will be stored
     * @return boolean
     * */
    void setEditionsToUser(User user, List<Edition> editions);
}
