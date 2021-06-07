package services;

import models.dao.mysqlfactotries.AuthorDAOFactory;
import models.dao.mysqlfactotries.EditionDAOFactory;
import models.dao.mysqlfactotries.GenreDAOFactory;
import models.dao.mysqlfactotries.UserDAOFactory;
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

    public boolean updateUserStatus(int id, int newStatus){
        return UserDAOFactory.getInstance().updateStatus(id, newStatus);
    }

    public List<User> getAllUsers(int size, int from){
        return UserDAOFactory.getInstance().findAllUsersWithoutEditions(size, from);
    }


    public boolean addNewGenre(Genre genre){
        return GenreDAOFactory.getInstance().insert(genre);
    }

    public boolean addNewAuthor(Author author){
        return AuthorDAOFactory.getInstance().insert(author);
    }

    public boolean addNewEdition(Edition edition){
        return EditionDAOFactory.getInstance().insert(edition);
    }
    public boolean deleteEditionById(int id){
        return EditionDAOFactory.getInstance().deleteById(id);
    }

    public boolean updateEdition(Edition edition){
        return EditionDAOFactory.getInstance().update(edition);
    }
    public int getSizeTableUsers(){return UserDAOFactory.getInstance().tableSize();}
}
