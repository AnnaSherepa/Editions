package services;

import models.dao.factory.DAOFactory;
import models.dao.interfaces.UserDAO;
import models.dao.mysqlfactotries.EditionDAOFactory;
import models.dao.mysqlfactotries.UserDAOFactory;
import models.entity.Edition;
import models.entity.User;

import java.math.BigDecimal;
import java.util.List;

public class UserService {
    private static final String VALID_BALANCE = "\\b(\\d*\\.)?\\d+\\b";
    private UserService(){}
    private static UserService instance;
    public static UserService getInstance() {
        if(instance == null){
            synchronized (AdminService.class){
                if(instance == null){
                    instance = new UserService();
                }
            }
        }
        return instance;
    }

    private DAOFactory mySQLDAOFactory = DAOFactory.getMySQLDAOFactory();

    public boolean checkBalance(String balance){
        return balance.matches(VALID_BALANCE);
    }

    public boolean updateBalance(int id, BigDecimal balance){
        return mySQLDAOFactory.getUserDAO().updateBalance(id, balance);
    }

    public Edition getEditionById(int id){
        return mySQLDAOFactory.getEditionDAO().findById(id);
    }

    public boolean setEditionsToUser(int id, List<Edition> editions){
        return mySQLDAOFactory.getUserDAO().setEditionsToUser(id, editions);
    }

    public boolean deleteUserEditionById(int idUser, int idEdition){
        return mySQLDAOFactory.getUserDAO().deleteUserEditionById(idUser, idEdition);
    }
    public User getUpdatedUser(int id){
        return mySQLDAOFactory.getUserDAO().findById(id);
    }

    public List<Edition> searchByTitle(String searchRequest){
        return mySQLDAOFactory.getEditionDAO().searchByTitle(searchRequest);
    }

    public List<Edition> getEditionsByIdGenre(int id){
        return mySQLDAOFactory.getEditionDAO().findAllEditionByGenreId(id);
    }

    public List<Edition> orderEditionsByTitleUk(){
        return mySQLDAOFactory.getEditionDAO().findAllEditionOrderByTitleUk();
    }

    public List<Edition> orderEditionsByTitleEn(){
        return mySQLDAOFactory.getEditionDAO().findAllEditionOrderByTitleEn();
    }

    public List<Edition> orderEditionsByAuthorNameUk(){
        return mySQLDAOFactory.getEditionDAO().findAllEditionOrderByAuthorNameUk();
    }

    public List<Edition> orderEditionsByAuthorNameEn(){
        return mySQLDAOFactory.getEditionDAO().findAllEditionOrderByAuthorNameEn();
    }

    public List<Edition> orderEditionsByPrice(){
        return mySQLDAOFactory.getEditionDAO().findAllEditionOrderByPrice();
    }

    public List<Edition> getAllEditions(){
        return mySQLDAOFactory.getEditionDAO().findAll();
    }
}
