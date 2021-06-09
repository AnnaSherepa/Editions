package models.dao.mysqlfactotries;

import models.dao.factory.Connector;
import models.dao.interfaces.EditionDAO;
import models.entity.Author;
import models.entity.Edition;
import models.entity.Genre;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class EditionDAOFactory implements EditionDAO {
    private static final Logger  LOGGER = Logger.getLogger(EditionDAOFactory.class);

    private static final String SQL_SELECT_ALL_EDITIONS = "SELECT * " +
            "FROM finalproject.editions\n" +
            "LEFT JOIN authors a on editions.id_author = a.idAuthor\n" +
            "LEFT JOIN genres g on editions.id_genre = g.idGenre ";
    private static final String SQL_SELECT_EDITION_BY_ID = SQL_SELECT_ALL_EDITIONS + " WHERE idEdition = ?";

    private static final String SQL_SELECT_EDITION_BY_ID_GENRE = SQL_SELECT_ALL_EDITIONS + " WHERE id_genre "
            + "= ?";

    private static final String SQL_SELECT_EDITION_ORDER_BY_PRICE = SQL_SELECT_ALL_EDITIONS + " ORDER BY price ";

    private static final String SQL_SELECT_EDITION_ORDER_BY_A_NAME_UK = SQL_SELECT_ALL_EDITIONS + " ORDER BY a.a_name_uk";
    private static final String SQL_SELECT_EDITION_ORDER_BY_A_NAME_EN = SQL_SELECT_ALL_EDITIONS + " ORDER BY a.a_name_en";

    private static final String SQL_SELECT_EDITION_ORDER_BY_TITLE_UK = SQL_SELECT_ALL_EDITIONS + " ORDER BY title_uk";
    private static final String SQL_SELECT_EDITION_ORDER_BY_TITLE_EN = SQL_SELECT_ALL_EDITIONS + " ORDER BY title_en";

    private static final String SQL_SELECT_EDITION_SEARCH_BY_TITLE = SQL_SELECT_ALL_EDITIONS + " WHERE title_en LIKE '%?%' OR title_uk LIKE '%?%'";


    private static final String SQL_DELETE_EDITION_BY_ID =  "DELETE FROM editions WHERE idEdition = ?";


    private static final String SQL_INSERT_EDITION =  "INSERT INTO editions(" +
            "title_uk, title_en, description_uk, description_en, imgPath, id_genre, id_author,  price, measurementPrice)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE_EDITION =  "UPDATE editions " +
            "SET title_uk = ?, title_en = ?, description_uk = ?, description_en = ?, imgPath = ?," +
            " id_genre = ?, id_author = ?,  price = ?, measurementPrice = ? " +
            " WHERE idEdition = ?";


    private EditionDAOFactory(){}
    private static EditionDAOFactory instance;

    public static synchronized EditionDAOFactory getInstance() {
        if(instance == null)
            instance = new EditionDAOFactory();
        return instance;
    }


    Edition extractFromResultSet(ResultSet rs) throws SQLException {
        Genre genre = GenreDAOFactory.getInstance().extractFromResultSet(rs);
        Author author = AuthorDAOFactory.getInstance().extractFromResultSet(rs);
        Edition edition =  new Edition.Builder()
                .setId(rs.getInt("idEdition"))
                .setIdGenre(rs.getInt("id_genre"))
                .setIdAuthor(rs.getInt("id_author"))
                .setTitleUk(rs.getNString("title_uk"))
                .setTitleEn(rs.getNString("title_en"))
                .setDescriptionUk(rs.getNString("description_uk"))
                .setDescriptionEn(rs.getNString("description_en"))
                .setPrice(rs.getBigDecimal("price"))
                .setMeasurement(rs.getNString("measurementPrice"))
                .setImgPath(rs.getNString("imgPath"))
                .build();
        edition.setAuthor(author);
        edition.setGenre(genre);
        return edition;
    }

    public List<Edition> find(String sql, int id, String value){
        System.out.println(sql);
        List<Edition> editions = new ArrayList<>();
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            con = Connector.getInstance().getConnection();
            statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            if(id != -1)
                statement.setInt(1, id);
            if(value != null)
                statement.setString(1, value);

            rs = statement.executeQuery();
            while (rs.next()){
                Edition edition = extractFromResultSet(rs);
                editions.add(edition);
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
        return editions;
    }

    @Override
    public Edition findById(int id) {
        List<Edition> editions = find(SQL_SELECT_EDITION_BY_ID, id, null);
        if(editions.isEmpty())
            throw new NoSuchElementException();
        return editions.get(0);
    }

    @Override
    public List<Edition> findAll() {
        return find(SQL_SELECT_ALL_EDITIONS, -1, null);
    }

    @Override
    public boolean insert(Edition edition) {
        LOGGER.info("Inserting starts");
        Connection con = null;
        PreparedStatement statement = null;
        try{
            con = Connector.getInstance().getConnection();
            statement = con.prepareStatement(SQL_INSERT_EDITION, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, edition.getTitleUk());
            statement.setString(2, edition.getTitleEn());
            statement.setString(3, edition.getDescriptionUk());
            statement.setString(4, edition.getDescriptionEn());
            statement.setString(5, edition.getImgPath());
            statement.setInt(6, edition.getIdGenre());
            statement.setInt(7, edition.getIdAuthor());
            statement.setBigDecimal(8, edition.getPrice());
            statement.setString(9, edition.getMeasurement());

            LOGGER.info("Execution of statement: " + statement);
            int affectedRows = statement.executeUpdate();
            LOGGER.info("Statement is executed");
            if (affectedRows == 0) {
                throw new SQLException("Creating edition failed, no rows affected.");
            }
            LOGGER.info("Edition is created");
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    edition.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating edition failed, no ID obtained.");
                }
            }
            LOGGER.info("Keys is compared. Id to edition is set");
        } catch (SQLException e) {
            LOGGER.info(e);
            if(con != null)
                Connector.getInstance().rollbackAndClose(con);
            return false;
        }finally {
            LOGGER.info("Final part. Closing all open resources");
            if (con != null && statement != null){
                Connector.getInstance().close(statement);
                Connector.getInstance().commitAndClose(con);
            }
        }
        LOGGER.info("Inserting finished");
        return true;
    }

    @Override
    public boolean update(Edition edition) {
        LOGGER.info("Updating starts");
        Connection con = null;
        PreparedStatement statement = null;
        try{
            con = Connector.getInstance().getConnection();
            statement = con.prepareStatement(SQL_UPDATE_EDITION, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, edition.getTitleUk());
            statement.setString(2, edition.getTitleEn());
            statement.setString(3, edition.getDescriptionUk());
            statement.setString(4, edition.getDescriptionEn());
            statement.setString(5, edition.getImgPath());
            statement.setInt(6, edition.getIdGenre());
            statement.setInt(7, edition.getIdAuthor());
            statement.setBigDecimal(8, edition.getPrice());
            statement.setString(9, edition.getMeasurement());
            statement.setInt(10, edition.getId());

            LOGGER.info("Execution of statement: " + statement);
            statement.executeUpdate();
            LOGGER.info("Statement is executed");
        } catch (SQLException e) {
            LOGGER.info(e);
            if(con != null)
                Connector.getInstance().rollbackAndClose(con);
            return false;
        }finally {
            LOGGER.info("Final part. Closing all open resources");
            if (con != null && statement != null){
                Connector.getInstance().close(statement);
                Connector.getInstance().commitAndClose(con);
            }
        }
        LOGGER.info("Inserting finished");
        return true;
    }

    @Override
    public boolean deleteById(int id) {
        LOGGER.info("DAO delete edition by id: " + id);
        Connection con = null;
        PreparedStatement statement = null;
        try{
            con = Connector.getInstance().getConnection();
            statement = con.prepareStatement(SQL_DELETE_EDITION_BY_ID);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            if(con != null)
                Connector.getInstance().rollbackAndClose(con);
            LOGGER.info(e);
            return false;
        }finally {
            if(con != null){
                Connector.getInstance().close(statement);
                Connector.getInstance().commitAndClose(con);
            }
        }
        LOGGER.info("DAO: deletion is finished");
        return true;
    }

    @Override
    public List<Edition> findAllEditionByGenreId(int idGenre) {
        return find(SQL_SELECT_EDITION_BY_ID_GENRE, idGenre, null);
    }

    @Override
    public List<Edition> findAllEditionOrderByPrice() {
        return find(SQL_SELECT_EDITION_ORDER_BY_PRICE, -1, null);
    }

    @Override
    public List<Edition> findAllEditionOrderByAuthorNameUk() {
        return find(SQL_SELECT_EDITION_ORDER_BY_A_NAME_UK, -1, null);
    }

    @Override
    public List<Edition> findAllEditionOrderByAuthorNameEn() {
        return find(SQL_SELECT_EDITION_ORDER_BY_A_NAME_EN, -1, null);
    }
    @Override
    public List<Edition> findAllEditionOrderByTitleEn() {
        return find(SQL_SELECT_EDITION_ORDER_BY_TITLE_EN, -1, null);
    }

    @Override
    public List<Edition> findAllEditionOrderByTitleUk() {
        return find(SQL_SELECT_EDITION_ORDER_BY_TITLE_UK, -1, null);
    }

    @Override
    public List<Edition> searchByTitle(String title){
        List<Edition> editions = new ArrayList<>();
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            con = Connector.getInstance().getConnection();
            statement = con.prepareStatement(SQL_SELECT_EDITION_SEARCH_BY_TITLE, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, title);
            statement.setString(1, title);
            rs = statement.executeQuery();
            while (rs.next()){
                Edition edition = extractFromResultSet(rs);
                editions.add(edition);
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
        return editions;
    }
}
