package models.dao.interfaces;

import models.entity.Genre;
import java.util.List;

public interface GenreDAO {

    /**
     * This method get from db record of genre by given id
     * and return new instance of Genre object
     * @param id - value of record's id
     * @return Genre
     * */
    Genre findById(int id);

    /**
     * This method get from db record of genre by given genre name
     * and return new instance of Genre object
     *
     * Name of genre have to be unique
     *
     * @param name - genre name
     * @return Genre
     * */
    Genre findByNameUk(String name);

    /**
     * This method get from db record of genre by given genre name
     * and return new instance of Genre object
     *
     * Name of genre have to be unique
     *
     * @param name - genre name
     * @return Genre
     * */
    Genre findByNameEn(String name);


    /**
     * Method return all records from db table genres
     * as list of Genre objects
     * @return List/<Genre/>
     * */
    List<Genre> findAll();

    /**
     * Method add new record to db table Genres,
     * based on given Genre object as a parameter
     *
     * If the insertion is successful,
     * the method returns true,
     * otherwise false
     *
     * @param genre - object of type Genre
     * @return boolean
     * */
    boolean insert(Genre genre);

    /**
     * Method update record from db table Genres,
     * based on given Genre object as a parameter
     *
     * If the updating is successful,
     * the method returns true,
     * otherwise false
     *
     * @param genre - old object, by which will be updated
     * @param newNameUk - name, which will be assigned as new value in nam_uk
     * @param newNameEn - name, which will be assigned as new value in name_en
     * @return boolean
     * */
    boolean updateName(Genre genre, String newNameUk, String newNameEn);

    /**
     * Method delete record from db table genres,
     * based on given genre name as a parameter
     *
     *  If the removal is successful,
     * the method returns true,
     * otherwise false
     *
     * @param nameUk - genre name which have to be deleted
     * @return boolean
     * */
    boolean deleteByNameUk(String nameUk);

    /**
     * Method delete record from db table genres,
     * based on given genre name as a parameter
     *
     *  If the removal is successful,
     * the method returns true,
     * otherwise false
     *
     * @param nameEn - genre name which have to be deleted
     * @return boolean
     * */
    boolean deleteByNameEn(String nameEn);
}
