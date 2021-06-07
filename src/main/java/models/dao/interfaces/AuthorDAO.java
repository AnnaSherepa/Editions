package models.dao.interfaces;

import models.entity.Author;
import java.util.List;

public interface AuthorDAO {

    /**
     * This method get from db author by given id
     * and return new instance of Author object
     * @param id - value of record's id
     * @return Author
     * */
    Author findById(int id);

    /**
     * Method return all records from db table authors
     * as list of author objects
     * @return List/<Author/>
     * */
    List<Author> findAll();

    /**
     * Method add new record to db table authors,
     * based on given Author object as a parameter
     *
     *  If the insertion is successful,
     * the method returns true,
     * otherwise false
     *
     * @param author - object of type Author
     * @return boolean
     * */
    boolean insert(Author author);

    /**
     * Method delete record from db table authors,
     * based on given id as a parameter
     *
     *  If the removal is successful,
     * the method returns true,
     * otherwise false
     *
     * @param id - id of record which have to be deleted
     * @return boolean
     * */
    boolean deleteById(int id);

}
