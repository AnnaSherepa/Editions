package controller.commands.readers;

import controller.commands.Command;
import manegers.Path;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

public class SwitchLangCommand implements Command {
    private static final String MAIN_PAGE = "main";
    private static final String LOGIN_PAGE = "logIn";
    private static final String ADMIN_USER_PAGE = "userList";
    private static final String REGISTRATION_PAGE = "signUp";

    private static final String CURRENT_PAGE = "page";

    private static final Logger LOGGER = Logger.getLogger(SwitchLangCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String locale = request.getParameter("lang");
        String page = request.getParameter(CURRENT_PAGE);

        request.getSession().setAttribute("language", locale);


        if(page.equals(LOGIN_PAGE)){
            return Path.LOG_IN;
        }else if(page.equals(REGISTRATION_PAGE)){
            return Path.SIGN_UP;
        }else if(page.equals(ADMIN_USER_PAGE)){
            return Path.ADMIN_USER_LIST;
        }
        return Path.MAIN_PAGE;
    }
}
