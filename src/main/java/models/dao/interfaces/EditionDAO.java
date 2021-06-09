package models.dao.interfaces;

import models.entity.Edition;

import java.util.List;

public interface EditionDAO {

    /**
     * This method get from db record of editions by given id
     * and return new instance of Edition object
     * @param id - value of record's id
     * @return Edition
     *
     * */
    Edition findById(int id);

    /**
     * Method return all records from db table editions
     * as list of Edition objects
     * @return List/<Edition/>
     * */
    List<Edition> findAll();

    /**
     * Method add new record to db editions table,
     * based on given Edition object as a parameter
     *
     * If the insertion is successful,
     * the method returns true,
     * otherwise false
     *
     * @param edition - object of Edition type
     * @return boolean
     * */
    boolean insert(Edition edition);

    /**
     * Method update old record in db editions table,
     * based on given Edition object as a parameter
     *
     * If the updating is successful,
     * the method returns true,
     * otherwise false
     *
     * @param edition - object of Edition type
     * @return boolean
     * */
    boolean update(Edition edition);

    /**
     * Method delete record from db table editions,
     * based on given id as a parameter
     *
     * If the removal is successful,
     * the method returns true,
     * otherwise false
     *
     * @param id - id of record which have to be deleted
     * @return boolean
     * */
    boolean deleteById(int id);

    /**
     * Method return list of editions by selected id genre
     *
     * @param idGenre - id of genre
     * @return List/<Edition/>
     * */
    List<Edition> findAllEditionByGenreId(int idGenre);


    /**
     * Method return list of editions ordered by price
     *
     * @return List/<Edition/>
     **/
    List<Edition> findAllEditionOrderByPrice();

    /**
     * Method return list of editions ordered by author name in uk
     *
     * @return List/<Edition/>
     **/
    List<Edition> findAllEditionOrderByAuthorNameUk();

    /**
     * Method return list of editions ordered by author name in en
     *
     * @return List/<Edition/>
     **/
    List<Edition> findAllEditionOrderByAuthorNameEn();

    /**
     * Method return list of editions ordered by edition title in en
     *
     * @return List/<Edition/>
     **/
    List<Edition> findAllEditionOrderByTitleEn();

    /**
     * Method return list of editions ordered by edition title in uk
     *
     * @return List/<Edition/>
     **/
    List<Edition> findAllEditionOrderByTitleUk();

    /**
     * Method return list of editions where title has a part, given in title parameter.
     * Search in title uk and title en
     *
     * @param title - part which will be searched
     * @return List/<Edition/>
     **/
    List<Edition> searchByTitle(String title);
}
