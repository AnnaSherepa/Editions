package controller.commands.readers.order_editions;

import controller.commands.Command;
import manegers.Path;
import models.entity.Edition;
import org.apache.log4j.Logger;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class GroupByGenreCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(GroupByGenreCommand.class);
    private final UserService userService = UserService.getInstance();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Groping editions");
        HttpSession session = request.getSession();
        Optional<String> groupByGenre = Optional.ofNullable(request.getParameter("groupBy"));

        if(!groupByGenre.isPresent()){
            session.setAttribute("actualEditions", userService.getAllEditions());
        }else {
            int idGenre = Integer.parseInt(groupByGenre.get());
            List<Edition> actual = userService.getEditionsByIdGenre(idGenre);
            session.setAttribute("actualEditions", actual);
        }
        LOGGER.info("Edition is grouped");
        return Path.MAIN_PAGE;
    }
}
