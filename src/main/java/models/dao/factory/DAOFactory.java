package models.dao.factory;

import models.dao.interfaces.*;


public abstract class DAOFactory {

    public static DAOFactory getMySQLDAOFactory(){return MySQLDAOFactory.getInstance();}
    public abstract AuthorDAO getAuthorDAO();
    public abstract UserDAO getUserDAO();
    public abstract GenreDAO getGenreDAO();
    public abstract EditionDAO getEditionDAO();

}
