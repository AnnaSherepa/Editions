package controller.commands.readers.orderEditions;

import controller.commands.Command;
import manegers.Path;
import models.entity.Edition;
import org.apache.log4j.Logger;
import services.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import java.util.stream.Collectors;

public class SearchByTitleCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(SearchByTitleCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = request.getServletContext();
        HttpSession session = request.getSession();
        List<Edition> allEditions = (List<Edition>) context.getAttribute("allEditions");
        String searchRequest = request.getParameter("searchRequest");

        List<Edition> actual = UserService.getInstance().searchByTitle(searchRequest);

        /*This part does not use connection to db*/
//        List<Edition> actual = allEditions.stream()
//                .filter(edition ->  edition.getTitleEn().contains(searchRequest) ||
//                                    edition.getTitleUk().contains(searchRequest))
//                .collect(Collectors.toList());

        session.setAttribute("actualEditions", actual);

        return Path.MAIN_PAGE;
    }
}
