package models.dao.factory;

import models.dao.interfaces.*;
import models.dao.mysqlfactotries.*;

public class MySQLDAOFactory extends DAOFactory {
    private MySQLDAOFactory(){}
    private static MySQLDAOFactory instance;

    public static MySQLDAOFactory getInstance() {
        if(instance == null){
            synchronized (MySQLDAOFactory.class){
                if(instance == null){
                    instance = new MySQLDAOFactory();
                }
            }
        }
        return instance;
    }

    @Override
    public AuthorDAO getAuthorDAO() {
        return AuthorDAOFactory.getInstance();
    }

    @Override
    public UserDAO getUserDAO() {
        return UserDAOFactory.getInstance();
    }

    @Override
    public GenreDAO getGenreDAO() {
        return GenreDAOFactory.getInstance();
    }

    @Override
    public EditionDAO getEditionDAO() {
        return EditionDAOFactory.getInstance();
    }


}
