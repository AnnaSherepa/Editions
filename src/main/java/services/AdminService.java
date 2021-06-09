package services;

import models.dao.factory.DAOFactory;
import models.entity.Author;
import models.entity.Edition;
import models.entity.Genre;
import models.entity.User;

import java.util.List;

public class AdminService {

    private AdminService(){}
    private static  AdminService instance;
    public static AdminService getInstance() {
        if(instance == null){
            synchronized (AdminService.class){
                if(instance == null){
                    instance = new AdminService();
                }
            }
        }
        return instance;
    }
    private DAOFactory mySQLDAOFactory = DAOFactory.getMySQLDAOFactory();

    public boolean updateUserStatus(int id, int newStatus){
        return mySQLDAOFactory.getUserDAO().updateStatus(id, newStatus);
    }

    public List<User> getAllUsers(int size, int from){
        return mySQLDAOFactory.getUserDAO().findAllUsersWithoutEditions(size, from);
    }


    public boolean addNewGenre(Genre genre){
        return mySQLDAOFactory.getGenreDAO().insert(genre);
    }

    public boolean addNewAuthor(Author author){
        return mySQLDAOFactory.getAuthorDAO().insert(author);
    }

    public boolean addNewEdition(Edition edition){
        return mySQLDAOFactory.getEditionDAO().insert(edition);
    }

    public boolean deleteEditionById(int id){
        return mySQLDAOFactory.getEditionDAO().deleteById(id);
    }

    public boolean updateEdition(Edition edition){
        return mySQLDAOFactory.getEditionDAO().update(edition);
    }

    public int getSizeTableUsers(){
        return mySQLDAOFactory.getUserDAO().tableSize();
    }
}
