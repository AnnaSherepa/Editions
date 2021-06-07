package controller.commands.readers.order_editions;

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

public class SearchByTitleCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(SearchByTitleCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String searchRequest = request.getParameter("searchRequest");
        List<Edition> actual = UserService.getInstance().searchByTitle(searchRequest);
        session.setAttribute("actualEditions", actual);
        return Path.MAIN_PAGE;
    }
}
