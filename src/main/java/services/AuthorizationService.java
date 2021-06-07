package services;

import models.dao.factory.DAOFactory;

import models.entity.User;
import models.enums.Role;

import java.math.BigDecimal;
import java.util.NoSuchElementException;


public class AuthorizationService {
    private AuthorizationService(){}
    private static  AuthorizationService instance;

    public static AuthorizationService getInstance() {
        if(instance == null){
            synchronized (AuthorizationService.class){
                if(instance == null){
                    instance = new AuthorizationService();
                }
            }
        }
        return instance;
    }
    private DAOFactory mySQLDAOFactory = DAOFactory.getMySQLDAOFactory();

    public boolean signUp(String login, String pass, String name, String surname,  String email){
        User user = new User.Builder()
                .setLogin(login)
                .setPass(pass)
                .setEmail(email)
                .setName(name)
                .setSurname(surname)
                .setRole(Role.USER.getRole()).setBalance(new BigDecimal(0)).build();
        return mySQLDAOFactory.getUserDAO().insert(user);
    }

    public User logIn(String login, String pass) throws NoSuchElementException{
        User user = mySQLDAOFactory.getUserDAO().findByLogin(login);
        if(user != null && user.getPass().equals(pass)){
            return user;
        }
        throw new NoSuchElementException();
    }

}
