package models.dao.mysqlfactotries;

import models.dao.factory.Connector;
import models.dao.interfaces.UserDAO;
import models.entity.Edition;
import models.entity.User;
import models.enums.Role;
import org.apache.log4j.Logger;


import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

public class UserDAOFactory implements UserDAO {
    private static final Logger LOGGER = Logger.getLogger(UserDAOFactory.class);
    private static final String SQL_SELECT_ALL_USERS = "SELECT * from finalproject.users\n" +
            "LEFT JOIN users_editions ue on users.idUser = ue.userId\n" +
            "LEFT JOIN editions e on ue.editionId = e.idEdition\n" +
            "LEFT JOIN authors a on e.id_author = a.idAuthor\n" +
            "LEFT JOIN genres g on e.id_genre = g.idGenre ";

    private static final String SQL_LIMIT_USERS = "LIMIT ? OFFSET ?";
    private static final String SQL_SELECT_ALL_USERS_WITHOUT_EDITIONS = "SELECT * FROM users  WHERE role = 'user' " + SQL_LIMIT_USERS;
    private static final String SQL_COUNT_TABLE_SIZE = "SELECT COUNT(*) as size FROM users";

    private static final String SQL_INSERT_USER_EDITIONS = "INSERT INTO users_editions(userId, editionId) VALUES (?, ?)";

    private static final String SQL_DELETE_USER_EDITION_BY_ID = "DELETE FROM users_editions WHERE userId = ? AND editionId = ?";

    private static final String SQL_SELECT_ALL_USERS_BY_USER_ROLE = SQL_SELECT_ALL_USERS  + " WHERE role = 'user'";

    private static final String SQL_SELECT_USER_BY_ID = SQL_SELECT_ALL_USERS + "WHERE idUser = ?";
    private static final String SQL_SELECT_USER_BY_LOGIN = SQL_SELECT_ALL_USERS + "WHERE login = ?";
    private static final String SQL_INSERT_USER = "INSERT INTO finalproject.users(login, pass, name, surname, email, balance, role)  " +
            "VALUES(?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_DELETE_USER_BY_ID = "DELETE FROM users WHERE idUser = ?";
    private static final String SQL_DELETE_USER_BY_LOGIN = "DELETE FROM users WHERE login = ?";

    private static final String SQL_UPDATE_USER_STATUS = "UPDATE users SET status = ? WHERE idUser = ? ";
    private static final String SQL_UPDATE_USER_BALANCE = "UPDATE users SET balance = balance + ? WHERE idUser = ? ";

    private UserDAOFactory(){}
    private static UserDAOFactory instance;

    public static synchronized UserDAOFactory getInstance(){
        if(instance == null){
            instance = new UserDAOFactory();
        }
        return instance;
    }

    @Override
    public boolean updateStatus(int id, int newStatus){
        Connection con = null;
        PreparedStatement statement = null;
        try{
            con = Connector.getInstance().getConnection();
            statement = con.prepareStatement(SQL_UPDATE_USER_STATUS);
            statement.setInt(1, newStatus);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            if(con != null)
                Connector.getInstance().rollbackAndClose(con);
            LOGGER.error(e);

        }finally {
            try {
                if(con != null && !con.isClosed()){
                    Connector.getInstance().close(statement);
                    Connector.getInstance().commitAndClose(con);
                }
            } catch (SQLException e) {
                LOGGER.error(e);
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean updateBalance(int id, BigDecimal balance){
        Connection con = null;
        PreparedStatement statement = null;
        try{
            con = Connector.getInstance().getConnection();
            statement = con.prepareStatement(SQL_UPDATE_USER_BALANCE);
            statement.setBigDecimal(1, balance);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            if(con != null)
                Connector.getInstance().rollbackAndClose(con);
            LOGGER.error(e);

        }finally {
            try {
                if(con != null && !con.isClosed()){
                    Connector.getInstance().close(statement);
                    Connector.getInstance().commitAndClose(con);
                    return true;
                }
            } catch (SQLException e) {
                LOGGER.error(e);
                return false;
            }
        }
        return false;
    }

    static User extractFromResultSet(ResultSet rs) throws SQLException {

        return new User.Builder().setId(rs.getInt("idUser"))
                .setLogin(rs.getNString("login"))
                .setEmail(rs.getNString("email"))
                .setPass(rs.getNString("pass"))
                .setRole(rs.getNString("role"))
                .setName(rs.getNString("name"))
                .setSurname(rs.getNString("surname"))
                .setBalance(rs.getBigDecimal("balance"))
                .setStatus(rs.getInt("status"))
                .build();
    }

    private User makeUniqueUser(Map<Integer, User> uniqueUser, User user){
        uniqueUser.putIfAbsent(user.getId(), user);
        return uniqueUser.get(user.getId());
    }

    private Edition makeUniqueEdition(Map<Integer, Edition> uniqueEdition, Edition edition){
        uniqueEdition.putIfAbsent(edition.getId(), edition);
        return uniqueEdition.get(edition.getId());
    }

    public List<User> find(String sql, int id, String login){
        LOGGER.info("Start user search");
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement statement = null;
        Map<Integer, User> uniqueUsers = new HashMap<>();
        Map<Integer, Edition> uniqueEditions = new HashMap<>();

        List<User> users = new ArrayList<>();
        try{
          con = Connector.getInstance().getConnection();
          statement = con.prepareStatement(sql);
          if(id != -1)
              statement.setInt(1, id);
          else if(login != null)
              statement.setString(1, login);

          rs = statement.executeQuery();
          while (rs.next()){
              User user = extractFromResultSet(rs);
              user = makeUniqueUser(uniqueUsers, user);
              if(rs.getInt("idEdition") != 0) {
                  Edition edition = EditionDAOFactory.getInstance().extractFromResultSet(rs);
                  edition = makeUniqueEdition(uniqueEditions, edition);
                  user.getEditions().add(edition);
              }
              users.add(user);
          }
        } catch (SQLException e) {
            if(con != null)
                Connector.getInstance().rollbackAndClose(con);
            LOGGER.error(e);
        }finally {
            try {
                if(con != null && !con.isClosed()){
                    Connector.getInstance().close(rs);
                    Connector.getInstance().close(statement);
                    Connector.getInstance().commitAndClose(con);
                }
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
        LOGGER.info("End search");
        return users;
    }

    @Override
    public User findById(int id) {
        List<User> users = find(SQL_SELECT_USER_BY_ID, id, null);
        if(users.isEmpty())
            throw new NoSuchElementException();
        return users.get(0);
    }

    @Override
    public User findByLogin(String login) {
        List<User> users = find(SQL_SELECT_USER_BY_LOGIN, -1, login);
        if(users.isEmpty())
            /*return something else may be empty user */
            throw new NoSuchElementException();
        return users.get(0);

    }

    @Override
    public List<User> findAll() {
        return find(SQL_SELECT_ALL_USERS, -1, null);
    }

    @Override
    public List<User> findAllUsers() {
        return find(SQL_SELECT_ALL_USERS_BY_USER_ROLE, -1, null);
    }

    @Override
    public boolean insert(User user) {
        Connection con = null;
        PreparedStatement statement = null;
        try{
            con = Connector.getInstance().getConnection();

            statement = con.prepareStatement(SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            System.out.println(statement);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPass());
            statement.setString(3, user.getName());
            statement.setString(4, user.getSurname());
            statement.setString(5, user.getEmail());
            statement.setBigDecimal(6, BigDecimal.ZERO);
            statement.setString(7, Role.USER.getRole());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            if(con != null)
                Connector.getInstance().rollbackAndClose(con);
            LOGGER.error(e);
            return false;
        }finally {
            try {
                if(con != null && !con.isClosed()){
                    Connector.getInstance().close(statement);
                    Connector.getInstance().commitAndClose(con);
                }
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
        return true;
    }

    private boolean delete(String sql, int id, String login){
        Connection con = null;
        PreparedStatement statement = null;
        try{
            con = Connector.getInstance().getConnection();
            statement = con.prepareStatement(sql);
            if(id != -1)
                statement.setInt(1, id);
            if(login != null)
                statement.setNString(1, login);
            /* if any edition -> have to work ON DELETE CASCADE */
        } catch (SQLException e) {
            if(con != null)
                Connector.getInstance().rollbackAndClose(con);
            LOGGER.error(e);
            return false;
        }finally {
            try {
                if(con != null && !con.isClosed()){
                    Connector.getInstance().close(statement);
                    Connector.getInstance().commitAndClose(con);
                }
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
        return true;
    }
    @Override
    public boolean deleteById(int id) {
        return delete(SQL_DELETE_USER_BY_ID, id, null);
    }

    @Override
    public boolean deleteByLogin(String login) {
        return delete(SQL_DELETE_USER_BY_LOGIN, -1, login);
    }

    @Override
    public boolean deleteUserEditionById(int idUser, int idEdition){
        Connection con = null;
        PreparedStatement statement = null;
        try{
            con = Connector.getInstance().getConnection();
            statement = con.prepareStatement(SQL_DELETE_USER_EDITION_BY_ID);
            statement.setInt(1, idUser);
            statement.setInt(2, idEdition);
            statement.execute();
        } catch (SQLException e) {
            if(con != null)
                Connector.getInstance().rollbackAndClose(con);
            LOGGER.error(e);
            return false;
        }finally {
            if(con != null){
                Connector.getInstance().close(statement);
                Connector.getInstance().commitAndClose(con);
            }
        }
        return true;
    }

    @Override
    public boolean setEditionsToUser(int id, List<Edition> editions){
        Connection con = null;
        PreparedStatement statement = null;
        try{
            con = Connector.getInstance().getConnection();
            statement = con.prepareStatement(SQL_INSERT_USER_EDITIONS);
            for(Edition edition: editions){
                statement.setInt(1, id);
                statement.setInt(2, edition.getId());
                statement.execute();
            }

        } catch (SQLException e) {
            if(con != null)
                Connector.getInstance().rollbackAndClose(con);
            LOGGER.error(e);
            return false;
        }finally {
            if(con != null){
                Connector.getInstance().close(statement);
                Connector.getInstance().commitAndClose(con);
            }
        }
        return true;
    }

    @Override
    public List<User> findAllUsersWithoutEditions(int size, int from){
        LOGGER.info("Start user search");
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement statement = null;
        List<User> users = new ArrayList<>();
        try{
            con = Connector.getInstance().getConnection();
            statement = con.prepareStatement(SQL_SELECT_ALL_USERS_WITHOUT_EDITIONS);
            statement.setInt(1, size);
            statement.setInt(2, from);
            rs = statement.executeQuery();
            while (rs.next()){
                User user = extractFromResultSet(rs);
                users.add(user);
            }
        } catch (SQLException e) {
            if(con != null)
                Connector.getInstance().rollbackAndClose(con);
            LOGGER.error(e);
        }finally {
            try {
                if(con != null && !con.isClosed()){
                    Connector.getInstance().close(rs);
                    Connector.getInstance().close(statement);
                    Connector.getInstance().commitAndClose(con);
                }
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
        LOGGER.info("End search");
        return users;
    }

    @Override
    public int tableSize(){
        LOGGER.info("Start user search");
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement statement = null;
        int size = -1;
        try{
            con = Connector.getInstance().getConnection();
            statement = con.prepareStatement(SQL_COUNT_TABLE_SIZE);
            rs = statement.executeQuery();
            if (rs.next()){
                size = rs.getInt("size");
            }
        } catch (SQLException e) {
            if(con != null)
                Connector.getInstance().rollbackAndClose(con);
            LOGGER.error(e);
        }finally {
            try {
                if(con != null && !con.isClosed()){
                    Connector.getInstance().close(rs);
                    Connector.getInstance().close(statement);
                    Connector.getInstance().commitAndClose(con);
                }
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
        LOGGER.info("End search");
        return size;
    }
}
