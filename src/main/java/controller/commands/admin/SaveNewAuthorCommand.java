package controller.commands.admin;

import controller.commands.Command;
import manegers.Messages;
import manegers.Path;
import models.entity.Author;
import org.apache.log4j.Logger;
import services.AdminService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SaveNewAuthorCommand implements Command {
    private static final String NAME_UK = "newAuthor_uk";
    private static final String NAME_EN = "newAuthor_en";

    private AdminService adminService = AdminService.getInstance();
    private CheckInput check = CheckInput.getInstance();

    private static final Logger LOGGER = Logger.getLogger(SaveNewAuthorCommand.class);
    /**
     * Add new author to the db, before checking input. Redirect to previous page with messages
     * */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Command start execution");
        String locale = (String) request.getSession().getAttribute(LOCALE);

        String nameUk = request.getParameter(NAME_UK);
        String nameEn = request.getParameter(NAME_EN);
        boolean error = false;

        if(!check.checkNameUk(nameUk)){
            request.setAttribute("nameUkAuthorError", Messages.getInstance(locale).getString(Messages.INVALID_NAME_UK_ERROR));
            LOGGER.info("Incorrect name UK");
            error = true;
        }

        if(!check.checkNameEn(nameEn)){
            request.setAttribute("nameEnAuthorError", Messages.getInstance(locale).getString(Messages.INVALID_NAME_EN_ERROR));
            LOGGER.info("Incorrect name EN");
            error = true;
        }

        if(!error){
            Author author = new Author.Builder().setNameUk(nameUk).setNameEn(nameEn).build();
            if(!adminService.addNewAuthor(author)){
                request.setAttribute("addingAuthorError", Messages.getInstance(locale).getString(Messages.ADDING_AUTHOR_ERROR));
                LOGGER.info("Exception in db");
            }else{
                request.setAttribute("authorAddedSuccess", Messages.getInstance(locale).getString(Messages.AUTHOR_ADDED_SUCCESS));
            }
        }
        LOGGER.info("Command finish execution");
        return Path.ADMIN_NEW_EDITION;
    }
}
