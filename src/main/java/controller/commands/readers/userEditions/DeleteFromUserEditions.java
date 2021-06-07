package controller.commands.readers.userEditions;

import controller.commands.Command;
import manegers.Path;
import models.entity.ShoppingCart;
import models.entity.User;
import org.apache.log4j.Logger;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DeleteFromUserEditions implements Command {
    private static final Logger LOGGER = Logger.getLogger(DeleteFromUserEditions.class);
    private final UserService userService = UserService.getInstance();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Start deletion");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int idEdition = Integer.parseInt(request.getParameter("idEdition"));

        if(!userService.deleteUserEditionById(user.getId(), idEdition)){
            LOGGER.info("Error with DB");
            return Path.USER_EDITION_PAGE;
        }

        session.setAttribute("user", userService.getUpdatedUser(user.getId()));
        LOGGER.info("Edition was deleted");
        return Path.USER_EDITION_PAGE;
    }
}
