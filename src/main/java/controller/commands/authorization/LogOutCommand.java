package controller.commands.authorization;

import controller.commands.Command;
import manegers.Path;
import models.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;


public class LogOutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        User user =  (User) session.getAttribute("user");
        Set<Integer> loggedUser = (Set<Integer>) context.getAttribute("loggedUser");
        loggedUser.remove(user.getId());
        context.setAttribute("loggedUser", loggedUser);
        session.setAttribute("user", null);
        return Path.MAIN_PAGE;
    }
}
