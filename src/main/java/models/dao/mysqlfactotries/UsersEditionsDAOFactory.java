package models.dao.mysqlfactotries;

import models.dao.factory.Connector;
import models.dao.interfaces.UsersEditionsDAO;
import models.entity.Edition;
import models.entity.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UsersEditionsDAOFactory implements UsersEditionsDAO{
    private static final Logger LOGGER = Logger.getLogger(UsersEditionsDAOFactory.class);
    private static final String SQL_SELECT_ID_USERS = "SELECT userId FROM users_editions";
    private static final String SQL_SELECT_ID_EDITIONS = "SELECT editionId FROM users_editions";
    private static final String SQL_SELECT_EDITIONS_OF_USER_BY_ID = "SELECT * FROM editions WHERE id in (" + SQL_SELECT_ID_EDITIONS
            + "WHERE userId = ?)";
    private static final String SQL_SELECT_USERS_OF_EDITION_BY_ID = "SELECT * FROM users WHERE id in ( " + SQL_SELECT_ID_USERS
            + "WHERE editionId = ?)";

    private static final String SQL_INSERT_USER_EDITIONS = "INSERT INTO users_editions(userId, editionId) VALUES (?, ?)";

    private UsersEditionsDAOFactory(){}
    private static UsersEditionsDAOFactory instance;

    public static synchronized UsersEditionsDAOFactory getInstance() {
        if(instance == null){
            instance = new UsersEditionsDAOFactory();
        }
        return instance;
    }

    @Override
    public List<Edition> findAllUsersEditions(int id) {
        return EditionDAOFactory.getInstance().find(SQL_SELECT_EDITIONS_OF_USER_BY_ID, id, null);
    }

    @Override
    public List<User> findAllEditionsUsers(int id) {
        return UserDAOFactory.getInstance().find(SQL_SELECT_USERS_OF_EDITION_BY_ID, id, null);
    }

    @Override
    public void setEditionsToUser(User user, List<Edition> editions) {
        /*
        * Duplicate with AuthorEditions. Have or create parent abstract class
        * */
        Connection con = null;
        PreparedStatement statement = null;

        try{
            con = Connector.getInstance().getConnection();
            con.setAutoCommit(false);
            statement = con.prepareStatement(SQL_INSERT_USER_EDITIONS);
            for(Edition edition: user.getEditions()){
                if(user.getId() < 1 || edition.getId() < 1 )
                    throw new SQLException();
                statement.setInt(1, user.getId());
                statement.setInt(2, edition.getId());
                statement.execute();
            }
        } catch (SQLException e) {
            if(con != null)
                Connector.getInstance().rollbackAndClose(con);
            LOGGER.error(e);
        }finally {
            if(con != null){
                Connector.getInstance().close(statement);
                Connector.getInstance().commitAndClose(con);
            }

        }
    }
}
