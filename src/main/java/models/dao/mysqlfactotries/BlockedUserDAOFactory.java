package models.dao.mysqlfactotries;

import models.dao.factory.Connector;
import models.dao.interfaces.BlockedUserDAO;
import models.entity.BlockedUser;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class BlockedUserDAOFactory implements BlockedUserDAO {
    private static final String SQL_SELECT_ALL_BLOCKED_USERS= "SELECT * FROM blocked_users";
    private static final String SQL_SELECT_BLOCKED_USER_BY_ID = SQL_SELECT_ALL_BLOCKED_USERS + "WHERE id = ?";

    private static final String SQL_DELETE_BLOCKED_USER_BY_ID =  "DELETE FROM blocked_users WHERE id = ?";
    private static final String SQL_INSERT_BLOCKED_USER =  "INSERT INTO blocked_users(id, reason, endDate) VALUES (?, ?, ?)";

    private BlockedUserDAOFactory(){}
    private static BlockedUserDAOFactory instance;
    public static synchronized BlockedUserDAOFactory getInstance() {
        if(instance ==null)
            instance = new BlockedUserDAOFactory();
        return instance;
    }

    private List<BlockedUser> find(String sql, int id){
        List<BlockedUser> blockedUsers = new ArrayList<>();
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            con = Connector.getInstance().getConnection();
            statement = con.prepareStatement(sql);
            if(id != -1)
                statement.setInt(1, id);
            rs = statement.executeQuery();
            while (rs.next()){
                Date sqlDate = rs.getDate("endDate");
                java.util.Date javaDate = DateFormat.getInstance().parse(sqlDate.toString());
                blockedUsers.add(new BlockedUser(rs.getInt("id"),
                        rs.getNString("reason"),
                        javaDate));
            }
        } catch (SQLException | ParseException e) {
            if(con != null)
                Connector.getInstance().rollbackAndClose(con);
            System.err.println(e);
        }finally {
            if (con != null)
                Connector.getInstance().commitAndClose(con);
            Connector.getInstance().close(statement);
            Connector.getInstance().close(rs);
        }
        return blockedUsers;
    }


    @Override
    public BlockedUser findById(int id) {
        List<BlockedUser> result = find(SQL_SELECT_BLOCKED_USER_BY_ID, id);
        if(result.isEmpty()){
            throw  new NoSuchElementException("Blocked user with id: " + id + "was not founded");
        }
        return result.get(0);
    }

    @Override
    public List<BlockedUser> findAll() {
        return find(SQL_SELECT_ALL_BLOCKED_USERS, -1);
    }

    @Override
    public boolean insert(BlockedUser user) {
        Connection con = null;
        PreparedStatement statement = null;
        try{
            con = Connector.getInstance().getConnection();
            statement = con.prepareStatement(SQL_INSERT_BLOCKED_USER);
            statement.setInt(1, user.getId());
            statement.setString(2, user.getReason());
            statement.setDate(3, Date.valueOf(user.getEndDate().toString()));
            statement.executeUpdate();
        } catch (SQLException e) {
            if(con != null)
                Connector.getInstance().rollbackAndClose(con);
            System.out.println(e);
            return false;
        }finally {
            if(con != null)
                Connector.getInstance().commitAndClose(con);
            Connector.getInstance().close(statement);
        }
        return true;
    }

    @Override
    public boolean updateBlockedUser(BlockedUser user) {
        /*
        * Think more about updating
        *
        **/
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        Connection con = null;
        PreparedStatement statement = null;
        try{
            con = Connector.getInstance().getConnection();
            statement = con.prepareStatement(SQL_DELETE_BLOCKED_USER_BY_ID);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            if(con != null)
                Connector.getInstance().rollbackAndClose(con);
            System.out.println(e);
            return false;
        }finally {
            if(con != null)
                Connector.getInstance().commitAndClose(con);
            Connector.getInstance().close(statement);
        }
        return true;

    }
}
