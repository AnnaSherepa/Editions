package controller.commands.readers;

import controller.commands.Command;
import manegers.Path;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class SwitchLangCommand implements Command {
    private static final String CURRENT_PAGE = "page";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String locale = request.getParameter("lang");
        String page = request.getParameter(CURRENT_PAGE);
        request.getSession().setAttribute("language", locale);
        if(page != null){
            return page;
        }
        return Path.MAIN_PAGE;
    }
}
