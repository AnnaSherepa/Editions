package services;

import models.dao.factory.DAOFactory;
import models.entity.Author;
import models.entity.Edition;
import models.entity.Genre;

import java.util.List;

public class InitialService {
    private InitialService(){}
    private static  InitialService instance;

    public static InitialService getInstance() {
        if(instance == null){
            synchronized (InitialService.class){
                if(instance == null){
                    instance = new InitialService();
                }
            }
        }
        return instance;
    }
    private DAOFactory mySQLDAOFactory = DAOFactory.getMySQLDAOFactory();

    /**
     * Get from DB Editions List
     *
     * @return List/<Edition/>
     * */

    public List<Edition> allListOfEditions(){
        return mySQLDAOFactory.getEditionDAO().findAll();
    }

    /**
     * Get from DB Genres List
     *
     * @return List/<Edition/>
     * */

    public List<Genre> allListOfGenres(){
        return mySQLDAOFactory.getGenreDAO().findAll();
    }

    /**
     * Get from DB Authors List
     *
     * @return List/<Edition/>
     * */

    public List<Author> allListOfAuthors(){
        return mySQLDAOFactory.getAuthorDAO().findAll();
    }

}
