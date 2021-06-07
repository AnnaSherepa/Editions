package controller.commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {
    String LOCALE="locale";
    String CURRENT_USER="user";

    /**
     * this method returns relative address of jsp file, which will be forwarded by servlet.
     * also additional services inside method may be called when needed.
     * @param request takes http request from servlet.
     * @param response takes http response from servlet.
     * @return address of .jsp file
     * @throws ServletException
     * @throws IOException
     */
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
