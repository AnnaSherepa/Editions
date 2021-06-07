package models.dao.mysqlfactotries;

import models.dao.factory.Connector;
import models.dao.interfaces.GenreDAO;
import models.entity.Genre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


public class GenreDAOFactory implements GenreDAO {
    private static final String SQL_SELECT_ALL_GENRES = "SELECT * FROM genres ";

    private static final String SQL_SELECT_GENRE_BY_ID = SQL_SELECT_ALL_GENRES + "WHERE idGenre = ? ";
    private static final String SQL_SELECT_GENRE_BY_NAME_UK = SQL_SELECT_ALL_GENRES + "WHERE g_name_uk = ? ";
    private static final String SQL_SELECT_GENRE_BY_NAME_EN = SQL_SELECT_ALL_GENRES + "WHERE g_name_en = ? ";

    private static final String SQL_DELETE_GENRE_BY_NAME_UK =  "DELETE FROM genres WHERE g_name_uk = ? ";
    private static final String SQL_DELETE_GENRE_BY_NAME_EN =  "DELETE FROM genres WHERE g_name_en = ? ";
    private static final String SQL_UPDATE_GENRE_NAME =  "UPDATE genres SET g_nameUk = ?, g_nameEn = ?  WHERE idGenre = ? ";
    private static final String SQL_INSERT_NEW_GENRE_BY_NAME =  "INSERT INTO genres(g_name_uk, g_name_en) VALUES (?,?)";



    private GenreDAOFactory(){}
    private static GenreDAOFactory instance;
    public static synchronized GenreDAOFactory getInstance() {
        if (instance == null){
            instance = new GenreDAOFactory();
        }
        return instance;
    }
    Genre extractFromResultSet(ResultSet rs) throws SQLException {
        return  new Genre.Builder().setId(rs.getInt("idGenre"))
                .setNameUk(rs.getNString("g_name_uk"))
                .setNameEn(rs.getNString("g_name_en"))
                .build();
    }
    private List<Genre> find(String sql, int id, String name){
        List<Genre> genres = new ArrayList<>();
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            con = Connector.getInstance().getConnection();

            statement = con.prepareStatement(sql);
            if(id != -1)
                statement.setInt(1, id);
            else if(name != null)
                statement.setString(1, name);
            System.out.println(statement);
            rs = statement.executeQuery();
            while (rs.next()){
                Genre genre = extractFromResultSet(rs);
                genres.add(genre);
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
        return genres;
    }


    private boolean deleteFromTable(String sql, String value){
        Connection con = null;
        PreparedStatement statement = null;

        try{
            con = Connector.getInstance().getConnection();
            statement = con.prepareStatement(sql);
            statement.setString(1, value);
            statement.executeUpdate();
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

    @Override
    public Genre findById(int id) {
        List<Genre> genres = find(SQL_SELECT_GENRE_BY_ID, id, null);
        if(genres.isEmpty()){
            throw new NoSuchElementException("Genre with id: " + id + " was not found");
        }
        return genres.get(0);
    }

    @Override
    public Genre findByNameUk(String name) {
        return find(SQL_SELECT_GENRE_BY_NAME_UK, -1, name).get(0);
    }

    @Override
    public Genre findByNameEn(String name) {
        return find(SQL_SELECT_GENRE_BY_NAME_EN, -1, name).get(0);
    }


    @Override
    public List<Genre> findAll(){
        return find(SQL_SELECT_ALL_GENRES, -1, null);
    }


    @Override
    public boolean insert(Genre genre) {
        Connection con = null;
        PreparedStatement statement = null;
        try{
            con = Connector.getInstance().getConnection();
            statement = con.prepareStatement(SQL_INSERT_NEW_GENRE_BY_NAME, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, genre.getNameUk());
            statement.setString(2, genre.getNameEn());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) { throw new SQLException("Creating genre failed, no rows affected."); }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) { genre.setId(generatedKeys.getInt(1)); }
                else{ throw new SQLException("Creating genre failed, no ID obtained."); }
            }

        } catch (SQLException e) {
            if(con != null)
                Connector.getInstance().rollbackAndClose(con);
            System.out.println(e);
            return false;
        }finally {
            if(con != null) {
                Connector.getInstance().close(statement);
                Connector.getInstance().commitAndClose(con);
            }
        }
        return true;
    }

    @Override
    public boolean updateName(Genre genre, String newNameUk, String newNameEn) {

        Connection con = null;
        PreparedStatement statement = null;
        boolean result = true;
        try{
            con = Connector.getInstance().getConnection();
            statement = con.prepareStatement(SQL_UPDATE_GENRE_NAME);
            statement.setString(1, newNameUk);
            statement.setString(2, newNameEn);
            statement.setInt(3, genre.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            if(con != null)
                Connector.getInstance().rollbackAndClose(con);
            System.out.println(e);
            result = false;
        }finally {
            if(con != null){
                Connector.getInstance().close(statement);
                Connector.getInstance().commitAndClose(con);
            }
        }

        if(result) {
            genre.setNameUk(newNameUk);
            genre.setNameEn(newNameEn);
        }
        return result;
    }

    @Override
    public boolean deleteByNameUk(String nameUk) {
        return deleteFromTable(SQL_DELETE_GENRE_BY_NAME_UK, nameUk);
    }

    @Override
    public boolean deleteByNameEn(String nameEn) {
        return deleteFromTable(SQL_DELETE_GENRE_BY_NAME_EN, nameEn);
    }

}
