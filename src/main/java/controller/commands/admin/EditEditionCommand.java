package controller.commands.admin;

import controller.commands.Command;
import manegers.Path;
import models.entity.Edition;
import models.enums.Money;
import services.InitialService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EditEditionCommand implements Command {
    private InitialService initialService = InitialService.getInstance();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("adminPanelGenre",initialService.allListOfGenres());
        request.getSession().setAttribute("adminPanelAuthors", initialService.allListOfAuthors());
        List<Edition> editions = (List<Edition>) request.getServletContext().getAttribute("allEditions");
        int idEdition = Integer.parseInt(request.getParameter("editedIdEdition"));

        Edition edition = editions.stream().filter(edition1 -> idEdition == edition1.getId()).findAny().orElse(null);

        request.getSession().setAttribute("editedEdition", edition);
        request.getSession().setAttribute("measurements", Money.values());
        return Path.ADMIN_UPDATE_EDITION_PAGE;
    }
}
