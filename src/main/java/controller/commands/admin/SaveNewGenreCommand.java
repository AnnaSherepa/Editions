package controller.commands.admin;

import controller.commands.Command;
import manegers.Messages;
import manegers.Path;
import models.entity.Genre;
import org.apache.log4j.Logger;
import services.AdminService;
import services.InitialService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class SaveNewGenreCommand implements Command {
    private static final String NAME_UK = "newGenre_uk";
    private static final String NAME_EN = "newGenre_en";

    private AdminService adminService = AdminService.getInstance();
    private InitialService initialService = InitialService.getInstance();
    private CheckInput check = CheckInput.getInstance();

    private static final Logger LOGGER = Logger.getLogger(SaveNewGenreCommand.class);
    /**
     * Add new genre to the db, before checking input. Redirect to previous page with messages
     * */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Command start execution");
        String locale = (String) request.getSession().getAttribute(LOCALE);
        String page = null;
        String nameUk = request.getParameter(NAME_UK);
        String nameEn = request.getParameter(NAME_EN);
        boolean error = false;

        if(!check.checkNameUk(nameUk)){
            request.setAttribute("nameUkError", Messages.getInstance(locale).getString(Messages.INVALID_NAME_UK_ERROR));
            LOGGER.info("Incorrect name UK");
            error = true;
        }

        if(!check.checkNameEn(nameEn)){
            request.setAttribute("nameEnError", Messages.getInstance(locale).getString(Messages.INVALID_NAME_EN_ERROR));
            LOGGER.info("Incorrect name EN");
            error = true;
        }

        if(!error){
            Genre genre = new Genre.Builder().setNameUk(nameUk).setNameEn(nameEn).build();
            if(!adminService.addNewGenre(genre)){
                request.setAttribute("addingGenreError", Messages.getInstance(locale).getString(Messages.ADDING_GENRE_ERROR));
                LOGGER.info("Exception in db");
            }else{
                request.getServletContext().setAttribute("genres", initialService.allListOfGenres());
                request.setAttribute("genreAddedSuccess", Messages.getInstance(locale).getString(Messages.GENRE_ADDED_SUCCESS));
            }
        }
        LOGGER.info("Command finish execution");
        return Path.ADMIN_NEW_EDITION;
    }
}
