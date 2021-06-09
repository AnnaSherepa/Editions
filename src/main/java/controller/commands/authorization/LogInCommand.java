package controller.commands.authorization;

import controller.commands.Command;
import controller.commands.admin.CheckInput;
import manegers.Messages;
import manegers.Path;
import manegers.ProjectConstants;
import models.entity.Edition;
import models.entity.User;
import org.apache.log4j.Logger;
import services.AuthorizationService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public class LogInCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(LogInCommand.class);
    private AuthorizationService service = AuthorizationService.getInstance();
    private CheckInput check = CheckInput.getInstance();
    private static final String PAGE_TO_FORWARD = "pageToForward";
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.info("Log in started");
        String locale = (String) request.getSession().getAttribute("language");
        LOGGER.info("Language: " + locale);

        String login = request.getParameter("login");
        String pass = request.getParameter("pass");

        boolean error = false;

        if(!check.checkLogin(login)){
            request.setAttribute("loginError", Messages.getInstance(locale).getString(Messages.LOGIN_ERROR));
            error = true;
        }

        if(!check.checkPass(pass)){
            LOGGER.info("Pass Input Error: " + Messages.getInstance(locale).getString(Messages.PASS_INPUT_ERROR));
            request.setAttribute("passInputError", Messages.getInstance(locale).getString(Messages.PASS_INPUT_ERROR));
            error = true;
        }

        if(error){
            LOGGER.info("Something goes wrong");
            request.getSession().setAttribute(PAGE_TO_FORWARD, Path.LOG_IN);
            return ProjectConstants.EMPTY_PAGE;
        }

        User user;
        try {
            user = service.logIn(login, pass);
            if(!check.checkStatus(user)){
                request.setAttribute("blockedUserError", Messages.getInstance(locale).getString(Messages.BLOCKED_USER_ERROR));
                LOGGER.warn("User is blocked by admin");
                request.getSession().setAttribute(PAGE_TO_FORWARD, Path.LOG_IN);
                return ProjectConstants.EMPTY_PAGE;
            }
        }catch (NoSuchElementException e){
            request.setAttribute("log_inError", Messages.getInstance(locale).getString(Messages.LOG_IN_ERROR));
            LOGGER.error("Incorrect input. Check log and passWord");
            request.getSession().setAttribute(PAGE_TO_FORWARD, Path.LOG_IN);
            return ProjectConstants.EMPTY_PAGE;
        }

        ServletContext context = request.getServletContext();
        Set<Integer> loggedUser = (Set<Integer>) context.getAttribute("loggedUser");
        if(loggedUser.contains(user.getId())){
            LOGGER.info("User is already logged in system. Please, log out from another session");
            request.setAttribute("log_inError", Messages.getInstance(locale).getString(Messages.LOG_IN_ERROR));
            request.getSession().setAttribute("pageToForward", Path.LOG_IN);
            return ProjectConstants.EMPTY_PAGE;
        }

        loggedUser.add(user.getId());
        context.setAttribute("loggedUser", loggedUser);
        request.getSession().setAttribute("user",user);

        List<Edition> allEditions = (List<Edition>) context.getAttribute("allEditions");
        request.getSession().setAttribute("actualEditions", allEditions);
        request.getSession().setAttribute("language", request.getLocale().toString());

        LOGGER.info("Locale: " + request.getLocale());
        LOGGER.info("User is added to session");
        return Path.MAIN_PAGE;
    }
}
