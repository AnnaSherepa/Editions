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
    import measurement,
    import authors,
    setLocale for messages*/

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Genre> genres = initialService.allListOfGenres();
        for (Genre genre: genres) {
            System.out.println(genre);

        }
        request.getSession().setAttribute("adminPanelGenre",genres);

        request.getSession().setAttribute("adminPanelAuthors", initialService.allListOfAuthors());
        request.getSession().setAttribute("measurements", Money.values());
        return Path.ADMIN_NEW_EDITION_PAGE;
    }
}
