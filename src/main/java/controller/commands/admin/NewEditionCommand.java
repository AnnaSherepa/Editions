package controller.commands.admin;

import controller.commands.Command;
import manegers.Path;
import models.entity.Genre;
import models.enums.Money;
import services.InitialService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class NewEditionCommand implements Command {

    private InitialService initialService = InitialService.getInstance();
    @Override
    /**
     * Add static genre,
    import authors
   */

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("adminPanelGenre", initialService.allListOfGenres());
        request.getSession().setAttribute("adminPanelAuthors", initialService.allListOfAuthors());
        //request.getSession().setAttribute("measurements", Money.values());
        return Path.ADMIN_NEW_EDITION_PAGE;
    }
}
