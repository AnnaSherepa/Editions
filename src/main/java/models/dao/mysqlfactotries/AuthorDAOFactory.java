package models.dao.mysqlfactotries;

import models.dao.factory.Connector;
import models.dao.interfaces.AuthorDAO;
import models.entity.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class AuthorDAOFactory implements AuthorDAO {
    private static final String SQL_SELECT_ALL_AUTHORS = "SELECT * FROM authors ";
    private static final String SQL_SELECT_AUTHOR_BY_ID = SQL_SELECT_ALL_AUTHORS + "WHERE idAuthor = ? ";
    private static final String SQL_INSERT_AUTHOR = "INSERT INTO authors(a_name_uk, a_name_en )  VALUES(?, ?) ";

    private static final String SQL_DELETE_AUTHOR_BY_ID = "DELETE FROM authors WHERE idAuthor = ?";

    private AuthorDAOFactory(){}
    private static AuthorDAOFactory instance;

    public static synchronized AuthorDAOFactory getInstance(){
        if(instance == null){
            instance = new AuthorDAOFactory();
        }
        return instance;
    }
    Author extractFromResultSet(ResultSet rs) throws SQLException {
        return  new Author.Builder().setId(rs.getInt("idAuthor"))
                .setNameUk(rs.getNString("a_name_uk"))
                .setNameEn(rs.getNString("a_name_en"))
                .build();
    }
    public List<Author> find(String sql, int id){
        List<Author> authors = new ArrayList<>();
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
                Author author = extractFromResultSet(rs);
                authors.add(author);
            }
        } catch (SQLException e) {
            if(con != null)
                Connector.getInstance().rollbackAndClose(con);
            System.err.println(e);
        }finally {
            if (con != null){
                Connector.getInstance().close(statement);
                Connector.getInstance().close(rs);
                Connector.getInstance().commitAndClose(con);
            }

        }
        return authors;
    }


    @Override
    public Author findById(int id) {
        List<Author> authors = find(SQL_SELECT_AUTHOR_BY_ID, id);
        if(authors.isEmpty())
            throw new NoSuchElementException();
        return authors.get(0);
    }

    @Override
    public List<Author> findAll() {
        return find(SQL_SELECT_ALL_AUTHORS, -1);
    }

    @Override
    public boolean insert(Author author) {
        Connection con = null;
        PreparedStatement statement = null;
        try{
            con = Connector.getInstance().getConnection();
            statement = con.prepareStatement(SQL_INSERT_AUTHOR, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, author.getNameUk());
            statement.setString(2, author.getNameEn());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating author failed, no rows affected.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    author.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating author failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            if(con != null)
                Connector.getInstance().rollbackAndClose(con);
            System.out.println(e);
            return false;
        }finally {
            if (con != null){
                Connector.getInstance().close(statement);
                Connector.getInstance().commitAndClose(con);
            }
        }
        return true;
    }

    @Override
    public boolean deleteById(int id) {
        Connection con = null;
        PreparedStatement statement = null;
        try{
            con = Connector.getInstance().getConnection();
            statement = con.prepareStatement(SQL_DELETE_AUTHOR_BY_ID);
            statement.setInt(1, id);
            /* if any edition -> have to work ON DELETE CASCADE */
        } catch (SQLException e) {
            if(con != null)
                Connector.getInstance().rollbackAndClose(con);
            System.out.println(e);
            return false;
        }finally {
            if(con != null){
                Connector.getInstance().close(statement);
                Connector.getInstance().commitAndClose(con);
            }
        }
        return true;
    }
}
