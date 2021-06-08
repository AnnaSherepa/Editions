package controller.commands.admin;

import controller.commands.Command;
import manegers.Messages;
import manegers.Path;
import manegers.ProjectConstants;
import models.entity.Edition;
import models.entity.Genre;
import org.apache.log4j.Logger;
import services.AdminService;
import services.InitialService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class SaveNewEditionCommand implements Command {
    private static final String TITLE_UK = "title_uk";
    private static final String TITLE_EN = "title_en";

    private static final String IMG_PATH = "img";

    private static final String DESCRIPTION_UK = "description_uk";
    private static final String DESCRIPTION_EN  = "description_en";

    private static final String ID_GENRE  = "id_genre";
    private static final String ID_AUTHOR  = "id_author";

    private static final String PRICE  = "price";
    private static final String MEASUREMENT  = "measurement";


    private AdminService adminService = AdminService.getInstance();
    private InitialService initialService = InitialService.getInstance();
    private static final Logger LOGGER = Logger.getLogger(SaveNewEditionCommand.class);
    /**
     * Add new genre to the db, before checking input. Redirect to previous page with messages
     * */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Command start execution");
        String locale = (String) request.getSession().getAttribute(LOCALE);
        String page = null;
        boolean error = false;
        String titleUk = request.getParameter(TITLE_UK);
        String titleEn = request.getParameter(TITLE_EN);

        String imgPath = request.getParameter(IMG_PATH);
        imgPath = imgPath.equals("") ? null : imgPath;

        String descriptionUk = request.getParameter(DESCRIPTION_UK);
        String descriptionEn = request.getParameter(DESCRIPTION_EN);
        int idGenre = 0;
        try {
            idGenre = Integer.parseInt(request.getParameter(ID_GENRE));
        }catch (NumberFormatException ex){
            LOGGER.error(ex);
            request.setAttribute("idGenreError", Messages.getInstance(locale).getString(Messages.ID_ERROR));
            error = true;
        }

        int idAuthor = 0;
        try {
            idAuthor = Integer.parseInt(request.getParameter(ID_AUTHOR));
        }catch (NumberFormatException ex){
            LOGGER.error(ex);
            request.setAttribute("idAuthorError", Messages.getInstance(locale).getString(Messages.ID_ERROR));
            error = true;
        }


        BigDecimal price = BigDecimal.valueOf(0.0);
        try {
            price = BigDecimal.valueOf(Double.parseDouble(request.getParameter(PRICE)));
        }catch (NumberFormatException ex){
            LOGGER.info(ex);
            request.setAttribute("priceError", Messages.getInstance(locale).getString(Messages.PRICE_ERROR));
            error = true;
            LOGGER.info("Error is catched");
        }
        LOGGER.info("After try-catch block");

        String measurement = request.getParameter(MEASUREMENT);
        if(!error){
            LOGGER.info("Creating a new edition");
            Edition edition = new Edition.Builder().setTitleUk(titleUk)
                    .setTitleEn(titleEn)
                    .setDescriptionEn(descriptionEn)
                    .setDescriptionUk(descriptionUk)
                    .setIdGenre(idGenre)
                    .setIdAuthor(idAuthor)
                    .setImgPath(imgPath)
                    .setPrice(price)
                    .setMeasurement(measurement)
                    .build();
            if(!adminService.addNewEdition(edition)){
                request.setAttribute("addingEditionError", Messages.getInstance(locale).getString(Messages.ADDING_EDITION_ERROR));
                LOGGER.info("Exception in db");
            }else{
                List<Edition> allEditions = initialService.allListOfEditions();
                request.getServletContext().setAttribute("allEditions", allEditions);
                request.setAttribute("editionAddedSuccess", Messages.getInstance(locale).getString(Messages.EDITION_ADDED_SUCCESS));
                return Path.MAIN_PAGE;
            }
        }

        request.getSession().setAttribute("pageToForward", Path.ADMIN_NEW_EDITION_PAGE);
        return ProjectConstants.EMPTY_PAGE;

    }
}
